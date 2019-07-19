package com.example.laioner.proyectosig;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by laioner on 23/07/2017.
 */
public class MyAppWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String URL){
    if (Uri.parse(URL).getHost().endsWith("000webhostapp.com")){
        return false;
            }
    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(URL));
    view.getContext().startActivity(intent);
return true;
}
}

