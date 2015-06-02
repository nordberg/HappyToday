package com.nordberg.android.happytoday;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class HappyMomentActivity extends ActionBarActivity {

    private static final String LOG_TAG = HappyMomentActivity.class.getSimpleName();
    public static final String HAPPY_DESC_TEXT = "com.nordberg.android.HAPPYDESCTEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_moment);

    }

    /**
     * Method called when pressing the "Add Moment!" button
     * @param view
     */
    public void addHappyMoment(View view) {
        Intent intent = new Intent(this, HappyFeedActivity.class);
        EditText happyDesc = (EditText) findViewById(R.id.happy_moment_edittext);
        String message = happyDesc.getText().toString();
        intent.putExtra(HAPPY_DESC_TEXT, message);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_happy_moment, menu);
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
