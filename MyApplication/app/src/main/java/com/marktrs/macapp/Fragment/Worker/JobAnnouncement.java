package com.marktrs.macapp.Fragment.Worker;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marktrs.macapp.Model.Job;
import com.marktrs.macapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobAnnouncement extends Fragment {

    private Job job;
    private static final String JOB_KEY = "Job_key";

    private TextView job_text;

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

        return inflater.inflate(R.layout.fragment_job_announcement, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        job_text = (TextView) view.findViewById(R.id.job_text);
        job_text.setText(job.getJobName());
    }
}
