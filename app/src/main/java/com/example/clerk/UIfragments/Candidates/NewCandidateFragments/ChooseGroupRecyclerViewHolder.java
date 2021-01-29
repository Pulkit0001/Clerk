package com.example.clerk.UIfragments.Candidates.NewCandidateFragments;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clerk.R;

public class ChooseGroupRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView groupNameTv;
    public TextView timeStamp;
    public FrameLayout itemView;
    public ChooseGroupRecyclerViewHolder(@NonNull final View itemView) {
        super(itemView);
        this.itemView = (FrameLayout)itemView;
        timeStamp = itemView.findViewById(R.id.choose_group_time_stamp_tv);
        groupNameTv = itemView.findViewById(R.id.choose_group_name_tv);
    }
}
