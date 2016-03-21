package com.app.quranqu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.quranqu.Adapter.ListQuranAdapter;
import com.app.quranqu.Model.DataHolder;
import com.app.quranqu.utils.AppAPI;
import com.app.quranqu.utils.AppConstant;
import com.app.quranqu.utils.AppUtility;
import com.app.quranqu.utils.DataCacheManagement;

import org.json.JSONArray;
import org.json.JSONObject;

public class DetailSurahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_surah);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new GetDetailSurahAsc().execute();

    }
    /**
     * retrieve surah detail from server vie GET METHOD
     * **/
    private class GetDetailSurahAsc extends AsyncTask<String, Void,String> {
        @Override
        protected String doInBackground(String... params) {
            String result="";
            DataCacheManagement dcm = new DataCacheManagement(DetailSurahActivity.this);
            try{
                result = AppUtility.HttpUrlConnectionGet(AppAPI.API_LIST_SURAH_DETAIL, 10000, "");
            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            AppUtility.progressDialog(DetailSurahActivity.this, "Mohon Tunggu", false);
            AppUtility.progressDialogShow();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            AppUtility.printLog(result);
            try{
                JSONObject jsonObject = new JSONObject(result);
                if(jsonObject.getInt(AppConstant.CODE)==200){
                    JSONArray arrayList = new JSONArray(jsonObject.getString(AppConstant.DATA));
                    if(arrayList.length()>0) {
                        JSONObject jsonObject2;
                        for (int i = 0; i < arrayList.length(); i++) {
                            jsonObject2 = arrayList.getJSONObject(i);
//                            orderList.add(
//                                    new DataHolder(jsonObject2.getString(AppConstant.INDEX),
//                                            jsonObject2.getString(AppConstant.NAME_INDONESIA))
//                            );
                        }
                    }
//                    ListQuranAdapter mListOrderAdapter = new ListQuranAdapter(orderList, ListQuranActivity.this);
//                    mViewList.setAdapter(mListOrderAdapter);
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
