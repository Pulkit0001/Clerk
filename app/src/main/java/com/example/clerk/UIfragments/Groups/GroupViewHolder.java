package com.example.clerk.UIfragments.Groups;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clerk.R;

public class GroupViewHolder extends RecyclerView.ViewHolder {

    public TextView grp_name;
    public TextView grp_id;
    public  TextView pay_pending;
    public  View itemView;
    public GroupViewHolder(@NonNull View itemView) {
        super(itemView);

        this.itemView=itemView;
        grp_id=itemView.findViewById(R.id.list_group_id_tv);
        grp_name=itemView.findViewById(R.id.list_group_name_tv);
        pay_pending=itemView.findViewById(R.id.pay_pend_tv);

    }
}
