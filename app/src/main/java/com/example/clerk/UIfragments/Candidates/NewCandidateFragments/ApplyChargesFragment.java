package com.example.clerk.UIfragments.Candidates.NewCandidateFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clerk.R;
import com.example.clerk.UIfragments.Candidates.CandidateViewModel;
import com.example.clerk.UIfragments.Groups.NewGroupFragments.ChooseChargesRecyclerViewAdapter;
import com.example.clerk.UIfragments.Groups.NewGroupFragments.NewGroupViewModel;
import com.example.clerk.database.ReturnPOJOs.ChargeOfGroupListDataObj;
import com.google.android.material.button.MaterialButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ApplyChargesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ApplyChargesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    NewCandidateViewModel viewModel;
    int candidateId;
    String message;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ApplyChargesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ApplyChargesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ApplyChargesFragment newInstance(String param1, String param2) {
        ApplyChargesFragment fragment = new ApplyChargesFragment();
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
            candidateId = getArguments().getInt("CANDIDATE_ID");
            message = getArguments().getString("MESSAGE");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_apply_charges2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Context context = this.getContext();
        final int[] chargeIds = {};
        final NavController controller = Navigation.findNavController(view);
        if (message != null) {
            if (message.equals("Extra")){
                viewModel = new ViewModelProvider(this).get(NewCandidateViewModel.class);
            }
        }
        else {
            ViewModelStoreOwner store = Navigation.findNavController(view).getViewModelStoreOwner(R.id.navigation2);
            viewModel = new ViewModelProvider(store).get(NewCandidateViewModel.class);
        }

        final ChooseChargesRecyclerViewAdapter adapter = new ChooseChargesRecyclerViewAdapter();

        final RecyclerView chooseCharges = view.findViewById(R.id.choose_extra_charges);
        viewModel.getCharges().observe(getViewLifecycleOwner(),new Observer<List<ChargeOfGroupListDataObj>>() {
            @Override
            public void onChanged(List<ChargeOfGroupListDataObj> charges) {
                chooseCharges.setAdapter(adapter);
                chooseCharges.setLayoutManager(new LinearLayoutManager(context));
                adapter.submitData(charges);
            }
        });


        MaterialButton nextBtn = view.findViewById(R.id.apply_extra_charges_btn);
        MaterialButton createNewBtn = view.findViewById(R.id.create_new_charge_from_apply_extra_charges);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (message != null) {
                    if(message.equals("Extra")){
                        viewModel.applyMoreCharges(adapter.getCheckedChargeIds(),candidateId);
                        controller.popBackStack();
                    }
                }
                else {
                    viewModel.applyCharges(adapter.getCheckedChargeIds());
                    //Bundle bundle = new Bundle();
                    //bundle.putInt("GROUP_ID",viewModel.id);
                    //controller.navigate(R.id.action_global_grpFullViewFragment,bundle);
                    controller.navigate(R.id.action_placeholder_to_choosePhotoFragment);
                }

            }
        });
        createNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_global_chargeDetailsFragment);
            }
        });
    }
}