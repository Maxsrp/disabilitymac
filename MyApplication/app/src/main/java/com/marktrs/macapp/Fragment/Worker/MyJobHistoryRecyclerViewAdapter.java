package com.marktrs.macapp.Fragment.Worker;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marktrs.macapp.Fragment.Worker.JobHistoryFragment.OnListFragmentInteractionListener;
import com.marktrs.macapp.Model.Job;
import com.marktrs.macapp.Model.JobApplication;
import com.marktrs.macapp.R;

import java.util.ArrayList;

public class MyJobHistoryRecyclerViewAdapter extends RecyclerView.Adapter<MyJobHistoryRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<JobApplication> mValues;
    private final OnListFragmentInteractionListener mListener;

    private final String ACCEPTED = "Accepted";
    private final String DENIED = "Denied";
    private final String WAITING = "waiting";

    public MyJobHistoryRecyclerViewAdapter(ArrayList<JobApplication> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_jobhistory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.jobName.setText(mValues.get(position).getJobName());
        holder.jobLocation.setText(mValues.get(position).getJobLocation());
        holder.applicationStatus.setText(mValues.get(position).getStatus());
        if (mValues.get(position).getStatus().equals(WAITING)){
            holder.applicationStatus.setTextColor(Color.parseColor("#FFC107"));
        }else if (mValues.get(position).getStatus().equals(ACCEPTED)){
            holder.applicationStatus.setTextColor(Color.parseColor("#4caf50"));
        }else {
            holder.applicationStatus.setTextColor(Color.parseColor("#E91E63"));
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentClick(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView jobName;
        public final TextView jobLocation;
        public final TextView applicationStatus;
        public JobApplication mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            jobName = (TextView) view.findViewById(R.id.job_name);
            jobLocation = (TextView) view.findViewById(R.id.job_location);
            applicationStatus = (TextView) view.findViewById(R.id.application_status);
        }
    }
}
