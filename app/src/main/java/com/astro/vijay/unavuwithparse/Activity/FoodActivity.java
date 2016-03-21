package com.astro.vijay.unavuwithparse.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.astro.vijay.unavuwithparse.Fragment.FoodTimeLineFragment;
import com.astro.vijay.unavuwithparse.R;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import butterknife.ButterKnife;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        introPage();
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

    private void introPage() {
        if (ParseUser.getCurrentUser() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, 1);
        } else {
            //ParseApplication.parseApplication.initFoodItems();
            Log.d("Vijayaraj intropage",ParseUser.getCurrentUser().getUsername());
            setTitle(ParseUser.getCurrentUser().getUsername());
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().add(R.id.flFoodDisplay, new FoodTimeLineFragment()).commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            Log.d("Vijayaraj", "Inside activityresult");
        }
    }

    public void onClickBeCook(MenuItem item) {
        Intent intent = new Intent(this, CookActivity.class);
        startActivity(intent);
    }

    public void onClicklogOut(MenuItem item) {
        final ProgressDialog progressDialog = new ProgressDialog(this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Logging out...");
        progressDialog.show();
        ParseUser.getCurrentUser().logOutInBackground(new LogOutCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    startActivity(new Intent(FoodActivity.this, LoginActivity.class));
                }
                progressDialog.dismiss();
            }
        });
    }
}
