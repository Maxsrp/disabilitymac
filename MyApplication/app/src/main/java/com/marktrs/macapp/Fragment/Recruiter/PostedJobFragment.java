package com.marktrs.macapp.Fragment.Recruiter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.marktrs.macapp.Model.Job;
import com.marktrs.macapp.R;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PostedJobFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;

    private FirebaseDatabase database;
    private DatabaseReference jobsRef;
    private ValueEventListener getAllJobListener;

    private ArrayList<Job> jobs;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private  RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PostedJobFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PostedJobFragment newInstance(int columnCount) {
        PostedJobFragment fragment = new PostedJobFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_postedjob_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            //TODO: Add posted job here
            jobs = new ArrayList<>();
            jobsRef = database.getReference("Jobs");
            mFirebaseUser = mFirebaseAuth.getCurrentUser();

            getAllJobListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot dtSnapshot : dataSnapshot.getChildren()) {
                        Job job = dtSnapshot.getValue(Job.class);
                        if (job.getOwnerUID().equals(mFirebaseUser.getUid())) {
                            Log.d("Jobs", job.getJobName());
                            jobs.add(job);
                        }
                    }
                    if(jobs.isEmpty()){
                        //TODO: show text 'You haven't posted any job'
                        Log.d("Jobs", "Empty List");
                    }else {
                        recyclerView.setAdapter(new MyPostedJobRecyclerViewAdapter(jobs));
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            jobsRef.addValueEventListener(getAllJobListener);


        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        jobsRef.removeEventListener(getAllJobListener);
    }

}
