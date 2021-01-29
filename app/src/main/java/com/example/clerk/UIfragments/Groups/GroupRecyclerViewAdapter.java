package com.example.clerk.UIfragments.Groups;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clerk.R;
import com.example.clerk.database.ReturnPOJOs.GroupListDataObj;
import com.example.clerk.database.databaseEntities.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupRecyclerViewAdapter extends RecyclerView.Adapter<GroupViewHolder> {

    List<GroupListDataObj> grpList = new ArrayList<>();

    public GroupRecyclerViewAdapter(List<GroupListDataObj> grpList){
        this.grpList=grpList;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View Row_Card = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_row_card,parent,false);
        return new GroupViewHolder(Row_Card);
    }

    @Override
    public void onBindViewHolder(@NonNull final GroupViewHolder holder, int position1) {

        int position = holder.getAdapterPosition();
        GroupListDataObj grp = grpList.get(position);
        holder.grp_name.setText(grp.groupName);
        holder.grp_id.setText(""+grp.groupId);
        holder.pay_pending.setText(""+grp.strength);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                System.out.println("The ifd I got on click ios "+Integer.parseInt(holder.grp_id.getText().toString()));
                b.putInt("Group_ID",Integer.parseInt(holder.grp_id.getText().toString()));
                Navigation.findNavController(v).navigate(R.id.action_groupsFragment_to_grpFullViewFragment,b);
            }
        });
    }

    @Override
    public int getItemCount() {

        return grpList.size();
    }
}
