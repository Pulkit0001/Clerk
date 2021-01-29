package com.example.clerk.UIfragments.Groups.NewGroupFragments;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.clerk.R;
import com.example.clerk.database.databaseEntities.Group;
import com.google.android.material.button.MaterialButton;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GroupDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupDetailsFragment extends Fragment {

    NewGroupViewModel viewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GroupDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GroupDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GroupDetailsFragment newInstance(String param1, String param2) {
        GroupDetailsFragment fragment = new GroupDetailsFragment();
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

        return inflater.inflate(R.layout.fragment_group_details, container, false);


    }

    private String formatTime(int hour, int min){
        String session;
        if(hour>12){
            session = "PM";
            hour=hour-12;
        }
        else {
            session = "AM";
        }
        String time;
        if(min>9)
         time = hour+":"+min+" "+session;
        else
            time = hour+":0"+min+" "+session;
        return time;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController controller =  Navigation.findNavController(view);
        viewModel = new ViewModelProvider(controller.getViewModelStoreOwner(R.id.navigation3)).get(NewGroupViewModel.class);
        MaterialButton nextBtn = view.findViewById(R.id.group_details_next_btn);

        final EditText grpNameInput = view.findViewById(R.id.grp_name_input);
        final EditText startTimeInput = view.findViewById(R.id.start_time_input);
        final EditText endTimeInput =  view.findViewById(R.id.end_time_input);
        final TextView error = view.findViewById(R.id.group_details_error_tv);
        error.setVisibility(View.INVISIBLE);
        final Context context = this.getContext();

        final TimePickerDialog.OnTimeSetListener listener1 = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                startTimeInput.setText(formatTime(hourOfDay,minute));
            }
        };

        final TimePickerDialog.OnTimeSetListener listener2 = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                endTimeInput.setText(formatTime(hourOfDay,minute));
            }
        };

        startTimeInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Calendar calendar = Calendar.getInstance();
                    TimePickerDialog timePickerDialog = new TimePickerDialog(context,listener1,calendar.
                            get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false);
                    timePickerDialog.show();
                }
            }
        });

        startTimeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                TimePickerDialog timePickerDialog = new TimePickerDialog(context,listener1,calendar.
                        get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false);
                timePickerDialog.show();
            }
        });

        endTimeInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Calendar calendar = Calendar.getInstance();
                    TimePickerDialog timePickerDialog = new TimePickerDialog(context,listener2,calendar.
                            get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false);
                    timePickerDialog.show();
                }
            }
        });

        endTimeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                TimePickerDialog timePickerDialog = new TimePickerDialog(context,listener2,calendar.
                        get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false);
                timePickerDialog.show();
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String grpName = grpNameInput.getText().toString();
                String startTime = startTimeInput.getText().toString();
                String endTime = endTimeInput.getText().toString();

                if(grpName.length()!=0&&startTime.length()!=0&&endTime.length()!=0){
                    final Group grp = new Group();
                    grp.setGroupName(grpName);
                    grp.setStartTime(startTime);
                    grp.setEndTime(endTime);
                    viewModel.insertGroup(grp);
                    controller.navigate(R.id.action_group_details_to_apply_charges_fragment);
                }
                else{
                    error.setVisibility(View.VISIBLE);
                }


            }
        });

    }
}