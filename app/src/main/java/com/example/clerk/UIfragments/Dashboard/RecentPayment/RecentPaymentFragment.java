package com.example.clerk.UIfragments.Dashboard.RecentPayment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clerk.R;
import com.example.clerk.database.AccountsRepository;
import com.example.clerk.database.databaseEntities.PaidPayment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecentPaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecentPaymentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecentPaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecnentPaymentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecentPaymentFragment newInstance(String param1, String param2) {
        RecentPaymentFragment fragment = new RecentPaymentFragment();
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
        return inflater.inflate(R.layout.fragment_recent_payments, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView recents = view.findViewById(R.id.recent_payments_listView);
        final PaymentRecyclerViewAdapter adapter = new PaymentRecyclerViewAdapter();
        recents.setAdapter(adapter);
        recents.setLayoutManager(new LinearLayoutManager(this.getContext()));
        AccountsRepository.getRecentPayments().observe(getViewLifecycleOwner(), new Observer<List<PaidPayment>>() {
            @Override
            public void onChanged(List<PaidPayment> paidPayments) {
                adapter.submitData(paidPayments);
            }
        });

    }
}