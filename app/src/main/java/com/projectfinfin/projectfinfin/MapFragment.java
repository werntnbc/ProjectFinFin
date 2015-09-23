package com.projectfinfin.projectfinfin;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.projectfinfin.projectfinfin.Floor.FloorSpinner;
import com.projectfinfin.projectfinfin.Fragments.CategoryFragment;

/**
 * Created by TNBC's on 23/7/2558.
 */
public class MapFragment extends Fragment {

        private Button button;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        button = (Button)rootView.findViewById(R.id.button_find_floor);
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
        switch (position) {
            case 0:
//                Drawable floor_g = getResources().getDrawable(R.drawable.floorg);
//                images.setImageDrawable(floor_g);
                break;
            default:
                break;
        }
        //  mAttacher.update();
    }
}
