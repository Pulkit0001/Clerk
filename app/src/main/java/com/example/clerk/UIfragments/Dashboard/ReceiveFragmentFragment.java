package com.example.clerk.UIfragments.Dashboard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clerk.R;
import com.example.clerk.database.databaseEntities.Candidate;
import com.example.clerk.database.databaseEntities.PendingPayment;
import com.google.android.material.button.MaterialButton;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReceiveFragmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReceiveFragmentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Context context;
    DashboardViewModel viewModel;
    NavController controller;
    TextView candidateNameTv;
    TextView guardianNameTv;
    TextView candidateIdTv;
    TextView issueDateTv;
    TextView dueDateTv;
    TextView pendingAmountTv;
    ImageView candidateImage;
    EditText paidAmountInput;
    MaterialButton cancelBtn;
    MaterialButton acceptBtn;
    int[] candidateId = new int[1];

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReceiveFragmentFragment() {
        // Required empty public constructor
    }

    public static ReceiveFragmentFragment newInstance(String param1, String param2) {
        ReceiveFragmentFragment fragment = new ReceiveFragmentFragment();
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
        return inflater.inflate(R.layout.fragment_receive_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = this.getContext();
        controller = Navigation.findNavController(view);
        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        candidateNameTv = view.findViewById(R.id.receiveFragment_candidate_name);
        candidateIdTv = view.findViewById(R.id.receiveFragment_candidate_id);
        guardianNameTv = view.findViewById(R.id.receiveFragment_guardian_name);

        candidateImage = view.findViewById(R.id.receiveFragment_candidate_image);

        issueDateTv = view.findViewById(R.id.receiveFragment_issue_date_tv);
        dueDateTv = view.findViewById(R.id.receiveFragment_due_date_tv);
        pendingAmountTv = view.findViewById(R.id.receiveFragment_pending_amount_tv);

        paidAmountInput = view.findViewById(R.id.receiveFragment_paid_amount_et);

        cancelBtn = view.findViewById(R.id.receiveFragment_cancel_btn);
        acceptBtn = view.findViewById(R.id.receiveFragment_accept_btn);



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
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(dialog!=null )
                    dialog.dismiss();


            }
        });
        builder.create().show();
    }
    void updateData(){
        if(candidateId[0]>0){
            viewModel.getCandidateDetails(candidateId[0]).
                    observe(getViewLifecycleOwner(), new Observer<Candidate>() {
                @Override
                public void onChanged(Candidate candidate) {
                    if(candidate==null){
                        new AlertDialog.Builder(context).setTitle("SORRY! No Such Candidate Exist\n").create().show();
                        controller.popBackStack();
                    }
                    else {
                        String data = candidate.getName() + candidate.getAge();
                        candidateNameTv.setText(data);
                        guardianNameTv.setText(candidate.getGuardianName());
                        data = "" + candidate.getCandidateId();
                        candidateIdTv.setText(data);
                    }
                }
            });
            viewModel.getPendingPaymentOfCandidate(candidateId[0]).
                    observe(getViewLifecycleOwner(), new Observer<PendingPayment>() {
                @Override
                public void onChanged(PendingPayment payment) {
                    if (payment != null) {
                        if (payment != null) {
                            issueDateTv.setText((CharSequence) payment.getIssueDate());
                            dueDateTv.setText((CharSequence) payment.getDueDate());
                            pendingAmountTv.setText(payment.getDueAmount());
                        }
                        else {
                            pendingAmountTv.setText("\u20b9 0");
                            pendingAmountTv.setTextColor(requireContext().getResources().getColor(R.color.primaryColor));
                            acceptBtn.setEnabled(false);
                        }
                    }
                }
            });
            acceptBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener positiveListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(dialog!=null){
                                dialog.dismiss();
                            }
                            int amount = Integer.parseInt(paidAmountInput.getText().toString());
                            viewModel.acceptPAyment(amount,candidateId[0]);
                            Toast.makeText(context,"Payment Accepted Successfully",Toast.LENGTH_LONG).show();
                            controller.popBackStack();
                        }
                    };

                    DialogInterface.OnClickListener negativeListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(dialog!=null){
                                dialog.dismiss();
                            }

                        }
                    };
                }
            });
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                DialogInterface.OnClickListener positiveListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(dialog!=null){
                            dialog.dismiss();
                        }
                        controller.popBackStack();
                    }
                };

                DialogInterface.OnClickListener negativeListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(dialog!=null){
                            dialog.dismiss();
                        }

                    }
                };
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(context).setTitle("Are you sure to cancel the Payment").
                            setPositiveButton("YES", positiveListener).setNegativeButton("NO",negativeListener)
                    .create().show();
                }

            });
        }
    }
}