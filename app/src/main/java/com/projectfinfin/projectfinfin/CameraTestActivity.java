package com.projectfinfin.projectfinfin;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;


public class CameraTestActivity extends Activity {

    private Camera mCamera;
    private CameraPreview mPreview;
    private Handler autoFocusHandler;
    TextView scanText;
    Button scanButton;
    ImageScanner scanner;
    private boolean barcodeScanned = false;
    private boolean previewing = true;
    static {
        System.loadLibrary("iconv");
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        autoFocusHandler = new Handler();
        mCamera = getCameraInstance(); // เปิด Camera
     /* Instance barcode scanner */
        scanner = new ImageScanner();
        scanner.setConfig(0, Config.X_DENSITY, 3);
        scanner.setConfig(0, Config.Y_DENSITY, 3);
        mPreview = new CameraPreview(this, mCamera, previewCb, autoFocusCB);  // สร้าง CameraPreview
        FrameLayout preview = (FrameLayout)findViewById(R.id.cameraPreview);
        preview.addView(mPreview);  // นำ Object CameraPreview มาใส่ใน Layout
        scanText = (TextView)findViewById(R.id.scanText);
        scanButton = (Button)findViewById(R.id.ScanButton);
        scanButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (barcodeScanned) {
                    barcodeScanned = false;
                    scanText.setText("Scanning...");
                    mCamera.setPreviewCallback(previewCb);
                    mCamera.startPreview();
                    previewing = true;
                    mCamera.autoFocus(autoFocusCB);
                }
            }
        });
    }
    public void onPause() {
        super.onPause();
        releaseCamera();
    }
    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e){
        }
        return c;
    }
    private void releaseCamera() {
        if (mCamera != null) {
            previewing = false;
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }
    private Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (previewing)
                mCamera.autoFocus(autoFocusCB);
        }
    };
    PreviewCallback previewCb = new PreviewCallback() {
        public void onPreviewFrame(byte[] data, Camera camera) {
            Camera.Parameters parameters = camera.getParameters();
            Size size = parameters.getPreviewSize();
            Image barcode = new Image(size.width, size.height, "Y800");  // สร้างรูป format Y800
            barcode.setData(data);  // ใส่ข้อมูลให้ภาพ ตรงนีข้อมูลน่าจะเป็นภาพจากกล้องครับ
            int result = scanner.scanImage(barcode);  // Scan หา Qr code ในรูป
            if (result != 0) {  // หากหาเจอ result ไม่เท่ากับ 1 ก็หยุด
                previewing = false;
                mCamera.setPreviewCallback(null);
                mCamera.stopPreview();
                SymbolSet syms = scanner.getResults();   // ดึงผลลัพธ์จาก Scanner คือผลลัพธ์ที่ decode แล้ว อาจมีหลาย code
                for (Symbol sym : syms) {
                    scanText.setText("barcode result " + sym.getData());  // ดึกค่าที่ถูก decode แล้วมาแสดง
                    barcodeScanned = true;
                }
            }
        }
    };
    // Mimic continuous auto-focusing
    AutoFocusCallback autoFocusCB = new AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            autoFocusHandler.postDelayed(doAutoFocus, 1000);  // ทำการโฟกัสใหม่ทุก 1 วินาที
        }
    };

}
