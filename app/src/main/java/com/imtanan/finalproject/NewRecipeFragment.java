package com.imtanan.finalproject;

/**
 * Created by Mani on 03/01/2016.
 */


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class NewRecipeFragment extends Fragment implements View.OnClickListener{

    //Defining views
    private EditText editTextName;
    private EditText editTextCM;
    private EditText editTextIngre;
    private EditText editTextOrigin;
    private Button buttonAdd;
    private Button buttonView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.newrecipe_fragment, container, false);


        //Initializing views
        editTextName = (EditText) v.findViewById(R.id.editTextName);
        editTextOrigin = (EditText) v.findViewById(R.id.editTextOrigin);
        editTextIngre = (EditText) v.findViewById(R.id.editTextIngre);
        editTextCM = (EditText) v.findViewById(R.id.editTextCM);

        buttonAdd = (Button) v.findViewById(R.id.buttonAdd);
      //  buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(NewRecipeFragment.this);
       // buttonView.setOnClickListener(this);

        return v;
    }

    //Adding an employee
    private void addRecipe(){

        final String name = editTextName.getText().toString().toLowerCase().trim();
        final String origin = editTextName.getText().toString().toLowerCase().trim();
        final String ingre = editTextIngre.getText().toString().toLowerCase().trim();
        final String cm = editTextCM.getText().toString().toLowerCase().trim();

        class AddRecipe extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(NewRecipeFragment.this.getActivity(),"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(NewRecipeFragment.this.getActivity(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_REC_NAME,name);
                params.put(Config.KEY_REC_ORIGIN,origin);
                params.put(Config.KEY_REC_INGRE,ingre);
                params.put(Config.KEY_REC_CM,cm);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }

        AddRecipe ar = new AddRecipe();
        ar.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addRecipe();
        }

       /* if(v == buttonView){
            startActivity(new Intent(this,ViewAllRecipe.class));
        }*/
    }
}