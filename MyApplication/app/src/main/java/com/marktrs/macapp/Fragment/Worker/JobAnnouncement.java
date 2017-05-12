package com.marktrs.macapp.Fragment.Worker;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.marktrs.macapp.Fragment.Recruiter.AddNewJobFragment;
import com.marktrs.macapp.Fragment.Recruiter.PostedJobFragment;
import com.marktrs.macapp.LoginActivity;
import com.marktrs.macapp.MainActivity;
import com.marktrs.macapp.Model.Job;
import com.marktrs.macapp.Model.JobApplication;
import com.marktrs.macapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobAnnouncement extends Fragment {

    private Job job;
    private static final String JOB_KEY = "Job_key";

    private TextView job_Name;
    private TextView job_symptomType;
    private TextView job_workplace;

    private TextView job_requiredEducation;
    private TextView job_requiredSkill;
    private TextView job_payment;

    private Button apply_job_button;

    private DatabaseReference mDatabase;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    public static JobAnnouncement newInstance(Job job) {

        Bundle bundle = new Bundle();
        JobAnnouncement fragment = new JobAnnouncement();
        bundle.putSerializable(JOB_KEY, job);
        fragment.setArguments(bundle);
        return fragment;
    }

    public JobAnnouncement() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.job = (Job) getArguments().getSerializable(JOB_KEY);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        return inflater.inflate(R.layout.fragment_job_announcement, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        job_Name = (TextView) view.findViewById(R.id.job_name);
        job_symptomType = (TextView) view.findViewById(R.id.job_symptoms);
        job_workplace = (TextView) view.findViewById(R.id.job_location);
        job_requiredEducation = (TextView) view.findViewById(R.id.job_education);
        job_requiredSkill = (TextView) view.findViewById(R.id.job_skill);
        job_payment = (TextView) view.findViewById(R.id.job_payment);

        apply_job_button = (Button) view.findViewById(R.id.apply_job);

        job_Name.setText(job.getJobName());
        job_symptomType.setText(job.getSymptomType());
        job_workplace.setText(job.getWorkplace());
        job_requiredEducation.setText(job.getRequiredEducation());
        job_requiredSkill.setText(job.getRequiredSkill());
        job_payment.setText(job.getPayment());

        apply_job_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Your resume was sent.",
                        Toast.LENGTH_SHORT).show();
                applyJob();
            }
        });

    }

    private void applyJob() {
        String key = mDatabase.child("JobApplications").push().getKey();

        JobApplication jobApplication = new JobApplication();
        jobApplication.setId(key);
        jobApplication.setJobId(job.getJobId());
        jobApplication.setOwnerId(job.getOwnerUID());
        jobApplication.setWorkerId(mFirebaseUser.getUid());
        jobApplication.setStatus("waiting");
        jobApplication.setJobName(job.getJobName());
        jobApplication.setJobLocation(job.getWorkplace());
        jobApplication.setJobSymptom(job.getSymptomType());

        mDatabase.child("JobApplications").child(key).setValue(jobApplication);

        //show job history after apply job
        showJobHistory();

    }

    private void showJobHistory() {
        JobHistoryFragment jobHistoryFragment = new JobHistoryFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_area, jobHistoryFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
