package com.example.clerk.UIfragments.Candidates;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clerk.R;
import com.example.clerk.database.ReturnPOJOs.CandidateListDataObj;
import com.example.clerk.database.databaseEntities.Candidate;

import java.util.List;
import java.util.Objects;

public class CandidateRecyclerViewAdapter extends RecyclerView.Adapter<CandidateViewHolder>{
    List<CandidateListDataObj> candidates;

    public CandidateRecyclerViewAdapter(List<CandidateListDataObj> candidates){
        this.candidates=candidates;
    }

    @NonNull
    @Override
    public CandidateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Row_Card = LayoutInflater.from(parent.getContext()).inflate(R.layout.candidate_row_card,parent,false);
        return new CandidateViewHolder(Row_Card);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final CandidateViewHolder holder, int position) {
        final CandidateListDataObj student = candidates.get(position);
        holder.candidateNameTv.setText(student.candidateName);
        holder.candidateIdTv.setText(""+student.candidateId);
        holder.pendingBtn.setText(student.dueAmount+"Pending");
        final Bundle b = new Bundle();
        b.putInt("CANDIDATE_ID", student.candidateId);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.action_candidatesFragment_to_candidateFullViewFragment,b);
            }
        });

        holder.pendingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.action_candidatesFragment_to_checkDuesFragment,b);
            }
        });
        holder.getHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_candidatesFragment_to_recentPaymentsFragment,b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }
}
