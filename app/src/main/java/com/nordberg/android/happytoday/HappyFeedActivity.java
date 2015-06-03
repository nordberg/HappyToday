package com.nordberg.android.happytoday;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;


public class HappyFeedActivity extends AppCompatActivity {

    private static final String LOG_TAG = HappyFeedActivity.class.getSimpleName();
    private static final int HAPPY_REQUEST_CODE = 1337;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    HappyDbHelper mDbHelper;

    private ArrayList<HappyMoment> happyDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_feed);

        mRecyclerView = (RecyclerView) findViewById(R.id.happy_recycler_view);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mDbHelper = new HappyDbHelper(this.getApplicationContext());

        happyDataset = new ArrayList<HappyMoment>();

        mAdapter = new HappyAdapter(happyDataset);
        mRecyclerView.setAdapter(mAdapter);

        loadMoments();
    }

    /**
     * Load happy moment entries from database to the dataset to be displayed.
     */
    private void loadMoments() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] proj = {
                HappyDatabaseContract.HappyEntry.COLUMN_NAME_DESCRIPTION,
                HappyDatabaseContract.HappyEntry.COLUMN_NAME_DATE
        };

        String sortOrder =
                HappyDatabaseContract.HappyEntry.COLUMN_NAME_DATE + " ASC";

        Cursor c = db.query(
                HappyDatabaseContract.HappyEntry.TABLE_NAME,
                proj,
                null,
                null,
                null,
                null,
                sortOrder
        );

        if (c.moveToFirst()) {
            String desc = c.getString(0);
            String date = c.getString(1);

            happyDataset.add(0, new HappyMoment(desc, date));
        }

        while (c.moveToNext()) {
            String desc = c.getString(c.getColumnIndex(HappyDatabaseContract
                    .HappyEntry.COLUMN_NAME_DESCRIPTION));
            String date = c.getString(c.getColumnIndex(HappyDatabaseContract
                    .HappyEntry.COLUMN_NAME_DATE));

            happyDataset.add(0, new HappyMoment(desc, date));
        }

        mAdapter.notifyDataSetChanged();
    }

    /**
     * Method called when pressing the FAB.
     * @param view
     */
    public void addHappyMoment(View view) {
        Intent intent = new Intent(this, HappyMomentActivity.class);
        startActivityForResult(intent, HAPPY_REQUEST_CODE);
    }

    /**
     * If an activity returns with a result this method will check what result was received.
     * If the request code equals HAPPY_REQUEST_CODE it means that the user wants to add a happy
     * moment.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case (HAPPY_REQUEST_CODE) : {
                if (resultCode == Activity.RESULT_OK) {
                    String desc = data.getStringExtra(HappyMomentActivity.HAPPY_DESC_TEXT);
                    // Add it at index 0 so that it appears at the top of the list
                    HappyMoment hm = new HappyMoment(desc);

                    SQLiteDatabase db = mDbHelper.getWritableDatabase();

                    // Insert values into database
                    ContentValues values = new ContentValues();
                    values.put(HappyDatabaseContract.HappyEntry.COLUMN_NAME_DESCRIPTION,
                            hm.getDesc());
                    values.put(HappyDatabaseContract.HappyEntry.COLUMN_NAME_DATE,
                            hm.getDateString());

                    long newRowId;

                    newRowId = db.insert(
                            HappyDatabaseContract.HappyEntry.TABLE_NAME,
                            null,
                            values);

                    happyDataset.add(0, hm);
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
