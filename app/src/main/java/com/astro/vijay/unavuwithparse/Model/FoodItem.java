package com.astro.vijay.unavuwithparse.Model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by uttamavillain on 3/7/16.
 */

@ParseClassName("FoodItem")
public class FoodItem extends ParseObject {

    public FoodItem() {
        super();
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public String getImageUrl() {

        return getString("imageUrl");
    }

    public void setImageUrl(String imageUrl) {
        put("imageUrl", imageUrl);
    }

    public String getDescription() {

        return getString("description");
    }

    public void setDescription(String description) {

        put("description", description);
    }

    public String getCusine() {
        return getString("cusine");
    }

    public void setCusine(String cusine) {
        put("cusine", cusine);
    }

    public ParseUser getUser() {
        return getParseUser("user");
    }

    public void setUser(ParseUser value) {
        put("user", value);
    }

    public ParseFile getPhotoFile() {
        return getParseFile("photo");
    }

    public void setPhotoFile(ParseFile file) {
        put("photo", file);
    }

    public static ParseQuery<FoodItem> getQuery() {
        return ParseQuery.getQuery(FoodItem.class);
    }

}