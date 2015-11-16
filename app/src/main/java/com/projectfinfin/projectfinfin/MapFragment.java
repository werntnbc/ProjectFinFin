package com.projectfinfin.projectfinfin;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
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
import com.projectfinfin.projectfinfin.WhereToGo.InNavFragment;
import com.projectfinfin.projectfinfin.WhereToGo.InnavDirection;

/**
 * Created by TNBC's on 23/7/2558.
 */
public class MapFragment extends Fragment {

    private Button buttonFloor , buttonLocation;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        buttonFloor = (Button) rootView.findViewById(R.id.button_find_floor);
        buttonFloor.setEnabled(false);
        buttonFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FloorSpinner();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        buttonLocation = (Button) rootView.findViewById(R.id.button_find_location);
        buttonLocation.setEnabled(false);
        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new InNavFragment();
                FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
                transaction1.replace(R.id.container, fragment);
                transaction1.addToBackStack(null);
                transaction1.commit();
            }
        });

        Spinner spinner = (Spinner) rootView.findViewById(R.id.map_spinner);
        spinner.setOnItemSelectedListener(new SpinnerClickListener());
        ArrayAdapter<CharSequence> Spinneradapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.Map_array,
                android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(Spinneradapter);
        //    floorLocationOnClick();
        return rootView;
    }

//    private void floorLocationOnClick(){
//        buttonFloor.setClickable(false);
//        buttonLocation.setClickable(false);
//
//    }

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
                break;
            case 1:
                buttonFloor.setEnabled(true);
                buttonLocation.setEnabled(true);
                break;
            default:
                break;
        }
        //  mAttacher.update();
    }
}
