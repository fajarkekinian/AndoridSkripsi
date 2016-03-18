package com.app.quranqu;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.app.quranqu.utils.AppAPI;
import com.app.quranqu.utils.AppConstant;
import com.app.quranqu.utils.AppUtility;
import com.app.quranqu.utils.DataCacheManagement;

import org.json.JSONObject;

import java.net.URLEncoder;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_PHONE_STATE;

/**
 * Created by julyan on 10/22/2015.
 * edited by trian on 03/18/2016
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new GetAuthTokenAsc().execute();
        if(canMakeSmores()){
            setPermission();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void setPermission(){
        if (ContextCompat.checkSelfPermission(SplashActivity.this, INTERNET) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(SplashActivity.this, ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this, READ_PHONE_STATE)) {
                AppUtility.printLog("masuk pertanyaan");
//                CoordinatorLayout coor = (CoordinatorLayout) findViewById(R.id.lytCoor);
//                Snackbar.make(coor, "Tolong Izinkan",
//                        Snackbar.LENGTH_INDEFINITE)
//                        .setAction("OK", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                ActivityCompat.requestPermissions(SplashActivity.this,
//                                        new String[]{
//                                                READ_PHONE_STATE
//                                        }, 111);
//
//                            }
//                        })
//                        .show();

            } else {
                AppUtility.printLog("Masuk langsung");
                ActivityCompat.requestPermissions(SplashActivity.this,
                        new String[]{
                                INTERNET,ACCESS_NETWORK_STATE
                        }, 111);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 111 : if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                AppUtility.printLog("read phone state permission granted");
            } else {
                AppUtility.printLog("read phone state permission denied");
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
            }
                break;
        }
    }

    /**
     * Just a check to see if we have marshmallows (version 23)
     *
     */
    private boolean canMakeSmores() {
        return(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1);
    }
    private class GetAuthTokenAsc extends AsyncTask<String, Void,String>{
        @Override
        protected String doInBackground(String... params) {
            String result="";
            try{
                String charset  = "UTF-8";
                String query = String.format("client_id=%s&client_secret=%s",
                        URLEncoder.encode(AppConstant.CLIENT_ID, charset),
                        URLEncoder.encode(AppConstant.CLIENT_SECRET, charset));
                result = AppUtility.HttpUrlConnectionPost(AppAPI.API_AUTH_TOKEN, query, charset, 10000);
            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            AppUtility.progressDialog(SplashActivity.this, "Mohon Tunggu", false);
            AppUtility.progressDialogShow();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            AppUtility.printLog(result);
            try{
                JSONObject jsonObject = new JSONObject(result);
                if(jsonObject.getInt(AppConstant.CODE)==201){
                    JSONObject jsonObject2 = new JSONObject(jsonObject.getString(AppConstant.DATA));
                    DataCacheManagement dcm = new DataCacheManagement(SplashActivity.this);
                    dcm.setAuthToken(jsonObject2.getString(AppConstant.ACCESS_TOKEN));
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }else{
                    AppUtility.printLog("error info = "+jsonObject.getString(AppConstant.INFO));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            AppUtility.progressDialogDismiss();
        }

    }
}
