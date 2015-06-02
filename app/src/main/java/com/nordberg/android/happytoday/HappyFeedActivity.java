package com.nordberg.android.happytoday;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;


public class HappyFeedActivity extends ActionBarActivity {

    private static final String LOG_TAG = HappyFeedActivity.class.getSimpleName();
    private static final int HAPPY_REQUEST_CODE = 1337;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<HappyMoment> happyDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_feed);

        mRecyclerView = (RecyclerView) findViewById(R.id.happy_recycler_view);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        happyDataset = new ArrayList<HappyMoment>();

        happyDataset.add(0, new HappyMoment("William köpte kanelbullar åt mig"));
        happyDataset.add(0, new HappyMoment("Jag lämnade in mitt kexjobb"));
        happyDataset.add(0, new HappyMoment("Jag skrev OS-tenta"));
        happyDataset.add(0, new HappyMoment("Jag var i Uppsala"));

        mAdapter = new MyAdapter(happyDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Method called when pressing the FAB.
     * @param view
     */
    public void addHappyMoment(View view) {
        Intent intent = new Intent(this, HappyMomentActivity.class);
        startActivityForResult(intent, HAPPY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case (HAPPY_REQUEST_CODE) : {
                if (resultCode == Activity.RESULT_OK) {
                    String desc = data.getStringExtra(HappyMomentActivity.HAPPY_DESC_TEXT);
                    happyDataset.add(0, new HappyMoment(desc));
                    mAdapter.notifyDataSetChanged();
                }
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_happy_feed, menu);
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
}
