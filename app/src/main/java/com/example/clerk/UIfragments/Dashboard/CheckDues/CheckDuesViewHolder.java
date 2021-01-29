package com.example.clerk.UIfragments.Dashboard.CheckDues;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clerk.R;
import com.google.android.material.button.MaterialButton;

public class CheckDuesViewHolder extends RecyclerView.ViewHolder {

    public ImageView candidateImage;
    public TextView candidateNameTv;
    public TextView candidateIdTv;
    public TextView guardianNameTv;
    public MaterialButton payNowBtn;
    public MaterialButton moreDetailsBtn;
    public TextView pendingAmountTv;
    public CheckDuesViewHolder(@NonNull View itemView) {
        super(itemView);
        candidateIdTv = itemView.findViewById(R.id.check_dues_frag_candidate_id_tv);
        candidateNameTv = itemView.findViewById(R.id.check_dues_frag_candidate_name_tv);
        guardianNameTv = itemView.findViewById(R.id.check_dues_frag_guardian_name_tv);
        payNowBtn = itemView.findViewById(R.id.card_pay_btn);
        moreDetailsBtn = itemView.findViewById(R.id.get_more_btn);
        candidateImage = itemView.findViewById(R.id.check_dues_frag_candidate_image);
        pendingAmountTv = itemView.findViewById(R.id.check_dues_frag_candidate_pend_amount_tv);

    }
}
