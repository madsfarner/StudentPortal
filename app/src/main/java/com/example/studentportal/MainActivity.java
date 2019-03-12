package com.example.studentportal;

import android.content.Intent;
import android.gesture.Gesture;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemTouchListener {

    private List<PortalObject> mPortalObjects;
    private PortalObjectAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private GestureDetector mGestureDetector;
    public final static String EXTRATEXT_NAME = "extratextName";
    public final static String EXTRATEXT_URL = "extratextUrl";
    public final static int REQUESTCODE = 1234;
    public final static int GRIDCOLOUMS = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.recyclerView);
        mPortalObjects = new ArrayList<>();

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, GRIDCOLOUMS));

        // Floating button for adding a new portal, opens a new activity for result.
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewPortalActivity.class);
                startActivityForResult(intent, REQUESTCODE);
            }
        });
        mRecyclerView.addOnItemTouchListener(this);
        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        //Hardcoded examples of portals
        mPortalObjects.add(new PortalObject("Min side", "https://startsiden.no"));
        mPortalObjects.add(new PortalObject("Vrk", "https://nrk.no"));
        mPortalObjects.add(new PortalObject("Aftenposten", "https://aftenposten.no"));
        mPortalObjects.add(new PortalObject("VG", "https://vg.no"));
        mPortalObjects.add(new PortalObject("Fronter", "http://fronter.no"));
        updateUI();

    }

    // When item in recyclerView is clicked, finds which view, and opens new activity with URL in intent.
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        int mAdapterPosition = rv.getChildAdapterPosition(child);
        if(child != null && mGestureDetector.onTouchEvent(e)) {
            Intent intent = new Intent(MainActivity.this, PortalWebActivity.class);
            intent.putExtra(EXTRATEXT_URL, mPortalObjects.get(mAdapterPosition).getmPortalUrl());
            startActivity(intent);
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateUI(){
        if (mAdapter == null) {
            mAdapter = new PortalObjectAdapter(mPortalObjects);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    //When returned from new Portal screen, adds new portal if resultCode is approved
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if(requestCode == REQUESTCODE){
            if(resultCode == RESULT_OK) {
                mPortalObjects.add(new PortalObject(data.getStringExtra(MainActivity.EXTRATEXT_NAME), data.getStringExtra(MainActivity.EXTRATEXT_URL)));
                updateUI();
            }
        }
    }
}
