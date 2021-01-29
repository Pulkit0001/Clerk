package com.example.clerk.UIfragments.Charges;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clerk.R;
import com.example.clerk.database.ReturnPOJOs.ChargeOfGroupListDataObj;
import com.example.clerk.database.databaseEntities.Charge;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class ChargesFragment extends Fragment {

    private static final int NEW_FEE_CHARGE = 0;
    private static final int NEW_DISCOUNT = 1;
    private static final  int  NEW_PENALTY_CHARGE = 2;


    ChargesViewModel viewModel;
    RecyclerView feeChargeList;
    RecyclerView discountList;
    RecyclerView penaltyChargeList;
    MaterialButton newFeeChargeBtn;
    MaterialButton newDiscountBtn;
    MaterialButton newPenaltyChargeBtn;
    NavController controller;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ChargesViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_charges, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        final Bundle bundle = new Bundle();
        feeChargeList = view.findViewById(R.id.fee_charge_list);
        discountList = view.findViewById(R.id.discount_list);
        penaltyChargeList = view.findViewById(R.id.penalty_charge_list);
        newFeeChargeBtn = view.findViewById(R.id.new_fee_charge_btn);
        newDiscountBtn = view.findViewById(R.id.new_discount_btn);
        newPenaltyChargeBtn = view.findViewById(R.id.new_penalty_charge_btn);
        feeChargeList.setAdapter(new ChargeRecyclerViewAdapter());
        feeChargeList.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.
                HORIZONTAL,false ));
        viewModel.getFeeChargeList().observe(getViewLifecycleOwner(), new Observer<List<ChargeOfGroupListDataObj>>() {
            @Override
            public void onChanged(List<ChargeOfGroupListDataObj> charges) {
                ((ChargeRecyclerViewAdapter)feeChargeList.getAdapter()).submitData(charges);
            }
        });

        discountList.setAdapter(new ChargeRecyclerViewAdapter());
        discountList.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.
                HORIZONTAL,false ));
        viewModel.getDiscountList().observe(getViewLifecycleOwner(), new Observer<List<ChargeOfGroupListDataObj>>() {
            @Override
            public void onChanged(List<ChargeOfGroupListDataObj> charges) {
                ((ChargeRecyclerViewAdapter)discountList.getAdapter()).submitData(charges);
            }
        });
        penaltyChargeList.setAdapter(new ChargeRecyclerViewAdapter());
        penaltyChargeList.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.
                HORIZONTAL,false ));
        viewModel.getPenaltyChargeList().observe(getViewLifecycleOwner(), new Observer<List<ChargeOfGroupListDataObj>>() {
            @Override
            public void onChanged(List<ChargeOfGroupListDataObj> charges) {
                ((ChargeRecyclerViewAdapter)penaltyChargeList.getAdapter()).submitData(charges);
            }
        });
        newFeeChargeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt("KEY",NEW_FEE_CHARGE);
                controller.navigate(R.id.action_chargesFragment_to_chargeDetailsFragment, bundle);
            }
        });
        newDiscountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt("KEY",NEW_DISCOUNT);
                controller.navigate(R.id.action_chargesFragment_to_chargeDetailsFragment, bundle);
            }
        });
        newPenaltyChargeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt("KEY",NEW_PENALTY_CHARGE);
                controller.navigate(R.id.action_chargesFragment_to_chargeDetailsFragment, bundle);
            }
        });
    }
}