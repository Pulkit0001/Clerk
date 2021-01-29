package com.example.clerk.UIfragments.Dashboard.CheckDues;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.example.clerk.R;
import com.example.clerk.database.ReturnPOJOs.PendingCandidateObjs;
import java.util.ArrayList;
import java.util.List;

public class CheckDuesAdapter  extends RecyclerView.Adapter<CheckDuesViewHolder> {

    List<PendingCandidateObjs> candidates = new ArrayList<>();

    @NonNull
    @Override
    public CheckDuesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_pending_card,
                parent,false);
        return new CheckDuesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckDuesViewHolder holder, int position) {
        PendingCandidateObjs candidate = candidates.get(holder.getAdapterPosition());
        String data = candidate.candidateName+candidate.age;
        holder.candidateNameTv.setText(data);
        String id = ""+candidate.candidateId;
        holder.candidateIdTv.setText(id);
        holder.guardianNameTv.setText(candidate.GuardianName);
        String amount = "\u20b9"+candidate.amount;
        holder.pendingAmountTv.setText(amount);
        final Bundle b =  new Bundle();
        b.putInt("CANDIDATE_ID",(int)candidate.candidateId);
        holder.moreDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_checkDuesFragment_to_candidateFullViewFragment,b);
            }
        });
        holder.payNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_checkDuesFragment_to_receiveFragmentFragment,b);
            }
        });


    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }

    public void submitData(List<PendingCandidateObjs> pendingCandidateObjs) {
        this.candidates = pendingCandidateObjs;
        notifyDataSetChanged();
    }
}
