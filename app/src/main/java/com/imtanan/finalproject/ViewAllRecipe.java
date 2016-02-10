package com.imtanan.finalproject;

/**
 * Created by Mani on 03/01/2016.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

//import android.support.v4.app.Fragment;

public class ViewAllRecipe extends android.support.v4.app.Fragment implements ListView.OnItemClickListener {

    private ListView listView;

    private String JSON_STRING;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.viewallrecipe_fragment, container, false);

        listView = (ListView) v.findViewById(R.id.listView);
        listView.setOnItemClickListener(ViewAllRecipe.this);
        getJSON();
        return v;
    }

    private void showRecipe() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_ID);
                String name = jo.getString(Config.TAG_NAME);

                HashMap<String, String> recipe = new HashMap<>();
                recipe.put(Config.TAG_ID, id);
                recipe.put(Config.TAG_NAME, name);
                list.add(recipe);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ViewAllRecipe.this.getActivity(), list, R.layout.list_item,
                new String[]{Config.TAG_ID, Config.TAG_NAME},
                new int[]{R.id.id, R.id.name});

        listView.setAdapter(adapter);
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewAllRecipe.this.getActivity(), "Fetching Data", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showRecipe();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Intent intent = new Intent(context, EditRecipesFragment.class);
       Intent i = new Intent(ViewAllRecipe.this.getActivity(), EditRecipesFragment.class);
        HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
        String RecId = map.get(Config.TAG_ID).toString();
        i.putExtra(Config.REC_ID, RecId);
        getActivity().startActivity(i);
    }
}
