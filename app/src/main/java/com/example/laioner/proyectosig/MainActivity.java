package com.example.laioner.proyectosig;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button vermapa;
    Button salirruta;
    Button iniciar;
    Button salir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        salirruta=(Button)findViewById(R.id.salirruta);
        vermapa=(Button)findViewById(R.id.vermapa);
        iniciar=(Button)findViewById(R.id.iniciar);
        salir = (Button)findViewById(R.id.salir);

        salirruta.setOnClickListener(this);
        vermapa.setOnClickListener(this);
        iniciar.setOnClickListener(this);

       salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }

        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.vermapa:
                Intent intent = new Intent(MainActivity.this,vistamapa.class);
                startActivity(intent);
                break;
            case R.id.salirruta:
                Intent intent2 = new Intent(MainActivity.this,vistasalir.class);
                startActivity(intent2);
                break;
            case R.id.iniciar:
                Intent intent3 = new Intent(MainActivity.this,IniciarActivity.class);
                startActivity(intent3);
                break;
        }
    }


    @Override
    public void onBackPressed() {

    }
}
