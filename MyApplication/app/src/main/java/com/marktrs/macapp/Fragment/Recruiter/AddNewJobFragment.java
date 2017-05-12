package com.marktrs.macapp.Fragment.Recruiter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.marktrs.macapp.Fragment.WorkerSetUpFragment;
import com.marktrs.macapp.Model.Job;
import com.marktrs.macapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNewJobFragment extends Fragment {


    private Button confirmButton;
    private EditText jobNameET;
    private EditText symptomTypeET;
    private EditText requiredEducationET;
    private EditText requiredSkillET;
    private EditText workplaceET;
    private EditText paymentET;

    private DatabaseReference mDatabase;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    public AddNewJobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        return inflater.inflate(R.layout.fragment_add_new_job, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        jobNameET = (EditText) view.findViewById(R.id.job_name);
        symptomTypeET = (EditText) view.findViewById(R.id .job_symptoms);
        requiredEducationET = (EditText) view.findViewById(R.id.job_education);
        requiredSkillET = (EditText) view.findViewById(R.id.job_skill);
        workplaceET = (EditText) view.findViewById(R.id.job_workplace);
        paymentET = (EditText) view.findViewById(R.id.job_payment);

        confirmButton = (Button) view.findViewById(R.id.job_submit_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addJobToFirebase();
                PostedJobFragment postedJobFragment = new PostedJobFragment();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.remove(AddNewJobFragment.this);
                transaction.replace(R.id.fragment_area, postedJobFragment);
                transaction.commit();
                Toast.makeText(getContext(), "Successful !",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addJobToFirebase() {
        String key = mDatabase.child("Jobs").push().getKey();

        Job job = new Job();
        job.setJobId(key);
        job.setJobName(jobNameET.getText().toString());
        job.setSymptomType(symptomTypeET.getText().toString());
        job.setRequiredEducation(requiredEducationET.getText().toString());
        job.setRequiredSkill(requiredSkillET.getText().toString());
        job.setWorkplace(workplaceET.getText().toString());
        job.setPayment(paymentET.getText().toString());
        job.setOwnerUID(mFirebaseUser.getUid());

        mDatabase.child("Jobs").child(key).setValue(job);
    }
}
