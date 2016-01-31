package com.imtanan.finalproject;

/**
 * Created by Mani on 21/01/2016.
 */
public class Config {

    public static final String DATA_URL = "http://iaraja.mohammedsohaib.com/getData/getData.php?recipe_name="; //The last part decides how you are going to search it
    public static final String KEY_RECIPE_NAME = "recipe_name";
    public static final String KEY_ORIGIN = "origin";
    public static final String KEY_INGREDIENTS = "ingredients";
    public static final String KEY_CM = "cm";
    public static final String JSON_ARRAY = "result";


    //URL to our login.php file
    public static final String LOGIN_URL = "http://iaraja.mohammedsohaib.com/mobile/login.php";
    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";


    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
}