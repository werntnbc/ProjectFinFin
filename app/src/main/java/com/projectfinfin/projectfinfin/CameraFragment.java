package com.projectfinfin.projectfinfin;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.projectfinfin.projectfinfin.zbarscanner.ZBarConstants;
import com.projectfinfin.projectfinfin.zbarscanner.ZBarScannerActivity;

import net.sourceforge.zbar.Symbol;

/**
 * Created by TNBC's on 9/7/2558.
 */
public class CameraFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_camera, container, false);

        //getFragmentManager().beginTransaction().remove(CameraFragment.this).commit();

        Button buttonBarcode = (Button) rootView.findViewById(R.id.button_go_camera);
        buttonBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ZBarScannerActivity.class);
                intent.putExtra(ZBarConstants.SCAN_MODES, new int[]{Symbol.QRCODE});
                startActivityForResult(intent, 0);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Toast.makeText(getActivity(), data.getStringExtra(ZBarConstants.SCAN_RESULT) , Toast.LENGTH_LONG).show();
        }
    }

}
