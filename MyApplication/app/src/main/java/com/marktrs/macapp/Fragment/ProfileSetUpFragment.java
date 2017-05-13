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
import android.widget.ImageButton;
import android.widget.Toast;

import com.marktrs.macapp.MainActivity;
import com.marktrs.macapp.Model.User;
import com.marktrs.macapp.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileSetUpFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileSetUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileSetUpFragment extends Fragment {

    private static final String USER_KEY = "User_key";
    private ImageButton workerButton;
    private ImageButton recruiterButton;

    private EditText firstName;
    private EditText lastName;

    public User user;

    public ProfileSetUpFragment() {
        // Required empty public constructor
    }

    public static ProfileSetUpFragment newInstance(User user) {
        ProfileSetUpFragment fragment = new ProfileSetUpFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER_KEY, user);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.user = (User) getArguments().getSerializable(USER_KEY);
        return inflater.inflate(R.layout.fragment_profile_set_up, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        workerButton = (ImageButton) view.findViewById(R.id.workerButton);
        recruiterButton = (ImageButton) view.findViewById(R.id.recruiterButton);
        firstName = (EditText) view.findViewById(R.id.firstName);
        lastName = (EditText) view.findViewById(R.id.lastName);

        workerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPressWorker();
            }
        });

        recruiterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPressRecruiter();
            }
        });
    }

    public void onPressRecruiter() {
        addUser(firstName.getText().toString(), lastName.getText().toString());
        RecruiterSetUpFragment recruiterSetUpFragment = new RecruiterSetUpFragment().newInstance(this.user);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_area, recruiterSetUpFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onPressWorker() {
        addUser(firstName.getText().toString(), lastName.getText().toString());
        WorkerSetUpFragment workerSetUpFragment = new WorkerSetUpFragment().newInstance(this.user);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_area, workerSetUpFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void addUser(String firstName, String lastName) {
        this.user.setFirstName(firstName);
        this.user.setLastName(lastName);
    }

}
