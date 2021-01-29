package com.example.clerk.UIfragments.Dashboard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.clerk.R;
import com.example.clerk.database.AccountsRepository;
import com.example.clerk.database.databaseEntities.Candidate;
import com.example.clerk.database.databaseEntities.PendingPayment;

import java.util.Objects;


public class CheckStatusFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    DashboardViewModel viewModel;
    TextView candidateNameTv;
    TextView guardianNameTv;
    TextView pendingAmountTv;
    TextView dueDateTv;
    TextView issueDateTv;
    TextView totalAmountTv;
    LinearLayout descriptionView;
    LinearLayout descriptionChildView;
    NavController controller;
    final int[] candidateId = {0};
    Context context;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CheckStatusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckStatusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckStatusFragment newInstance(String param1, String param2) {
        CheckStatusFragment fragment = new CheckStatusFragment();
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
        viewModel = new ViewModelProvider(requireActivity()).get(DashboardViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_status, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        candidateNameTv = view.findViewById(R.id.check_status_candidate_name_tv);
        guardianNameTv = view.findViewById(R.id.check_status_guardian_name_tv);
        pendingAmountTv = view.findViewById(R.id.check_status_pending_amount_tv);
        issueDateTv = view.findViewById(R.id.check_status_joining_date_tv);
        dueDateTv = view.findViewById(R.id.check_status_due_date);
        totalAmountTv = view.findViewById(R.id.check_status_total_amount_tv);
        descriptionView = view.findViewById(R.id.description_view);
        descriptionChildView = view.findViewById(R.id.description_child_view);
        controller = Navigation.findNavController(view);
        context = this.getContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Check Candidate Status");
        final EditText et  = new EditText(context);
        et.setHint("Candidate Id");
        builder.setView((et));
        builder.setPositiveButton("Check", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String id = et.getText().toString();
                if(id.length()>0)
                    candidateId[0] =Integer.parseInt(id);
                dialog.dismiss();
                updateData();
            }
        });
        updateData();
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(dialog!=null )
                    dialog.dismiss();


            }
        });
        builder.create().show();




    }

    private void updateData() {
        if (candidateId[0] > 0) {
            viewModel.getCandidateDetails(candidateId[0]).
                    observe(getViewLifecycleOwner(), new Observer<Candidate>() {
                        @Override
                        public void onChanged(Candidate candidate) {
                            if(candidate==null){
                                new AlertDialog.Builder(context).setTitle("SORRY! No Such Candidate Exist").create().show();
                                controller.popBackStack();
                            }
                            String data = candidate.getName() + candidate.getAge();
                            candidateNameTv.setText(data);
                            guardianNameTv.setText(candidate.getGuardianName());
                        }
                    });


            viewModel.getPendingPaymentOfCandidate(candidateId[0]).
                    observe(getViewLifecycleOwner(), new Observer<PendingPayment>() {
                        @Override
                        public void onChanged(PendingPayment payment) {
                            if (payment != null) {
                                issueDateTv.setText((CharSequence) payment.getIssueDate());
                                dueDateTv.setText((CharSequence) payment.getDueDate());
                                pendingAmountTv.setText(payment.getDueAmount());
                                totalAmountTv.setText(payment.getDueAmount());
//                              TextView tv1 = (TextView)(descriptionChildView.getChildAt(0));
//                              TextView tv2 = (TextView)(descriptionChildView).getChildAt(1);
//                              tv1.setText();
//                              tv2.setText();
//                               descriptionView.addView(descriptionChildView);
                            }
                            else {
                                pendingAmountTv.setText("\u20b9 0");
                                totalAmountTv.setText("\u20b9 0");
                                pendingAmountTv.setTextColor(requireContext().getResources().getColor(R.color.primaryColor));
                            }
                        }

                    });
        }
    }

}