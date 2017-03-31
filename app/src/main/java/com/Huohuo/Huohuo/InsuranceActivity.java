package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.Huohuo.Huohuo.base.BaseActivity;

/**
 * Created by JinBo on 2017/3/30.
 */

public class InsuranceActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);
        showContentView();
        setTitle("保险理赔");
        WebView webView=(WebView)findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.baoyuntong.com/cargo/");
    }
    public static void start(Context context) {
        Intent intent = new Intent(context, InsuranceActivity.class);
        context.startActivity(intent);
    }

}
