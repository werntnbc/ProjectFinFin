package com.projectfinfin.projectfinfin.WhereToGo;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.projectfinfin.projectfinfin.R;
import com.projectfinfin.projectfinfin.jsonFeed.DetailsActivity;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class ShowMapInnavFragment extends Fragment {

	static ProgressDialog pDialog;
	static View rootView;
	static TextView startStoreName;
	static TextView endStoreName;
	static ImageView startStoreMap;
	static ImageView endStoreMap;
	static Bitmap startBitMap;
	static Bitmap endBitMap;
	static Context c;
	static String startStorePath;
	static String endStorePath;

	// constructor

	public ShowMapInnavFragment(String singleshopurl) {

		new SimpleTask().execute(singleshopurl);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_showmapinnav, container,
				false);
		c = getActivity().getApplicationContext();
		startStoreMap = (ImageView) rootView.findViewById(R.id.StartMap);
		endStoreMap = (ImageView) rootView.findViewById(R.id.EndMap);
		startStoreName = (TextView) rootView.findViewById(R.id.StartStoreName);
		endStoreName = (TextView) rootView.findViewById(R.id.EndStoreName);

		startStoreMap.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(getActivity(), DetailsActivity.class);
					int[] screenLocation = new int[2];
					startStoreMap.getLocationOnScreen(screenLocation);
					intent.putExtra("left", screenLocation[0]).
							putExtra("top", screenLocation[1]).
							putExtra("width", startStoreMap.getWidth()).
							putExtra("height", startStoreMap.getHeight()).
							putExtra("title", "Navigation").
							putExtra("image", startStorePath);
					startActivity(intent);
				}
			});

			endStoreMap.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(getActivity(), DetailsActivity.class);
					int[] screenLocation = new int[2];
					endStoreMap.getLocationOnScreen(screenLocation);
					intent.putExtra("left", screenLocation[0]).
							putExtra("top", screenLocation[1]).
							putExtra("width", endStoreMap.getWidth()).
							putExtra("height", endStoreMap.getHeight()).
							putExtra("title", "Navigation").
							putExtra("image", endStorePath);
					startActivity(intent);
				}
			});

		return rootView;

	}

	public static class SimpleTask extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
//			// Create Show ProgressBar
//			pDialog = new ProgressDialog(getActivity());
//			pDialog.setMessage("Please wait...");
//			pDialog.setCancelable(false);
//			pDialog.show();
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
			try {
//				if (pDialog.isShowing())
//					pDialog.dismiss();
				
				showData(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	public static void showData(String jsonString) throws JSONException {

		jsonString = "{\"advancedStores\":" + jsonString + "}";
		Log.d("json", jsonString);

		JSONObject a = new JSONObject(jsonString);
		String startStoreNameFromJson = a.getJSONArray("advancedStores")
				.getJSONObject(0).getString("storeName");
		String startStoreMapFromJson = a.getJSONArray("advancedStores")
				.getJSONObject(0).getString("mapPicture");
		String endStoreNameFromJson = a.getJSONArray("advancedStores")
				.getJSONObject(1).getString("storeName");
		String endStoreMapFromJson = a.getJSONArray("advancedStores")
				.getJSONObject(1).getString("mapPicture");

		
		String startFloor = a.getJSONArray("advancedStores")
				.getJSONObject(0).getString("sStoreName");
		String endFloor = a.getJSONArray("advancedStores")
				.getJSONObject(1).getString("sStoreName");
		
		if(startFloor.equalsIgnoreCase(endFloor)){
			// ชั้นเดียวกัน
			
			startStorePath = "http://188.166.251.138:8080/images/map/"
					+ startStoreMapFromJson;
			endStorePath = "http://188.166.251.138:8080/images/map/"
					+ endStoreMapFromJson;
			// รูป end ไม่ขึ้น 

			Picasso.with(c).load(startStorePath).resize(900,900).centerInside().into(startStoreMap);
			Picasso.with(c).load(endStorePath).resize(900,900).centerInside().into(endStoreMap);

			Log.d("startStorePath", startStorePath);
			Log.d("endStorePath", endStorePath);
		}else{
			// คนละชั้น
			
			startStorePath = "http://188.166.251.138:8080/images/direction/"
					+ startStoreMapFromJson;
			endStorePath = "http://188.166.251.138:8080/images/direction/"
					+ endStoreMapFromJson;
			// รูป end ไม่ขึ้น

			Picasso.with(c).load(startStorePath).resize(900,900).centerInside().into(startStoreMap);
			Picasso.with(c).load(endStorePath).resize(900,900).centerInside().into(endStoreMap);

			//new LoadEndImage().execute(endStorePath);
			//new LoadStartImage().execute(startStorePath);

			Log.d("startStorePath", startStorePath);
			Log.d("endStorePath", endStorePath);
		}
		


		Log.d("startStoreNameFromJson", startStoreNameFromJson);
		Log.d("startStoreMapFromJson", startStoreMapFromJson);
		Log.d("endStoreNameFromJson", endStoreNameFromJson);
		Log.d("endStoreMapFromJson", endStoreMapFromJson);
//		Log.d("startStorePath", startStorePath);
//		Log.d("endStorePath", endStorePath);

		startStoreName.setText(startStoreNameFromJson);
		endStoreName.setText(endStoreNameFromJson);

	}

	public static class LoadStartImage extends
			AsyncTask<String, String, Bitmap> {

		protected Bitmap doInBackground(String... args) {
			try {
				startBitMap = BitmapFactory.decodeStream((InputStream) new URL(
						args[0]).getContent());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return startBitMap;
		}

		protected void onPostExecute(Bitmap image) {
			if (image != null) {
				startStoreMap.setImageBitmap(image);
			} else {
			}
		}
	}

	public static class LoadEndImage extends AsyncTask<String, String, Bitmap> {

		protected Bitmap doInBackground(String... args) {
			try {
				endBitMap = BitmapFactory.decodeStream((InputStream) new URL(
						args[0]).getContent());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return endBitMap;
		}

		protected void onPostExecute(Bitmap image) {
			if (image != null) {
				endStoreMap.setImageBitmap(image);
			} else {
			}
		}
	}

}
