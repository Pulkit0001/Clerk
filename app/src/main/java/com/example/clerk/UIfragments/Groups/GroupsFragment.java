package com.example.clerk.UIfragments.Groups;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clerk.R;
import com.example.clerk.database.ReturnPOJOs.GroupListDataObj;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class GroupsFragment extends Fragment {

    GroupViewModel viewModel;
    Observer<List<GroupListDataObj>> groupListObserver;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_groups, container, false);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView listView = view.findViewById(R.id.grp_list_view);
        final Context context = this.getContext();
        viewModel = new ViewModelProvider(this).get(GroupViewModel.class);
        groupListObserver = new Observer<List<GroupListDataObj>>() {
            @Override
            public void onChanged(List<GroupListDataObj> groups) {
                System.out.println(groups.size()+".......................................................");
                GroupRecyclerViewAdapter adapter = new GroupRecyclerViewAdapter(groups);
                listView.setAdapter(adapter);
                listView.setLayoutManager(new LinearLayoutManager(context));
            }
        };
        viewModel.getGroupList().observe(getViewLifecycleOwner(),groupListObserver);
        FloatingActionButton fab = view.findViewById(R.id.new_grp_fab);
        fab.setOnClickListener(viewModel.addNewGroup());
    }
}