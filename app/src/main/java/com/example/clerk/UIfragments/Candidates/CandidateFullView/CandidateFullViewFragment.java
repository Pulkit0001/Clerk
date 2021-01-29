package com.example.clerk.UIfragments.Candidates.CandidateFullView;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clerk.R;
import com.example.clerk.UIfragments.Groups.NewGroupFragments.ChooseChargesRecyclerViewAdapter;
import com.example.clerk.database.ReturnPOJOs.ChargeOfGroupListDataObj;
import com.example.clerk.database.databaseEntities.Candidate;
import com.example.clerk.database.databaseEntities.PendingPayment;
import com.example.clerk.database.databaseEntities.StudentContactInfo;
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CandidateFullViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CandidateFullViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    int candidateID;
    TextView candidateNameTv;
    TextView guardianNameTv;
    TextView groupNameTv;
    TextView pendingAmountTv;
    TextView addressTv;
    TextView contactTv;
    TextView optionalContactTv;
    TextView emailIdTv;
    MaterialButton addMoreChargesBtn;
    ImageButton locationBtn;
    ImageButton contactBtn;
    ImageButton optionalContactBtn;
    ImageButton emailBtn;
    ImageView candidateImage;
    Context context;
    NavController controller;
    CandidateFullView viewModel;
    RecyclerView chargeList;
    TextView joiningDateTv;
    TextView issueDateTv;
    TextView dueDateTv;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CandidateFullViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CandidateFullViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CandidateFullViewFragment newInstance(String param1, String param2) {
        CandidateFullViewFragment fragment = new CandidateFullViewFragment();
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
            candidateID = getArguments().getInt("CANDIDATE_ID");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_candidate_full_view2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = this.getContext();
        controller = Navigation.findNavController(view);
        viewModel = new ViewModelProvider(this).get(CandidateFullView.class);
        candidateImage = view.findViewById(R.id.candidate_full_view_image);
        candidateNameTv = view.findViewById(R.id.candidate_full_view_candidate_name);
        guardianNameTv = view.findViewById(R.id.candidate_full_view_guardian_name);
        groupNameTv = view.findViewById(R.id.candidate_full_view_group_name);
        pendingAmountTv = view.findViewById(R.id.candidate_full_view_pending_amount_tv);
        addMoreChargesBtn = view.findViewById(R.id.candidate_full_view_add_more_btn);
        chargeList = view.findViewById(R.id.candidate_full_view_charges_list_view);
        addressTv  = view.findViewById(R.id.candidate_full_view_address_tv);
        locationBtn = view.findViewById(R.id.candidate_full_view_address_icon);
        contactTv = view.findViewById(R.id.candidate_full_view_contact_tv);
        contactBtn = view.findViewById(R.id.candidate_full_view_phone_icon);
        optionalContactBtn = view.findViewById(R.id.candidate_full_view_optional_phone_icon);
        optionalContactTv = view.findViewById(R.id.candidate_full_view_optional_contact_tv);
        optionalContactBtn  = view.findViewById(R.id.candidate_full_view_optional_phone_icon);
        emailIdTv = view.findViewById(R.id.candidate_full_view_mail_tv);
        emailBtn = view.findViewById(R.id.candidate_full_view_mail_icon);
        joiningDateTv = view.findViewById(R.id.candidate_full_view_joining_date);
        issueDateTv = view.findViewById(R.id.candidate_full_view_issue_date_tv);
        dueDateTv = view.findViewById(R.id.candidate_full_view_due_date_tv);
        viewModel.getCandidateDetails(candidateID).observe(getViewLifecycleOwner(), new Observer<Candidate>() {
            @Override
            public void onChanged(Candidate candidate) {
                String data = candidate.getName()+", "+candidate.getAge();
                candidateNameTv.setText(data);
                guardianNameTv.setText(candidate.getGuardianName());
                data = "Group: "+candidate.getGroupName();
                groupNameTv.setText(data);
                StudentContactInfo contactInfo = candidate.getContactInfo();
                addressTv.setText(contactInfo.getAddress());
                contactTv.setText(contactInfo.getMobile1());
                optionalContactTv.setText(contactInfo.getMobile2());
                emailIdTv.setText(contactInfo.getEmail());
                data = "Joined On: "+candidate.getJoiningDate();
                joiningDateTv.setText(data);
            }
        });
        viewModel.getPendingPayment(candidateID).observe(getViewLifecycleOwner(), new Observer<PendingPayment>() {
            @Override
            public void onChanged(PendingPayment payment) {
                String data;
                if(payment!=null){
                    data = "Last Issued On: "+payment.getIssueDate();
                    issueDateTv.setText(data);
                    data = "Due Date On: "+payment.getDueDate();
                    dueDateTv.setText(data);
                    data = "\u20b9 "+payment.getDueAmount();
                    pendingAmountTv.setText(data);
                }
                else{
                    data = "/u20b9 0";
                    pendingAmountTv.setText(data);
                    pendingAmountTv.setTextColor(context.getResources().getColor(R.color.primaryColor));
                    issueDateTv.setVisibility(View.INVISIBLE);
                    dueDateTv.setVisibility(View.INVISIBLE);
                }
            }
        });
        final ChooseChargesRecyclerViewAdapter adapter = new ChooseChargesRecyclerViewAdapter();
        chargeList.setAdapter(adapter);
        chargeList.setLayoutManager(new LinearLayoutManager(context));

        viewModel.getChargesOfCandidate(candidateID).observe(getViewLifecycleOwner(), new Observer<List<ChargeOfGroupListDataObj>>() {
            @Override
            public void onChanged(List<ChargeOfGroupListDataObj> charges) {

                adapter.submitData(charges);
            }
        });
        addMoreChargesBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putInt("CANDIDATE_ID", candidateID);
                b.putString("MESSAGE", "Extra");
                controller.navigate(R.id.action_candidateFullViewFragment_to_apply_more_charges, b);
            }
        });
    }
}