package com.imtanan.finalproject;

/**
 * Created by Mani on 03/01/2016.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SearchRecipes extends Fragment implements View.OnClickListener {
    private EditText editTextId;
    private Button buttonRecipe;
    private TextView textViewResult;
    private TextView textViewResult1;
    private TextView textViewResult2;
    private TextView textViewResult3;
    private TextView textView;
    private ProgressDialog loading;
//Right what is this
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.searchrecipes_fragment, container, false);


        editTextId = (EditText) v.findViewById(R.id.editTextId);
        buttonRecipe = (Button) v.findViewById(R.id.buttonRecipe);
        textViewResult = (TextView) v.findViewById(R.id.textViewResult);
        textViewResult1 = (TextView) v.findViewById(R.id.textViewResult1);
        textViewResult2 = (TextView) v.findViewById(R.id.textViewResult2);
        textViewResult3 = (TextView) v.findViewById(R.id.textViewResult3);
        textView = (TextView)   v.findViewById(R.id.textView);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(Config.EMAIL_SHARED_PREF,"Not Available");

        textView.setText("Current User: " + email);
        buttonRecipe.setOnClickListener(this);
        return v;



    }
    private void getData() {
        String recipe_name = editTextId.getText().toString().trim();
        if (recipe_name.equals("") /*|| id.equals(null)*/) {
            Toast.makeText(SearchRecipes.this.getActivity(), "Please enter a recipe name", Toast.LENGTH_LONG).show();
            return;
        }
        loading = ProgressDialog.show(SearchRecipes.this.getActivity(), "Please wait...", "Fetching...", false, false);

        //Changed it so all the searching will be from database to lowercase
        String url = Config.DATA_URL + editTextId.getText().toString().toLowerCase().trim();

        //Changed it so all the searching will be from database to lowercase
        StringRequest stringRequest = new StringRequest(url.toLowerCase(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SearchRecipes.this.getActivity(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(SearchRecipes.this.getActivity());
        requestQueue.add(stringRequest);
    }


    private void showJSON(String response) {
        String recipe_name = "";
        String origin = "";
        String ingredients = "";
        String cm = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject recipeData = result.getJSONObject(0);
            recipe_name = recipeData.getString(Config.KEY_RECIPE_NAME);
            origin = recipeData.getString(Config.KEY_ORIGIN);
            ingredients = recipeData.getString(Config.KEY_INGREDIENTS);
            cm = recipeData.getString(Config.KEY_CM);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        textViewResult.setText("Recipe Name:\t" + "\n" + recipe_name);
        textViewResult3.setText("origin:\t" + "\n" + origin);
        textViewResult1.setText("\ningredients:\t" + "\n" + ingredients);
        textViewResult2.setText("\nCooking Method:\t" + "\n" + cm);
    }


    @Override
    public void onClick(View v) {
        getData();

    }
}