package com.projectfinfin.projectfinfin.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.projectfinfin.projectfinfin.R;
import com.projectfinfin.projectfinfin.TypeBabyActivity;
import com.projectfinfin.projectfinfin.TypeBeautyActivity;
import com.projectfinfin.projectfinfin.TypeElectronicActivity;
import com.projectfinfin.projectfinfin.TypeFashionActivity;
import com.projectfinfin.projectfinfin.TypeFoodActivity;
import com.projectfinfin.projectfinfin.TypeHomeActivity;
import com.projectfinfin.projectfinfin.TypeMobileActivity;

/**
 * Created by TNBC's on 18/7/2558.
 */
public class CategoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);

        //getFragmentManager().beginTransaction().remove(CategoryFragment.this).commit();

        //Click Category Food
        Button buttonFood = (Button) rootView.findViewById(R.id.button_C_food);
        buttonFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), TypeFoodActivity.class);
                startActivity(i);
            }
        });

        //Click Category Fashion and Sport
        Button buttonFashion = (Button) rootView.findViewById(R.id.button_C_fashion);
        buttonFashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), TypeFashionActivity.class);
                startActivity(i);
            }
        });

        //Click Category Beauty
        Button buttonBeauty = (Button) rootView.findViewById(R.id.button_C_beauty);
        buttonBeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), TypeBeautyActivity.class);
                startActivity(i);
            }
        });

        //Click Category Baby
        Button buttonBaby = (Button) rootView.findViewById(R.id.button_C_baby);
        buttonBaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), TypeBabyActivity.class);
                startActivity(i);
            }
        });

        //Click Category Home
        Button buttonHome = (Button) rootView.findViewById(R.id.button_C_home);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), TypeHomeActivity.class);
                startActivity(i);
            }
        });

        //Click Category Mobile
        Button buttonMobile = (Button) rootView.findViewById(R.id.button_C_mobile);
        buttonMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), TypeMobileActivity.class);
                startActivity(i);
            }
        });

        //Click Category Electronic
        Button buttonElectronic = (Button) rootView.findViewById(R.id.button_C_eletronic);
        buttonElectronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), TypeElectronicActivity.class);
                startActivity(i);
            }
        });


        return rootView;
    }
}
