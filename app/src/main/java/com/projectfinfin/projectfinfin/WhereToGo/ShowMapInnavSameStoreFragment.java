package com.projectfinfin.projectfinfin.WhereToGo;

import android.app.Fragment;
import android.app.ProgressDialog;
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

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import com.projectfinfin.projectfinfin.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;



public class ShowMapInnavSameStoreFragment extends Fragment {

	static ProgressDialog pDialog;
	static View rootView;
	static TextView startStoreName;
	static ImageView startStoreMap;
	static Bitmap startBitMap;

	// constructor

	public ShowMapInnavSameStoreFragment(String singleshopurl) {

		new SimpleTask().execute(singleshopurl);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_showmapinnavsamestore, container,
				false);

		startStoreMap = (ImageView) rootView.findViewById(R.id.StartMap);
		startStoreName = (TextView) rootView.findViewById(R.id.StartStoreName);

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

	private static void showData(String jsonString) throws JSONException {

		jsonString = "{\"advancedStores\":" + jsonString + "}";
		Log.d("json", jsonString);

		JSONObject a = new JSONObject(jsonString);
		String startStoreNameFromJson = a.getJSONArray("advancedStores")
				.getJSONObject(0).getString("storeName");
		String startStoreMapFromJson = a.getJSONArray("advancedStores")
				.getJSONObject(0).getString("mapPicture");


		String startStorePath = "http://188.166.251.138:8080/images/map/"
				+ startStoreMapFromJson;

		// รูป end ไม่ขึ้น
		new LoadStartImage().execute(startStorePath);
		// can zoom in - out the image with this code
//		PhotoViewAttacher mAttacher1 = null;
//		mAttacher1 = new PhotoViewAttacher(startStoreMap);
//		mAttacher1.update();

		//

		Log.d("startStoreNameFromJson", startStoreNameFromJson);
		Log.d("startStoreMapFromJson", startStoreMapFromJson);
		Log.d("startStorePath", startStorePath);


		startStoreName.setText(startStoreNameFromJson);

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


}
