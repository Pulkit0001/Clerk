package com.example.clerk.UIfragments.Groups.NewGroupFragments;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clerk.R;
import com.example.clerk.database.ReturnPOJOs.ChargeOfGroupListDataObj;

import java.util.ArrayList;
import java.util.List;

public class ChooseChargesRecyclerViewAdapter extends RecyclerView.Adapter<ChooseChargesRecyclerViewHolder> {
    private  List<ChargeOfGroupListDataObj> mCharges = new ArrayList<>();
    private boolean[] selected;





    @NonNull
    @Override
    public ChooseChargesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_charge_row,parent,
                false);
        return new ChooseChargesRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChooseChargesRecyclerViewHolder holder, final int position) {
        ChargeOfGroupListDataObj charge = mCharges.get(holder.getAdapterPosition());
        String amount = "\u20b9"+charge.amount;
        holder.chargeAmountTv.setText(amount);
        String id = ""+charge.chargeId;
        holder.chargeIdTv.setText(id);
        holder.chargeNameTv.setText(charge.chargeName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable drawable = new ColorDrawable(ContextCompat.getColor(v.getContext(),R.color.colorPrimary));
                if(selected[holder.getAdapterPosition()]){
                    holder.checkBox.setChecked(false);
                    drawable.setAlpha(0);
                    selected[holder.getAdapterPosition()]=false;
                }
                else {
                    holder.checkBox.setChecked(true);
                    drawable.setAlpha(100);
                    selected[holder.getAdapterPosition()]=true;
                }
                holder.itemView.setForeground(drawable);
            }
        });

    }

    public int[] getCheckedChargeIds(){
        int i=0;
        List<Integer> checkedIds = new ArrayList<>();
        for(boolean status : selected){
            if(status) {
                checkedIds.add(mCharges.get(i).chargeId);
            }
            i++;

        }
        int[] checkArr = new int[checkedIds.size()];
        i=0;
        for(Integer item : checkedIds){
            checkArr[i]=item;
            i++;
        }
        return checkArr;
    }



    @Override
    public int getItemCount() {
        return mCharges.size();
    }

    public void submitData(List<ChargeOfGroupListDataObj> mCharges) {
        this.mCharges = mCharges;
        selected = new boolean[mCharges.size()];
        for (int i = 0; i < mCharges.size(); i++) {
            selected[i] = false; }
        notifyDataSetChanged();
    }


}
