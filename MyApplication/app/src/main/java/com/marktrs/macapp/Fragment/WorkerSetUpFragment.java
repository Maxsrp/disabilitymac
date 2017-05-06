package com.marktrs.macapp.Fragment;


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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.marktrs.macapp.LoginActivity;
import com.marktrs.macapp.Model.User;
import com.marktrs.macapp.Model.Worker;
import com.marktrs.macapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkerSetUpFragment extends Fragment {

    private static final String USER_KEY = "User_key";

    private Button confirmButton;
    private EditText symptomET;
    private EditText educationET;
    private EditText skillET;

    public User user;

    private DatabaseReference mDatabase;

    public WorkerSetUpFragment() {
        // Required empty public constructor
    }

    public static WorkerSetUpFragment newInstance(User user) {
        WorkerSetUpFragment fragment = new WorkerSetUpFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER_KEY, user);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.user = (User) getArguments().getSerializable(USER_KEY);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return inflater.inflate(R.layout.fragment_worker_set_up, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        confirmButton = (Button) view.findViewById(R.id.worker_submit_button);
        symptomET = (EditText) view.findViewById(R.id.symptom);
        educationET = (EditText) view.findViewById(R.id.education);
        skillET = (EditText) view.findViewById(R.id.skill);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProfileToFirebase();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.remove(WorkerSetUpFragment.this);
                transaction.commit();
                Toast.makeText(getContext(), "Successful !",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addProfileToFirebase() {
        Worker workerProfile = new Worker(
                symptomET.getText().toString()
                , educationET.getText().toString()
                , skillET.getText().toString());
        this.user.setWorker(workerProfile);
        mDatabase.child("User").child(user.getuID()).setValue(this.user);
    }
}
