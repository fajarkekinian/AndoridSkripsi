package com.app.quranqu;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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

        if(isNetworkConnected()){
            new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }
            }, 1000);
        }else{
            showAlertSetting();
        }

        if(canMakeSmores()){
            setPermission();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void setPermission(){
        if (ContextCompat.checkSelfPermission(SplashActivity.this, INTERNET) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(SplashActivity.this, ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this, INTERNET)) {
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
     */
    private boolean canMakeSmores() {
        return(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    /**
     * retrieve auth token from server
     * **/
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
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                }else{
                    AppUtility.printLog("error info = "+jsonObject.getString(AppConstant.INFO));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    /**
     * check internet connection
     * **/
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    /**
     * show alert setting
     * **/
    private void showAlertSetting(){
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        builder.setTitle("Tidak Ada Koneksi");
        builder.setMessage("Mohon cek koneksi internet Anda?");
        builder.setPositiveButton(getResources().getString(R.string.lbl_reply), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                startActivity(new Intent(SplashActivity.this,SplashActivity.class));
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
    }
}
