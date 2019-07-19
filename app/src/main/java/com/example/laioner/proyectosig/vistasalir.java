package com.example.laioner.proyectosig;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class vistasalir extends AppCompatActivity  implements View.OnClickListener {

    EditText motivo;
    Button enviar;
    WebView myBrowser;
    private String IP = "http://proyectobaseleo.000webhostapp.com/es/crearretiro/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistasalir);
        motivo=(EditText)findViewById(R.id.editText2);
        enviar =(Button)findViewById(R.id.button4);
        enviar.setOnClickListener(this);
        myBrowser =(WebView)findViewById(R.id.webretiro) ;
        WebSettings webSettings = myBrowser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myBrowser.setWebViewClient(new WebViewClient());
        myBrowser.setWebViewClient(new MyAppWebViewClient());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button4:
                String url = IP + motivo.getText()+ "/3";
                myBrowser.loadUrl(url);
                //finish();


                break;


        }
    }
}
