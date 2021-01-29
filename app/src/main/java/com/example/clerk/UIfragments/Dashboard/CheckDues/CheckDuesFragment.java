package com.example.clerk.UIfragments.Dashboard.CheckDues;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.example.clerk.R;
import com.example.clerk.UIfragments.Candidates.CandidateViewModel;
import com.example.clerk.UIfragments.Dashboard.DashboardViewModel;
import com.example.clerk.database.AccountsRepository;
import com.example.clerk.database.ReturnPOJOs.ChooseGroupListDataObj;
import com.example.clerk.database.ReturnPOJOs.PendingCandidateObjs;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckDuesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckDuesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView checkDuesView;
    private AutoCompleteTextView grpSpinner;
    DashboardViewModel viewModel;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CheckDuesFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CheckDuesFragment newInstance(String param1, String param2) {
        CheckDuesFragment fragment = new CheckDuesFragment();
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
        return inflater.inflate(R.layout.fragment_check_dues, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        final Context context = this.getContext();
        grpSpinner = view.findViewById(R.id.check_dues_group_spinner);
        checkDuesView = view.findViewById(R.id.check_dues_recycler_view);
        final CandidateViewModel.SpinnerAdapter adapter = new CandidateViewModel.
                SpinnerAdapter(this.requireContext(), android.R.layout.simple_spinner_dropdown_item);

        final CheckDuesAdapter checkDuesAdapter = new CheckDuesAdapter();
        checkDuesView.setAdapter(checkDuesAdapter);
        checkDuesView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        grpSpinner.setAdapter(adapter);
        AccountsRepository.getGrps().observe(getViewLifecycleOwner(), new Observer<List<ChooseGroupListDataObj>>() {
            @Override
            public void onChanged(List<ChooseGroupListDataObj> chooseGroupListDataObjs) {
                adapter.submitData(chooseGroupListDataObjs);
            }
        });
        grpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, View view, int position, long id) {
                AccountsRepository.getPendingCandidatesOfGroup(adapter.getGroupId(position)).
                        observe(getViewLifecycleOwner(), new Observer<List<PendingCandidateObjs>>() {
                    @Override
                    public void onChanged(List<PendingCandidateObjs> pendingCandidateObjs) {
                       checkDuesAdapter.submitData(pendingCandidateObjs);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        AccountsRepository.getGrps().observe(getViewLifecycleOwner(), new Observer<List<ChooseGroupListDataObj>>() {
            @Override
            public void onChanged(List<ChooseGroupListDataObj> chooseGroupListDataObjs) {

            }
        });

    }
}