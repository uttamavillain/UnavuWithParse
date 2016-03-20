package com.astro.vijay.unavuwithparse;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.astro.vijay.unavuwithparse.Model.FoodItem;
import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;

/**
 * Created by uttamavillain on 3/6/16.
 */
public class ParseApplication extends Application {

    private static final String TAG = ParseApplication.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        //Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(FoodItem.class);
       Parse.initialize(new Parse.Configuration.Builder(this)
               .applicationId("testunavuappid") // should correspond to APP_ID env variable
               .clientKey(null)  // set explicitly unless clientKey is explicitly configured on Parse server
               .server("https://testunavu.herokuapp.com/parse/").build());
        parseApplication = this;
    }

    public static ParseApplication parseApplication;

    public ParseFile loadPhoto(int res) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), res);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] image = stream.toByteArray();
        return new ParseFile(image);
    }

    public void initFoodItems() {

        FoodItem foodItem1 = new FoodItem();
        foodItem1.setName("Dosa");
        foodItem1.setDescription("Dosa is a fermented crepe made from rice batter and black lentils. It is a staple dish in South Indian states of Tamil nadu, Andhra Pradesh, Karnataka, Kerala and Telangana");
        foodItem1.setDescription("South India");
        foodItem1.setUser(ParseUser.getCurrentUser());
        ParseFile file = loadPhoto(R.drawable.dosa);
        file.saveInBackground();
        foodItem1.setPhotoFile(file);
        foodItem1.saveInBackground();

        FoodItem foodItem2 = new FoodItem();
        foodItem2.setName("Idly");
        foodItem2.setDescription("Idly is a traditional breakfast in South Indian households. Idli is a savoury cake that is popular throughout India and neighbouring countries like Sri Lanka");
        foodItem2.setDescription("South India");
        foodItem2.setUser(ParseUser.getCurrentUser());
        file = loadPhoto(R.drawable.idlly);
        file.saveInBackground();
        foodItem2.setPhotoFile(file);
        foodItem2.saveInBackground();

        FoodItem foodItem3 = new FoodItem();
        foodItem3.setName("Piza");
        foodItem3.setDescription("Pizza is a flatbread generally topped with tomato sauce and cheese and baked in an oven. It is commonly topped with a selection of meats, vegetables and condiments.");
        foodItem3.setCusine("Italian");
        foodItem3.setUser(ParseUser.getCurrentUser());
        file = loadPhoto(R.drawable.piza);
        file.saveInBackground();
        foodItem3.setPhotoFile(file);
        foodItem3.saveInBackground();

        FoodItem foodItem4 = new FoodItem();
        foodItem4.setName("Pasta");
        foodItem4.setDescription("Pasta is a staple food of traditional Italian cuisine, with the first reference dating to 1154 in Sicily. It is also commonly used to refer to the variety of pasta dishes.");
        foodItem4.setCusine("Italian");
        foodItem4.setUser(ParseUser.getCurrentUser());
        file = loadPhoto(R.drawable.pasta);
        file.saveInBackground();
        foodItem4.setPhotoFile(file);
        foodItem4.saveInBackground();
    }

            /*
        ParseObject foodItem8 = ParseObject.create(FoodItem.class);
                                            foodItem8.put("food", "pizza");
                                            foodItem8.saveInBackground();
                                            //initFoodItems();
                                            // Define the class we would like to query
                                            ParseQuery<FoodItem> query = ParseQuery.getQuery(FoodItem.class);
// Define our query conditions
                                            query.whereEqualTo("owner", ParseUser.getCurrentUser());
// Execute the find asynchronously
                                            query.findInBackground(new FindCallback<FoodItem>() {
                                                public void done(List<FoodItem> itemList, ParseException e) {
                                                    if (e == null) {
                                                        // Access the array of results here
                                                        for (FoodItem item : itemList)
                                                            Log.d(TAG, item.getName());
                                                    } else {
                                                        Log.d("item", "Error: " + e.getMessage());
                                                    }
                                                }
                                            });
         */
}
