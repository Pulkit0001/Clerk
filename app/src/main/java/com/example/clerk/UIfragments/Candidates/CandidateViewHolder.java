package com.example.clerk.UIfragments.Candidates;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clerk.R;
import com.google.android.material.button.MaterialButton;

public class CandidateViewHolder extends RecyclerView.ViewHolder {

    public TextView candidateNameTv;
    public TextView candidateIdTv;
    public MaterialButton pendingBtn;
    public ImageView candidateImageIcon;
    public MaterialButton getHistoryBtn;
    public View itemView;
    public CandidateViewHolder(@NonNull View itemView) {
        super(itemView);
        candidateNameTv=itemView.findViewById(R.id.candidate_row_name_tv);
        candidateIdTv=itemView.findViewById(R.id.candidate_row_id_tv);
        pendingBtn=itemView.findViewById(R.id.candidate_pends_btn);
        candidateImageIcon=itemView.findViewById(R.id.candidate_row_image_ic);
        getHistoryBtn = itemView.findViewById(R.id.get_candidate_history_btn);
        this.itemView = itemView;

    }
}
