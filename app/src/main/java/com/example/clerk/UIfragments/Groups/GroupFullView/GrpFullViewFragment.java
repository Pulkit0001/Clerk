package com.example.clerk.UIfragments.Groups.GroupFullView;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.clerk.R;
import com.example.clerk.UIfragments.Groups.NewGroupFragments.ChooseChargesRecyclerViewAdapter;
import com.example.clerk.database.ReturnPOJOs.ChargeOfGroupListDataObj;
import com.example.clerk.database.databaseEntities.Charge;
import com.example.clerk.database.databaseEntities.Group;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GrpFullViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GrpFullViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int id;
    GroupFullView viewModel;

    public GrpFullViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GrpFullViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GrpFullViewFragment newInstance(String param1, String param2) {
        GrpFullViewFragment fragment = new GrpFullViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt("Group_ID");
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        viewModel = new ViewModelProvider(this).get(GroupFullView.class);
        viewModel.setId(id);
        System.out.println("The id I  got is"+viewModel.id+"............................................");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_grp_full_view, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Context context = this.getContext();


        final TextView grpIdTv = view.findViewById(R.id.group_id_tv2);
        final TextView grpNameTv = view.findViewById(R.id.textView4);

        viewModel.getGrp().observe(getViewLifecycleOwner(), new Observer<Group>() {
            @Override
            public void onChanged(Group group) {
                String id= ""+group.getGroupId();
                grpIdTv.setText(id);
                grpNameTv.setText(group.getGroupName());
            }
        });

        MaterialButton seeMemBtn = view.findViewById(R.id.see_members_btn);
        MaterialButton addNewChrg = view.findViewById(R.id.add_new_charge_btn);
        MaterialButton checkPendsBtn = view.findViewById(R.id.check_pending_btn);


        final RecyclerView ChargeList = view.findViewById(R.id.grp_full_view_charges_list_view);
        final ChooseChargesRecyclerViewAdapter adapter = new ChooseChargesRecyclerViewAdapter();
        ChargeList.setAdapter(adapter);
        ChargeList.setLayoutManager(new LinearLayoutManager(context));

        viewModel.getChargesOfGroup().observe(getViewLifecycleOwner(), new Observer<List<ChargeOfGroupListDataObj>>() {
            @Override
            public void onChanged(List<ChargeOfGroupListDataObj> charges) {

                adapter.submitData(charges);
            }
        });
        seeMemBtn.setOnClickListener(viewModel.seeMembers());
        addNewChrg.setOnClickListener(viewModel.addMoreCharges());
        checkPendsBtn.setOnClickListener(viewModel.checkPendings());
    }
}