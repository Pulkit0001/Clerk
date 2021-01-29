package com.example.clerk.UIfragments.Dashboard.RecentPayment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clerk.R;

public class PaymentViewHolder extends RecyclerView.ViewHolder {
    public TextView dateTimeStamp;
    public TextView paymentIdTv;
    public TextView candidateNameTv;
    public TextView paidAmountTv;
    public PaymentViewHolder(@NonNull View itemView) {
        super(itemView);
        dateTimeStamp = itemView.findViewById(R.id.date_time_tv);
        paymentIdTv = itemView.findViewById(R.id.payment_row_payment_id_tv);
        candidateNameTv = itemView.findViewById(R.id.payment_row_cand_name_tv);
        paidAmountTv = itemView.findViewById(R.id.paid_amount_tv);
    }
}
