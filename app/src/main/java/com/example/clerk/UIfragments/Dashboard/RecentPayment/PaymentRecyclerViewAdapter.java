package com.example.clerk.UIfragments.Dashboard.RecentPayment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clerk.R;
import com.example.clerk.database.AccountsRepository;
import com.example.clerk.database.databaseEntities.PaidPayment;

import java.util.ArrayList;
import java.util.List;

public class PaymentRecyclerViewAdapter extends RecyclerView.Adapter<PaymentViewHolder> {
    List<PaidPayment> recentPayments = new ArrayList<>();


    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_row_card,parent,
                false);
        return new PaymentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        PaidPayment payment = recentPayments.get(position);
        holder.paidAmountTv.setText(payment.getAmount());
        holder.candidateNameTv.setText(payment.getCandidateName());
        String dateTime = payment.getPaidDate()+" "+payment.getPaidTime();
        holder.dateTimeStamp.setText(dateTime);
        holder.paymentIdTv.setText(payment.getPaymentId());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void submitData(List<PaidPayment> paidPayments) {
        this.recentPayments = paidPayments;
        notifyDataSetChanged();

    }
}
