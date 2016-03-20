package com.astro.vijay.unavuwithparse.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.astro.vijay.unavuwithparse.Fragment.FoodDetailFragment;
import com.astro.vijay.unavuwithparse.R;

/**
 * Created by uttamavillain on 3/13/16.
 */
public class FoodDetailActivity extends AppCompatActivity {
    public static final String TAG = FoodDetailActivity.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_food_detail);
        FragmentManager fm = getSupportFragmentManager();
        if(fm.findFragmentById(R.id.fragment_container) == null) {
            Fragment fragment = FoodDetailFragment.newInstance(getIntent().getStringExtra("objectid"));
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
