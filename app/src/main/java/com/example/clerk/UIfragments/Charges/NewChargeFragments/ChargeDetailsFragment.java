package com.example.clerk.UIfragments.Charges.NewChargeFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clerk.R;
import com.example.clerk.database.databaseEntities.Charge;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChargeDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChargeDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String NEW_FEE_CHARGE = "New Fee Charge";
    private static final String NEW_DISCOUNT = "New Discount";
    private static final  String  NEW_PENALTY_CHARGE = "New Penalty";

    EditText chargeNameInp;
    EditText chargeAmountInp;
    MaterialButton chargeDetailsDoneBtn;
    private  int chargeType;
    NewChargeViewModel viewModel;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChargeDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChargeDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChargeDetailsFragment newInstance(String param1, String param2) {
        ChargeDetailsFragment fragment = new ChargeDetailsFragment();
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
            Bundle b = getArguments();
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            chargeType = b.getInt("KEY");
        }
        viewModel = new ViewModelProvider(this).get(NewChargeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_charge_details, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Context context = this.getContext();
        chargeNameInp = view.findViewById(R.id.charge_name_input);
        chargeAmountInp = view.findViewById(R.id.charge_amount_input);
        chargeDetailsDoneBtn = view.findViewById(R.id.charge_details_done_btn);
        final AutoCompleteTextView repaymentInp = view.findViewById(R.id.repayment_type_input);
        final TextView error = view.findViewById(R.id.charge_details_error_tv);
        error.setVisibility(View.INVISIBLE);
        ArrayAdapter<NewChargeViewModel.Interval> adapter = new ArrayAdapter<>(this.getContext(),
                R.layout.drop_down_menu_item,R.id.gender_dropdown_tv, NewChargeViewModel.Interval.values());
        repaymentInp.setAdapter(adapter);
        final int[] x = {10};
        repaymentInp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("charge amount is"+position+"...........................................");
                x[0] =position;
            }
        });
        final NavController controller = Navigation.findNavController(view);
        chargeDetailsDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String chargeName = chargeNameInp.getText().toString();
                String chargeAmount = chargeAmountInp.getText().toString();
                if(chargeAmount.length()!=0 && chargeName.length()!=0 && x[0]<7 ) {
                    NewChargeViewModel.Interval repaymentInput = NewChargeViewModel.Interval.values()[x[0]];
                    viewModel.addCharge(chargeName, chargeAmount, repaymentInput, chargeType);
                    controller.popBackStack(R.id.chargeDetailsFragment, true);
                    Toast.makeText(context,"Charge Created Successfully",Toast.LENGTH_SHORT).show();
                }
                else{
                    error.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}