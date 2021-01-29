package com.example.clerk.UIfragments.Candidates.NewCandidateFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clerk.R;
import com.example.clerk.database.ReturnPOJOs.ChooseGroupListDataObj;
import com.google.android.material.button.MaterialButton;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChooseGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChooseGroupFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    NewCandidateViewModel viewModel;
    NavController controller;
    public ChooseGroupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChooseGroupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChooseGroupFragment newInstance(String param1, String param2) {
        ChooseGroupFragment fragment = new ChooseGroupFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_group, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        MaterialButton createNewBtn = view.findViewById(R.id.create_new_group_from_choose_group);
        final MaterialButton nextBtn = view.findViewById(R.id.choose_group_next_btn);
        final Context context = this.getContext();
        final RecyclerView chooseGrpListView = view.findViewById(R.id.choose_group_list_view);
        viewModel = new ViewModelProvider(controller.getViewModelStoreOwner(R.id.navigation2)).
                get(NewCandidateViewModel.class);
        final ChooseGroupRecyclerViewAdapter adapter = new ChooseGroupRecyclerViewAdapter();
        viewModel.getGroups().observe(getViewLifecycleOwner(), new Observer<List<ChooseGroupListDataObj>>() {
            @Override
            public void onChanged(List<ChooseGroupListDataObj> groups) {
                if(groups.size()==0){
                    nextBtn.setEnabled(false);
                }
                else {
                    nextBtn.setEnabled(true);
                }
                chooseGrpListView.setAdapter(adapter);
                adapter.submitData(groups);
                chooseGrpListView.setLayoutManager(new LinearLayoutManager(context));
            }
        });
        createNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 controller.navigate(R.id.action_global_navigation3);
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter.getSelectedGroup()<0){
                    new AlertDialog.Builder(context).setTitle("ERROR").
                            setMessage("Please Select a Group").create().show();
                }
                else {
                    int selectedGroup = adapter.getSelectedGroup();
                    System.out.println(selectedGroup+".............................................");
                    viewModel.addGroup(selectedGroup);
                    Navigation.findNavController(v).navigate(R.id.action_chooseGroupFragment_to_choose_extra_charges_fragment);
                }

            }
        });

    }
}