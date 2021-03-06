package com.marktrs.macapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.view.menu.MenuView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.marktrs.macapp.Fragment.ProfileSetUpFragment;
import com.marktrs.macapp.Fragment.Recruiter.AddNewJobFragment;
import com.marktrs.macapp.Fragment.Recruiter.JobApplicationAchiveFragment;
import com.marktrs.macapp.Fragment.Recruiter.PostedJobFragment;
import com.marktrs.macapp.Fragment.Recruiter.dummy.DummyContent;
import com.marktrs.macapp.Fragment.RecruiterSetUpFragment;
import com.marktrs.macapp.Fragment.Worker.AllJobFragment;
import com.marktrs.macapp.Fragment.Worker.JobAnnouncement;
import com.marktrs.macapp.Fragment.Worker.JobHistoryFragment;
import com.marktrs.macapp.Model.Job;
import com.marktrs.macapp.Model.JobApplication;
import com.marktrs.macapp.Model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener,
        AllJobFragment.OnListFragmentInteractionListener,
        JobHistoryFragment.OnListFragmentInteractionListener,
        PostedJobFragment.OnPostedFragmentInteractionListener,
        JobApplicationAchiveFragment.OnListFragmentInteractionListener{

    private static final String TAG = "MainActivity";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseDatabase database;

    private DatabaseReference userRef;
    private ValueEventListener getUserProfile;

    private DatabaseReference applicationRef;

    private View mProgressView;
    private View mMainFragmentView;

    private FloatingActionButton fab;
    private NavigationView navigationView;
    private TextView name;
    private TextView noContentText;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mMainFragmentView = findViewById(R.id.progress_bar_area);
        mProgressView = findViewById(R.id.main_progress);

        showProgress(true);

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This feature is not available yet", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nameTextView);
        TextView email = (TextView) navigationView.getHeaderView(0).findViewById(R.id.emailTextView);
        noContentText = (TextView) findViewById(R.id.no_content);
        email.setText(mFirebaseUser.getEmail());

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("User").child(mFirebaseUser.getUid());

        getUserProfile = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0) {
                    User user = dataSnapshot.getValue(User.class);
                    if (user.getRecruiter() != null) {
                        name.setText(user.getFirstName() + " " +user.getLastName() + " (Recruiter)");
                        UpdateUI("postedJob");
                    } else {
                        name.setText(user.getFirstName() + " " +user.getLastName());
                        UpdateUI("jobList");
                    }
                } else {
                    UpdateUI("setupProfile");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        userRef.addListenerForSingleValueEvent(getUserProfile);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_job) {
            AllJobFragment allJobFragment = new AllJobFragment();
            transaction.replace(R.id.fragment_area, allJobFragment);
            transaction.commit();
        } else if (id == R.id.nav_posted_job) {
            PostedJobFragment postedJobFragment = new PostedJobFragment();
            transaction.replace(R.id.fragment_area, postedJobFragment);
            transaction.commit();
        } else if (id == R.id.nav_history) {
            JobHistoryFragment jobHistoryFragment = new JobHistoryFragment();
            transaction.replace(R.id.fragment_area, jobHistoryFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_sign_out) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void UpdateUI(String route) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if (route.equals("setupProfile")) {
            User user = new User();
            user.setuID(mFirebaseUser.getUid());
            fab.setVisibility(View.GONE);
            navigationView.getMenu().getItem(0).setVisible(false);
            navigationView.getMenu().getItem(1).setVisible(false);
            navigationView.getMenu().getItem(2).setVisible(false);
            navigationView.getMenu().getItem(3).setVisible(false);
            ProfileSetUpFragment profileSetUpFragment = new ProfileSetUpFragment().newInstance(user);
            transaction.replace(R.id.fragment_area, profileSetUpFragment);

        } else if (route.equals("postedJob")) {
            navigationView.getMenu().getItem(2).setVisible(false);
            navigationView.getMenu().getItem(0).setVisible(false);
            setRecruiterNavSideBar();
            PostedJobFragment postedJobFragment = new PostedJobFragment();
            transaction.replace(R.id.fragment_area, postedJobFragment);

        } else if (route.equals("jobList")) {
            navigationView.getMenu().getItem(1).setVisible(false);
            AllJobFragment allJobFragment = new AllJobFragment();
            transaction.replace(R.id.fragment_area, allJobFragment);
        }
        transaction.commit();
        userRef.removeEventListener(getUserProfile);
        showProgress(false);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mMainFragmentView.setVisibility(show ? View.GONE : View.VISIBLE);
            mMainFragmentView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mMainFragmentView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mMainFragmentView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onListFragmentInteraction(Job job) {
        JobAnnouncement jobAnnouncement = new JobAnnouncement().newInstance(job);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_area, jobAnnouncement);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onListFragmentClick(JobApplication item) {
        //TODO: remove or cancel this application
    }

    @Override
    public void onPressPostedJob(final Job job) {
        //TODO: send all application relate to this job to newInstance
        JobApplicationAchiveFragment allAppFragment = new JobApplicationAchiveFragment().newInstance(job);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_area, allAppFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void onListFragmentInteraction(JobApplication item) {
        applicationRef = database.getReference().child("JobApplications").child(item.getId());
        applicationRef.setValue(item);
    }

    public void setRecruiterNavSideBar() {
        navigationView.getMenu().getItem(1).setVisible(true);
        navigationView.getMenu().getItem(3).setVisible(true);
        fab.setVisibility(View.VISIBLE);
        fab.setImageResource(R.drawable.ic_action_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Adding new job",
                        Toast.LENGTH_SHORT).show();
                AddNewJobFragment addNewJobFragment = new AddNewJobFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_area, addNewJobFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                noContentText.setVisibility(View.GONE);
                fab.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void setWorkerNavSideBar(){
        navigationView.getMenu().getItem(2).setVisible(true);
        navigationView.getMenu().getItem(0).setVisible(true);
        navigationView.getMenu().getItem(3).setVisible(true);
        fab.setVisibility(View.VISIBLE);
    }
}
