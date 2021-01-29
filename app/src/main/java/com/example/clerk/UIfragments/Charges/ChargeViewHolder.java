package com.example.clerk.UIfragments.Charges;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clerk.R;

public class ChargeViewHolder extends RecyclerView.ViewHolder {

    TextView chargeName;
    TextView chargeId;
    TextView chargeAmount;

    public ChargeViewHolder(@NonNull View itemView) {
        super(itemView);
        chargeName = itemView.findViewById(R.id.row_charge_name);
        chargeId = itemView.findViewById(R.id.charge_id);
        chargeAmount = itemView.findViewById(R.id.charge_amount);
    }
}
