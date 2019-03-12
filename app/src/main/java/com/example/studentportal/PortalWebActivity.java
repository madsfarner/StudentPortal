package com.example.studentportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PortalWebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal_web);

        //Get URL through intent and initiates the WebView with the URL. Set spesific WebViewClient so default behavior doesnt open switch app to Chrome browser
        Intent intent = getIntent();
        String mURL = intent.getStringExtra(MainActivity.EXTRATEXT_URL);
        WebView mWebView = findViewById(R.id.webView);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(mURL);
    }
}
