package com.astro.vijay.unavuwithparse;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astro.vijay.unavuwithparse.Model.FoodItem;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by uttamavillain on 3/5/16.
 */
public class CookFoodListAdapter extends ParseQueryAdapter<FoodItem> {

    public CookFoodListAdapter(Context context) {
        super(context, new QueryFactory<FoodItem>() {
            public ParseQuery<FoodItem> create() {
                // Here we can configure a ParseQuery to display
                // only top-rated meals.
                ParseQuery query = new ParseQuery("FoodItem");
                return ParseQuery.getQuery(FoodItem.class).whereEqualTo("user", ParseUser.getCurrentUser());
            }
        });
        ParseQuery.getQuery(FoodItem.class).findInBackground(new FindCallback<FoodItem>() {
            public void done(List<FoodItem> itemList, ParseException e) {
                if (e == null) {
                    // Access the array of results here
                    for (FoodItem item : itemList)
                        Log.d("Vijayaraj", item.getName());
                } else {
                    Log.d("item", "Error: " + e.getMessage());
                }
            }
        });
    }

    @Override
    public View getItemView(FoodItem foodItem, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.item_food, null);
        }
        super.getItemView(foodItem, convertView, parent);


        ParseImageView ivFoodPic = (ParseImageView) convertView.findViewById(R.id.ivFoodPic);
        ParseFile photoFile = foodItem.getParseFile("photo");
        if (photoFile != null) {
            ivFoodPic.setParseFile(photoFile);
            ivFoodPic.loadInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    // nothing to do
                }
            });
        }        TextView tvFoodName = (TextView) convertView.findViewById(R.id.tvFoodName);
        tvFoodName.setText(foodItem.getName());

        return convertView;
    }
}
