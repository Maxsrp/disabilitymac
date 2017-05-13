package com.marktrs.macapp.Fragment.Recruiter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.marktrs.macapp.Model.Job;
import com.marktrs.macapp.R;

import java.util.ArrayList;
import java.util.Map;

public class MyPostedJobRecyclerViewAdapter extends RecyclerView.Adapter<MyPostedJobRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Job> jobs;
    private Map<String, Integer> jobApplicationCount;
    private PostedJobFragment.OnPostedFragmentInteractionListener mListener;

    public MyPostedJobRecyclerViewAdapter(ArrayList<Job> jobs, Map<String, Integer> jobApplicationCount, PostedJobFragment.OnPostedFragmentInteractionListener listener) {
        this.jobs = jobs;
        this.jobApplicationCount = jobApplicationCount;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_postedjob, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = jobs.get(position);
        holder.mIdView.setText(jobs.get(position).getJobName());
        holder.mContentView.setText(jobs.get(position).getWorkplace());
        holder.jobSymptom.setText(jobs.get(position).getSymptomType());
        if(null != jobApplicationCount.get(jobs.get(position).getJobId())){
            holder.applicationCounter.setText(jobApplicationCount.get(jobs.get(position).getJobId()).toString());
        }else {
            holder.applicationCounter.setText("0");
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onPressPostedJob(holder.mItem);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView jobSymptom;
        public final TextView applicationCounter;
        public Job mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            jobSymptom = (TextView) view.findViewById(R.id.job_symptom);
            applicationCounter = (TextView) view.findViewById(R.id.application_count);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
