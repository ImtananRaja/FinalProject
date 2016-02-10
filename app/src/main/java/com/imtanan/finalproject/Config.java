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


    //The Url for register
    public static final String REGISTER_URL = "http://iaraja.mohammedsohaib.com/mobile/register.php";

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
    //This is do the Url for the user to upload an image to the database
    public static final String UPLOAD_URL ="http://iaraja.mohammedsohaib.com/VolleyUpload/Upload.php";
    //The names of the variables in UplaodImage.java class
    public static final String KEY_IMAGE = "image";
    public static final String KEY_NAME = "name";



    //Address of our scripts of the CRUD
    public static final String URL_ADD="http://iaraja.mohammedsohaib.com/CRUD_Recipe/Create.php";
    public static final String URL_GET_ALL = "http://iaraja.mohammedsohaib.com/CRU_Recipe/ReadAll.php";
    public static final String URL_GET_REC = "http://iaraja.mohammedsohaib.com/CRUD_Recipe/Read.php?id=";
    public static final String URL_UPDATE_REC = "http://iaraja.mohammedsohaib.com/CRUD_Recipe/Update.php";
    public static final String URL_DELETE_REC = "http://iaraja.mohammedsohaib.com/CRUD_Recipe/Delete.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_REC_ID = "id";
    public static final String KEY_REC_NAME = "recipe_name";
    public static final String KEY_REC_ORIGIN = "origin";
    public static final String KEY_REC_INGRE = "ingredients";
    public static final String KEY_REC_CM = "cookmethod";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "recipe_name";
    public static final String TAG_ORIGIN = "origin";
    public static final String TAG_INGRE = "ingredients";
    public static final String TAG_CM = "cookmethod";

    //employee id to pass with intent
    public static final String REC_ID = "rec_id";
}