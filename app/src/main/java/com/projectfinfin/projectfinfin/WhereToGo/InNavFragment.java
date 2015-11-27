package com.projectfinfin.projectfinfin.WhereToGo;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.projectfinfin.projectfinfin.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.List;
//import android.widget.Toast;

public class InNavFragment extends Fragment {

    public static final String url = "http://188.166.251.138:8080/GsonShowStore";
    private ProgressDialog pDialog;
    private CustomAdapter_Storelv mAdapter;
    Spinner startspinner;
    Spinner endspinner;
    Button btn;

    public InNavFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_innav, container,
                false);

        startspinner = (Spinner) rootView.findViewById(R.id.spinner1);
        endspinner = (Spinner) rootView.findViewById(R.id.spinner2);
        btn = (Button) rootView.findViewById(R.id.button1);

        startspinner.setOnItemSelectedListener(new SpinnerSelected());
        endspinner.setOnItemSelectedListener(new SpinnerSelected());
        btn.setOnClickListener(new buttonClick());


        new SimpleTask().execute(url);
        return rootView;

    }


    public class buttonClick implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            int startPosition = startspinner.getSelectedItemPosition();
            int endPosition = endspinner.getSelectedItemPosition();
            String start = (String) startspinner.getItemAtPosition(startPosition);
            String end = (String) endspinner.getItemAtPosition(endPosition);
//			Toast.makeText(getActivity(), start + " and " + end,Toast.LENGTH_SHORT).show();
            if (start.equalsIgnoreCase(end)) {
                new HttpAsyncTaskSameStore().execute("http://188.166.251.138:8080/GsonShowInNav", start, end);
            } else {
                new HttpAsyncTask().execute("http://188.166.251.138:8080/GsonShowInNav", start, end);
            }

        }
    }

    @SuppressLint("ShowToast")
    public class SpinnerSelected implements OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            int startPosition = startspinner.getSelectedItemPosition();
            int endPosition = endspinner.getSelectedItemPosition();
            String start = (String) startspinner
                    .getItemAtPosition(startPosition);
            String end = (String) endspinner.getItemAtPosition(endPosition);
            Log.d("SelectedItemOnSpinner", start + " and " + end);


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // TODO Auto-generated method stub
        }

    }

    private class SimpleTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            // Create Show ProgressBar
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... urls) {
            String result = "";
            try {

                HttpGet httpGet = new HttpGet(urls[0]);
                HttpClient client = new DefaultHttpClient();

                HttpResponse response = client.execute(httpGet);

                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    InputStream inputStream = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result += line;
                    }
                }

            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }
            return result;

        }

        protected void onPostExecute(String jsonString) {
            // Dismiss ProgressBar
            if (pDialog.isShowing())
                pDialog.dismiss();

            showData(jsonString);
        }
    }

    private void showData(String jsonString) {

        jsonString = "{\"stores\":" + jsonString + "}";

        Gson gson = new Gson();
        Blog blog = gson.fromJson(jsonString, Blog.class);
        List<Store> stores = blog.getStores();

        mAdapter = new CustomAdapter_Storelv(getActivity(), stores);

        startspinner.setAdapter(mAdapter);
        endspinner.setAdapter(mAdapter);

    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            InnavDirection innav = new InnavDirection();
            innav.setStartStore(urls[1]);
            innav.setEndStore(urls[2]);

            return POST(urls[0], innav);
        }

        protected void onPostExecute(String mapStoreurl) {
            Log.d("mapStoreurl", mapStoreurl);

            Fragment fg = new ShowMapInnavFragment(mapStoreurl);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.container, fg);
            ft.addToBackStack(null);
            ft.commit();

        }
    }

    public static String POST(String url, InnavDirection innav) {

        String mapStoreurl = "";
        try {

            String json = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("startStore", innav.getStartStore());
            jsonObject.accumulate("endStore", innav.getEndStore());
            json = jsonObject.toString();
            // print Store Name
            Log.d("json", json);

            mapStoreurl = url + "?jsonData=" + URLEncoder.encode(json, "UTF-8");
//			Log.d("mapStoreurl", mapStoreurl);

        } catch (Exception e) {

        }
        return mapStoreurl;
    }


    private class HttpAsyncTaskSameStore extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            InnavDirection innav = new InnavDirection();
            innav.setStartStore(urls[1]);
            innav.setEndStore(urls[2]);

            return POST(urls[0], innav);
        }

        protected void onPostExecute(String mapStoreurl) {
            Log.d("mapStoreurl", mapStoreurl);

            Fragment fg = new ShowMapInnavSameStoreFragment(mapStoreurl);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ft.replace(R.id.container, fg);
            ft.addToBackStack(null);
            ft.commit();

        }
    }

    public static String POSTSameStore(String url, InnavDirection innav) {

        String mapStoreurl = "";
        try {

            String json = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("startStore", innav.getStartStore());
            jsonObject.accumulate("endStore", innav.getEndStore());
            json = jsonObject.toString();
            // print Store Name
            Log.d("json", json);

            mapStoreurl = url + "?jsonData=" + URLEncoder.encode(json, "UTF-8");
//			Log.d("mapStoreurl", mapStoreurl);

        } catch (Exception e) {

        }
        return mapStoreurl;
    }

}