package com.imtanan.finalproject;

/**
 * Created by Mani on 03/01/2016.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class EditRecipesFragment extends Fragment implements View.OnClickListener {

    private EditText editTextId;
    private EditText editTextName;
    private EditText editTextOrigin;
    private EditText editTextIngre;
    private EditText editTextCM;

    private Button buttonUpdate;
    // private Button buttonDelete;

    private String id;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.erecipes_fragment, container, false);

        Intent intent = getActivity().getIntent();

        id = intent.getStringExtra(Config.REC_ID);

        editTextId = (EditText) v.findViewById(R.id.editTextId);
        editTextName = (EditText) v.findViewById(R.id.editTextName);
        editTextOrigin = (EditText) v.findViewById(R.id.editTextOrigin);
        editTextIngre = (EditText) v.findViewById(R.id.editTextIngre);
        editTextCM = (EditText) v.findViewById(R.id.editTextCM);

        buttonUpdate = (Button) v.findViewById(R.id.buttonUpdate);
        // buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(EditRecipesFragment.this);
        //buttonDelete.setOnClickListener(this);

        editTextId.setText(id);

        getRecipe();
        return v;
    }

    private void getRecipe() {
        class GetRecipe extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditRecipesFragment.this.getActivity(), "Fetching...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showRecipe(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_REC, id);
                return s;
            }
        }
        GetRecipe gr = new GetRecipe();
        gr.execute();
    }

    private void showRecipe(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(Config.TAG_NAME);
            String origin = c.getString(Config.TAG_ORIGIN);
            String ingre = c.getString(Config.TAG_INGRE);
            String cm = c.getString(Config.TAG_CM);

            editTextName.setText(name);
            editTextOrigin.setText(origin);
            editTextIngre.setText(ingre);
            editTextCM.setText(cm);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateRecipe() {
        final String name = editTextName.getText().toString().toLowerCase().trim();
        final String origin = editTextOrigin.getText().toString().toLowerCase().trim();
        final String ingre = editTextIngre.getText().toString().toLowerCase().trim();
        final String cm = editTextCM.getText().toString().toLowerCase().trim();

        class UpdateRecipe extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditRecipesFragment.this.getActivity(), "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(EditRecipesFragment.this.getActivity(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_REC_ID, id);
                hashMap.put(Config.KEY_REC_NAME, name);
                hashMap.put(Config.KEY_REC_ORIGIN ,origin);
                hashMap.put(Config.KEY_REC_INGRE, ingre);
                hashMap.put(Config.KEY_REC_CM, cm);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_REC, hashMap);

                return s;
            }
        }

        UpdateRecipe ur = new UpdateRecipe();
        ur.execute();
    }

    @Override
    public void onClick(View v) {
        if (v == buttonUpdate) {
            updateRecipe();
        }

       /* if (v == buttonDelete) {
            confirmDeleteEmployee();
        }*/
    }
}
