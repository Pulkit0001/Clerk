package com.example.clerk.UIfragments.Candidates.NewCandidateFragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clerk.R;
import com.example.clerk.database.ReturnPOJOs.ChooseGroupListDataObj;
import com.example.clerk.database.ReturnPOJOs.GroupListDataObj;
import com.example.clerk.database.databaseEntities.Group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ChooseGroupRecyclerViewAdapter extends RecyclerView.Adapter<ChooseGroupRecyclerViewHolder> {

    List<ChooseGroupListDataObj> groups;
    boolean[] status;
    int selectedGroup=-1;
    RecyclerView listView;


    public ChooseGroupRecyclerViewAdapter() {

    }

    @NonNull
    @Override
    public ChooseGroupRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_group_row,parent,
                false);
        return new ChooseGroupRecyclerViewHolder(v);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        listView = recyclerView;
    }



    @Override
    public void onBindViewHolder(@NonNull final ChooseGroupRecyclerViewHolder holder, final int position1) {
        final int  position = holder.getAdapterPosition();
        final Context context = holder.itemView.getContext();
        final ChooseGroupListDataObj group = groups.get(position);

        if(position!=selectedGroup){
            holder.itemView.setSelected(false);
            ColorDrawable drawable =new ColorDrawable(ContextCompat.getColor(context,R.color.colorSurface));
            drawable.setAlpha(0);
            holder.itemView.setForeground(drawable);
        }
        else {
            holder.itemView.setSelected(false);
            ColorDrawable drawable =new ColorDrawable(ContextCompat.getColor(context,R.color.primaryColor));
            drawable.setAlpha(100);
            holder.itemView.setForeground(drawable);
        }
        String s = group.startTime+" - "+group.endTime;
        holder.timeStamp.setText(s);
        holder.groupNameTv.setText(group.groupName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previouslySelected = selectedGroup;
                if(selectedGroup!=position) {
                    selectedGroup=position;
                    v.setSelected(true);
                    ColorDrawable drawable =new ColorDrawable(ContextCompat.getColor(context,R.color.primaryColor));
                    drawable.setAlpha(100);
                    holder.itemView.setForeground(drawable);
                }
                else {
                    selectedGroup=-1;
                }
                notifyItemChanged(previouslySelected);
            }
        });
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public int getSelectedGroup() {
        if(selectedGroup!=-1)
        return (int) groups.get(selectedGroup).groupId;
        else
            return -1;
    }

    public void submitData(List<ChooseGroupListDataObj> groups) {
        this.groups = groups;
        notifyDataSetChanged();
        status = new boolean[groups.size()];
        Arrays.fill(status, false);
    }
}
