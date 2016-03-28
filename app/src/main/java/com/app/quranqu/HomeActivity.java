package com.app.quranqu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        WebView wvHome = (WebView)findViewById(R.id.wvHome);
//        wvHome.loadDataWithBaseURL("http://103.43.45.112/service/mobile/", "", "text/html", "utf-8", null);
        wvHome.loadUrl("http://103.43.45.112/service/mobile/");
        wvHome.clearCache(true);
        wvHome.clearHistory();
        wvHome.getSettings().setJavaScriptEnabled(true);
        wvHome.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wvHome.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

}
