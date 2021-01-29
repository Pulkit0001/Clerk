package com.example.clerk.UIfragments.Candidates.NewCandidateFragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.clerk.R;
import com.example.clerk.database.databaseEntities.Candidate;
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private NewCandidateViewModel viewModel;
    MaterialButton nextBtn;
    Calendar calendar = Calendar.getInstance();
    EditText DOBInput;
    EditText NameInput;
    EditText guardianNameInput;
    Context context;
    NavController controller;
    EditText joiningDateInput;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public PersonalDetailsFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static PersonalDetailsFragment newInstance(String param1, String param2) {
        PersonalDetailsFragment fragment = new PersonalDetailsFragment();
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
        return inflater.inflate(R.layout.fragment_personal_details, container, false);
    }

    void updateDobLabel()
    {
        String myFormat = "DD/MM/YYYY"; //In which you need put here
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year =  calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        String date = day+"/"+month+"/"+year;
        DOBInput.setText(date);
    }

    void updateJoiningDateLabel()
    {
        String myFormat = "DD/MM/YYYY"; //In which you need put here
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year =  calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        String date = day+"/"+month+"/"+year;
        joiningDateInput.setText(date);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        controller = Navigation.findNavController(view);
        context = this.getContext();
        viewModel = new ViewModelProvider(controller.getViewModelStoreOwner(R.id.navigation2)).
                    get(NewCandidateViewModel.class);
        nextBtn = view.findViewById(R.id.cand_persona_details_nxt_btn);
        NameInput = view.findViewById(R.id.name_input);
        guardianNameInput = view.findViewById(R.id.guardian_name_input);
        DOBInput = view.findViewById(R.id.date_of_birth_input);
        joiningDateInput = view.findViewById(R.id.joining_date_input);
        final TextView error = view.findViewById(R.id.personal_details_error_tv);
        error.setVisibility(View.INVISIBLE);


        final AutoCompleteTextView GenderInput = view.findViewById(R.id.gender_input);
        String[] genders = {"Male", "Female", "Others"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,R.layout.drop_down_menu_item,R.id.gender_dropdown_tv,genders);
        GenderInput.setAdapter(adapter);


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateDobLabel();
            }
        };

        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateJoiningDateLabel();
            }
        };


        DOBInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DatePickerDialog datePickerDialog = new DatePickerDialog(context,date,calendar.
                            get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.
                            get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.show();
                }
            }
        });

        DOBInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context,date,calendar.
                        get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.
                        get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        joiningDateInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DatePickerDialog datePickerDialog = new DatePickerDialog(context,date2,calendar.
                            get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.
                            get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.show();
                }
            }
        });
        joiningDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context,date2,calendar.
                        get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.
                        get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = NameInput.getText().toString();
                String guardianName = guardianNameInput.getText().toString();
                String dateOfBirth = DOBInput.getText().toString();
                String gender = GenderInput.getText().toString();
                String joiningDate = joiningDateInput.getText().toString();
                if (name.length() != 0 && guardianName.length()!=0 && gender.length()!= 0 && dateOfBirth.length()!= 0 && joiningDate.length()!= 0) {
                    error.setVisibility(View.INVISIBLE);
                    viewModel.addStudent(name,guardianName,dateOfBirth,gender,joiningDate);
                    Navigation.findNavController(v).navigate(R.id.action_personalDetailsFragment_to_contactDetailsFragment);


                }
                else {
                    error.setVisibility(View.VISIBLE);
                }

            }
        });




    }


}