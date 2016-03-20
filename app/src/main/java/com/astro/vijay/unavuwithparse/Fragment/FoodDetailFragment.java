package com.astro.vijay.unavuwithparse.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.astro.vijay.unavuwithparse.Activity.FoodDetailActivity;
import com.astro.vijay.unavuwithparse.Model.FoodItem;
import com.astro.vijay.unavuwithparse.R;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by uttamavillain on 3/13/16.
 */
public class FoodDetailFragment extends Fragment{
    private static final String TAG = FoodDetailActivity.class.getName();
    @Bind(R.id.tvName) TextView tvName;
    @Bind(R.id.ivFoodPic) ParseImageView ivFoodPic;
    @Bind(R.id.tvdescription) TextView tvDescription;
    @Bind(R.id.btorderthis) Button btOrder;

    public FoodDetailFragment() {

    }

    public static FoodDetailFragment newInstance(String objectId) {

        Bundle args = new Bundle();
        args.putString("objectid", objectId);
        FoodDetailFragment fragment = new FoodDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_food_detail, parent, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String objectid = getArguments().getString("objectid");
        ParseQuery<FoodItem> query = ParseQuery.getQuery(FoodItem.class);
// First try to find from the cache and only then go to network
        //query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK); // or CACHE_ONLY
// Execute the query to find the object with ID
        query.getInBackground(objectid, new GetCallback<FoodItem>() {
            public void done(FoodItem foodItem, ParseException e) {
                if (e == null) {
                    tvName.setText(foodItem.getName());
                    tvDescription.setText(foodItem.getDescription());
                    ParseFile photoFile = foodItem.getPhotoFile();
                    if (photoFile != null) {
                        ivFoodPic.setParseFile(photoFile);
                        ivFoodPic.loadInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                // nothing to do
                            }
                        });
                    }
                }
            }
        });
    }
}
