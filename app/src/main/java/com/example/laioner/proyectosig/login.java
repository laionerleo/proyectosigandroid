package com.example.laioner.proyectosig;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class login extends AppCompatActivity implements View.OnClickListener {
    TextView contenido;
    Button empezar;
    TextView numero;
    TextView contraseña;
    TextView usuario;

    LinkedList listausuario;
    LinkedList listacontraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario= (TextView)findViewById(R.id.textView4);
        contenido=(TextView)findViewById(R.id.contenido);
        empezar=(Button)findViewById(R.id.iniciar);
        empezar.setOnClickListener(this);

        numero=(TextView)findViewById(R.id.usuariotext);
        contraseña=(TextView)findViewById(R.id.contraseñatext);
        listausuario= new LinkedList();
        listacontraseña= new LinkedList();
        listausuario.add("654321");
        listausuario.add("666666");
        listausuario.add("777777");
        listausuario.add("60817621");



        listacontraseña.add("123456");
        listacontraseña.add("55555");
        listacontraseña.add("11385389");
        listacontraseña.add("11385389");

    }
    JSONArray res = new JSONArray();
    String respuestavalue= new String();



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iniciar:

                if(verificarusuario()){
                    Intent intent = new Intent(login.this,MainActivity.class);
                    startActivity(intent);
                }


                break;




        }
    }

    public  boolean verificarusuario(){

        String usuariologin= String.valueOf(numero.getText());
        String contraseñalogin = String.valueOf(contraseña.getText());
        if(listausuario.contains(usuariologin)){
            if(listacontraseña.contains(contraseñalogin)){
                usuario.setText("usuario existe");
                return true;
            }else{
                usuario.setText("contyraseña incorrecta");
                return  false;
            }
        }else{
            usuario.setText("usuario o contrase no  existe");
            return  false;

        }
    }
}
