package com.projectfinfin.projectfinfin.Floor;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.projectfinfin.projectfinfin.R;

/**
 * Created by TNBC's on 31/8/2558.
 */
public class FloorSpinner extends Fragment {

    private ImageView images;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_floorspinner, container, false);

        images = (ImageView) rootView.findViewById(R.id.imageView1);
        Spinner spinner = (Spinner) rootView.findViewById(R.id.floor_spinner);
        spinner.setOnItemSelectedListener(new SpinnerClickListener());

        ArrayAdapter<CharSequence> Spinneradapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.Floor_array,
                android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(Spinneradapter);

        return rootView;
    }

/* ของ Spinner */

    private class SpinnerClickListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position,
                                   long id) {
            // TODO Auto-generated method stub
            displaySpinner(position);

        }


        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    }


    private void displaySpinner(int position) {

      //  PhotoViewAttacher mAttacher = null;
        switch (position) {
            case 0:
                Drawable floor_g = getResources().getDrawable(R.drawable.floorg);
                images.setImageDrawable(floor_g);
                // mAttacher = new PhotoViewAttacher(images);
                break;
            case 1:
                Drawable floor_1 = getResources().getDrawable(R.drawable.floor1);
                images.setImageDrawable(floor_1);
               // mAttacher = new PhotoViewAttacher(images);
                break;
            case 2:
                Drawable floor_2 = getResources().getDrawable(R.drawable.floor2);
                images.setImageDrawable(floor_2);
              //  mAttacher = new PhotoViewAttacher(images);
                break;
            case 3:
                Drawable floor_3 = getResources().getDrawable(R.drawable.floor3);
                images.setImageDrawable(floor_3);
              //  mAttacher = new PhotoViewAttacher(images);
                break;
            case 4:
                Drawable floor_4 = getResources().getDrawable(R.drawable.floor4);
                images.setImageDrawable(floor_4);
               // mAttacher = new PhotoViewAttacher(images);
                break;
            default:
                break;
        }
      //  mAttacher.update();
    }
}
