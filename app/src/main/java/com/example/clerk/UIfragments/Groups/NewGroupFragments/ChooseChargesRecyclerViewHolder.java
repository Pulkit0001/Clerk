package com.example.clerk.UIfragments.Groups.NewGroupFragments;

import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clerk.R;

public class ChooseChargesRecyclerViewHolder extends RecyclerView.ViewHolder {
    public CheckBox checkBox;
    public TextView chargeIdTv;
    public TextView chargeNameTv;
    public TextView chargeAmountTv;
    public FrameLayout itemView;



    public ChooseChargesRecyclerViewHolder(@NonNull View itemView) {

        super(itemView);
        checkBox =  itemView.findViewById(R.id.payment_selection_checkBox);
        chargeIdTv = itemView.findViewById(R.id.choose_charge_id_tv);
        chargeNameTv = itemView.findViewById(R.id.choose_charge_name_tv);
        chargeAmountTv = itemView.findViewById(R.id.choose_charge_amount_tv);
        this.itemView=(FrameLayout)itemView;


    }
}
