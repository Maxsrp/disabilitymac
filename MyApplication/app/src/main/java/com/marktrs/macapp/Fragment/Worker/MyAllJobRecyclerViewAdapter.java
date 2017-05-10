package com.marktrs.macapp.Fragment.Worker;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marktrs.macapp.Fragment.Recruiter.AddNewJobFragment;
import com.marktrs.macapp.Fragment.Worker.AllJobFragment.OnListFragmentInteractionListener;
import com.marktrs.macapp.Fragment.Worker.dummy.DummyContent.DummyItem;
import com.marktrs.macapp.Fragment.WorkerSetUpFragment;
import com.marktrs.macapp.Model.Job;
import com.marktrs.macapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyAllJobRecyclerViewAdapter extends RecyclerView.Adapter<MyAllJobRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Job> jobs;
    private final AllJobFragment.OnListFragmentInteractionListener mListener;

    public MyAllJobRecyclerViewAdapter(ArrayList<Job> jobs, AllJobFragment.OnListFragmentInteractionListener listener) {
        this.jobs = jobs;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_alljob, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = jobs.get(position);
        holder.mIdView.setText(jobs.get(position).getJobName());
        holder.mContentView.setText(jobs.get(position).getWorkplace());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onListFragmentInteraction(holder.mItem);
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
        public Job mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
