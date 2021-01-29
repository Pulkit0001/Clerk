package com.example.clerk.UIfragments.Charges;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clerk.R;
import com.example.clerk.database.ReturnPOJOs.ChargeOfGroupListDataObj;
import com.example.clerk.database.databaseEntities.Charge;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.Inflater;

public class ChargeRecyclerViewAdapter extends RecyclerView.Adapter<ChargeViewHolder> {
    List<ChargeOfGroupListDataObj> mCharges = new ArrayList<>();

    public ChargeRecyclerViewAdapter(){


    }
    public void submitData(List<ChargeOfGroupListDataObj> charges){
        this.mCharges = charges;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ChargeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.charge_row_card,parent,
                false);
        return new ChargeViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ChargeViewHolder holder, int position) {
        ChargeOfGroupListDataObj mCharge = mCharges.get(holder.getAdapterPosition());
        String amount = "\u20b9"+mCharge.amount;
        holder.chargeAmount.setText(amount);
        String id = ""+mCharge.chargeId;
        holder.chargeId.setText(id);
        holder.chargeName.setText(mCharge.chargeName);

    }
    @Override
    public int getItemCount() {
        return mCharges.size();
    }
}
