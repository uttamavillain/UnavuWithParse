package com.astro.vijay.unavuwithparse.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.astro.vijay.unavuwithparse.Activity.FoodDetailActivity;
import com.astro.vijay.unavuwithparse.FoodListAdapter;
import com.astro.vijay.unavuwithparse.R;

/**
 * Created by uttamavillain on 3/5/16.
 */
public class FoodTimeLineFragment extends Fragment {

    public static final String TAG = FoodTimeLineFragment.class.getName();
    private ListView lvFoodList;
    private FoodListAdapter foodListAdapter;

    public FoodTimeLineFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.d(TAG, "OnCreateView");
        View view = inflater.inflate(R.layout.fragment_food_list, parent, false);
        lvFoodList = (ListView) view.findViewById(R.id.lvFoodList);
        lvFoodList.setAdapter(foodListAdapter);
        lvFoodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), FoodDetailActivity.class);
                intent.putExtra("objectid",foodListAdapter.getItem(position).getObjectId());
                startActivity(intent);
                Log.d(TAG, "Starting intent: "+intent);
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        foodListAdapter = new FoodListAdapter(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
