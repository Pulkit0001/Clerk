package com.example.clerk.UIfragments.Candidates.NewCandidateFragments;

import android.graphics.Path;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.clerk.R;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private NewCandidateViewModel viewModel;
    private NavController navController;
    TextView error;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContactDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactDetailsFragment newInstance(String param1, String param2) {
        ContactDetailsFragment fragment = new ContactDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_details, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        viewModel = new ViewModelProvider(navController.getViewModelStoreOwner(R.id.navigation2)).
                get(NewCandidateViewModel.class);
        MaterialButton nextBtn = view.findViewById(R.id.contact_details_fragment_next_btn);
        final EditText AddressInput = view.findViewById(R.id.address_input);
        final EditText ContactNoInput = view.findViewById(R.id.contact_no_input);
        final EditText OptionalContactNoInput = view.findViewById(R.id.optional_contact_no_input);
        final EditText EmailInput = view.findViewById(R.id.email_id_input);
        error = view.findViewById(R.id.contact_details_error_tv);
        error.setVisibility(View.INVISIBLE);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = AddressInput.getText().toString();
                String contactNo = ContactNoInput.getText().toString();
                String optionalContactNo = OptionalContactNoInput.getText().toString();
                String emailId = EmailInput.getText().toString();

                if (address.length()!=0 && contactNo.length()!=0 && emailId.length()!=0&& contactNo.length()==10
                &&(optionalContactNo.length()==0||optionalContactNo.length()==10)){
                    error.setVisibility(View.INVISIBLE);
                    viewModel.addContactDetails(address,contactNo,optionalContactNo,emailId);
                    navController.navigate(R.id.action_contactDetailsFragment_to_chooseGroupFragment);
                }
                else{
                    if(optionalContactNo.length()!=10&&optionalContactNo.length()>0){
                        OptionalContactNoInput.setError("Provide a valid Contact No.");
                    }
                    if (contactNo.length()!=10) {
                        ContactNoInput.setError("Provide a valid Contact No.");
                    }
                    error.setVisibility(View.VISIBLE);

                }
            }
        });
    }
}