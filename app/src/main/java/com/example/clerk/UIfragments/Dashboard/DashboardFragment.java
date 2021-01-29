package com.example.clerk.UIfragments.Dashboard;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.clerk.R;
import com.google.android.material.button.MaterialButton;

public class DashboardFragment extends Fragment {

    NavController controller;
    DashboardViewModel viewModel;
    MaterialButton checkStatusBtn;
    MaterialButton newCandidateBtn;
    MaterialButton newChargeBtn;
    MaterialButton getHistoryBtn;
    MaterialButton checkDuesBtn;
    MaterialButton receivePaymentBtn;
    TextView totalPendingAmountTV;

    public DashboardFragment(){
        //nothing to do here
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.dashboard_layout, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        totalPendingAmountTV = view.findViewById(R.id.total_amount_pending_tv);
        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        viewModel.candidateId=0;
        LiveData<Integer> livePendingAmount = viewModel.getTotalPendingAmount();
        livePendingAmount.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer!=null)
                    totalPendingAmountTV.setText("\u20b9 "+integer);
            }
        });
        checkDuesBtn = view.findViewById(R.id.dashboard_check_dues_btn);
        checkStatusBtn = view.findViewById(R.id.check_status_btn);
        newCandidateBtn = view.findViewById(R.id.dashboard_new_candidate_btn);
        newChargeBtn = view.findViewById(R.id.dashboard_new_charge_btn);
        getHistoryBtn = view.findViewById(R.id.dashboard_get_history_btn);
        receivePaymentBtn = view.findViewById(R.id.dashboard_receive_payment_btn);
        checkDuesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_homeFragment_to_checkDuesFragment);
            }
        });
        newCandidateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_homeFragment_to_navigation2);
            }
        });
        newChargeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_homeFragment_to_chargeDetailsFragment);
            }
        });
        checkStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    controller.navigate(R.id.action_homeFragment_to_checkStatusFragment);
            }
        });
        receivePaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_homeFragment_to_receiveFragmentFragment);
            }
        });
        getHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_homeFragment_to_recentPaymentsFragment);
            }
        });

    }
}