package com.example.clerk.UIfragments.Candidates;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clerk.R;
import com.example.clerk.database.AccountsRepository;
import com.example.clerk.database.ReturnPOJOs.CandidateListDataObj;
import com.example.clerk.database.ReturnPOJOs.ChooseGroupListDataObj;
import com.example.clerk.database.databaseEntities.Candidate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

import static androidx.core.content.ContextCompat.checkSelfPermission;


public class CandidatesFragment extends Fragment {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    CandidateViewModel viewModel;

    public CandidatesFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_candidates, container, false);



        //observe(getViewLifecycleOwner(), new Observer<List<CandidateListDataObj>>() {
         //   @Override
         //   public void onChanged(List<CandidateListDataObj> candidates) {
         //       }
        //});


//        view.findViewById(R.id.camera_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
//                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//                }
//
//            }
//        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(CandidateViewModel.class);
        final Context context = this.getContext();

        final RecyclerView candidateList = view.findViewById(R.id.candidate_list);
        final Spinner grpSpinner = view.findViewById(R.id.candidate_frag_grp_spinner);

        assert context != null;
        final CandidateViewModel.SpinnerAdapter adapter = new CandidateViewModel.
                SpinnerAdapter(context, android.R.layout.simple_spinner_dropdown_item);


        AccountsRepository.getGrps().observe(getViewLifecycleOwner(), new Observer<List<ChooseGroupListDataObj>>() {
             @Override
             public void onChanged(List<ChooseGroupListDataObj> chooseGroupListDataObjs) {

                 adapter.submitData(chooseGroupListDataObjs);
                 System.out.println(chooseGroupListDataObjs.size()+"...................................................");
                 grpSpinner.setAdapter(adapter);
                 grpSpinner.setSelection(0);
             }
         });




        grpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Spinner item selected at position"+position+"..................................");
                int grpId = adapter.getGroupId(position);
                System.out.println("and the Group ID i got is"+grpId+",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
                viewModel.getCandidatesOfGrp(grpId).observe(getViewLifecycleOwner(), new Observer<List<CandidateListDataObj>>() {
                    @Override
                    public void onChanged(List<CandidateListDataObj> candidateListDataObjs) {
                        System.out.println("And the lost of candidate contains "+candidateListDataObjs.size()+"elements .........");
                        candidateList.setAdapter(new CandidateRecyclerViewAdapter(candidateListDataObjs));
                        candidateList.setLayoutManager(new LinearLayoutManager(context));


                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        FloatingActionButton newCandidateBtn = view.findViewById(R.id.add_new_candidate_fab);
        newCandidateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_candidatesFragment_to_navigation2);
            }
        });
    }

    //    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK)
//        {
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            image.setImageBitmap(photo);
//        }
//    }
}