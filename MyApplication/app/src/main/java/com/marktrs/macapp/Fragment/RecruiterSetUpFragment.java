package com.marktrs.macapp.Fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
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
import com.marktrs.macapp.Fragment.Recruiter.PostedJobFragment;
import com.marktrs.macapp.MainActivity;
import com.marktrs.macapp.Model.Recruiter;
import com.marktrs.macapp.Model.User;
import com.marktrs.macapp.Model.Worker;
import com.marktrs.macapp.R;
import com.marktrs.macapp.SignUpActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecruiterSetUpFragment extends Fragment {

    private static final String USER_KEY = "User_key";
    public User user;
    private DatabaseReference mDatabase;

    private Button confirmButton;
    private EditText companyNameET;
    private EditText companyLocationET;
    private EditText companyDescriptionET;

    public RecruiterSetUpFragment() {
        // Required empty public constructor
    }

    public static RecruiterSetUpFragment newInstance(User user) {
        RecruiterSetUpFragment fragment = new RecruiterSetUpFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER_KEY, user);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.user = (User) getArguments().getSerializable(USER_KEY);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return inflater.inflate(R.layout.fragment_recruiter_set_up, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        confirmButton = (Button) view.findViewById(R.id.recruiter_submit_button);
        companyNameET = (EditText) view.findViewById(R.id.company_name);
        companyLocationET = (EditText) view.findViewById(R.id.company_location);
        companyDescriptionET = (EditText) view.findViewById(R.id.company_description);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProfileToFirebase();
                Toast.makeText(getContext(), "Successful !",
                        Toast.LENGTH_LONG).show();
                ((MainActivity)getActivity()).setRecruiterNavSideBar();
                PostedJobFragment postedJobFragment = new PostedJobFragment();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_area, postedJobFragment);
                transaction.commit();
            }
        });
    }

    public void addProfileToFirebase() {
        Recruiter recruiter = new Recruiter(
                companyNameET.getText().toString()
                , companyLocationET.getText().toString()
                , companyDescriptionET.getText().toString());
        this.user.setRecruiter(recruiter);
        mDatabase.child("User").child(user.getuID()).setValue(this.user);
    }
}
