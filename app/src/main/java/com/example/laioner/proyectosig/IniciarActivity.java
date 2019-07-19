package com.example.laioner.proyectosig;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IniciarActivity extends AppCompatActivity implements View.OnClickListener,LocationListener{
    private serviceTask asyncTask ;
    private SendToServer sendubicacion;
    private String IP = "http://proyectobaseleo.000webhostapp.com/es/";
    private String GET = IP + "Iniciar/";
    private String  UBICACION= IP + "/ubicacion/";
    private JSONObject respuestaJSON;
    int contador;
    TextView contenido;
    Button empezar;
    Button terminar;
    TextView numero;
    TextView contraseña;
    Boolean  habilitador;
    private GoogleMap mMap;
    LocationManager locationManager;
    Location location; // location
    double latitude; // latitude
    double longitude; // longitude
    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0; // 0 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000; // 1 second

// Declaring a Location Manager
private Context mContext = null;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;
    WebView myBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);
        contenido=(TextView)findViewById(R.id.textView6);
        empezar=(Button)findViewById(R.id.empezar);
        empezar.setOnClickListener(this);
        contador=0;
        habilitador=false;
        terminar=(Button)findViewById(R.id.terminar);
        terminar.setOnClickListener(this);

        mContext = IniciarActivity.this;

        List<String> indices = new ArrayList<>();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getlocalizacion();

        ///cargar webview
        myBrowser =(WebView)findViewById(R.id.web1) ;
        WebSettings webSettings = myBrowser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myBrowser.setWebViewClient(new WebViewClient());
        myBrowser.setWebViewClient(new MyAppWebViewClient());
       // myBrowser.loadUrl("https://proyectobaseleo.000webhostapp.com/es/");

    }
    JSONArray res = new JSONArray();
    String respuestavalue= new String();

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.empezar:

                habilitador=true;
                contenido.setText("habilitado y mandando");

                break;
            case R.id.terminar:

                habilitador=false;
                contenido.setText("desabilitado y sin mandar ubicacion");
                break;


        }
    }

    public Location getlocalizacion(){
        locationManager = (LocationManager) mContext
                .getSystemService(LOCATION_SERVICE);

        // getting GPS status
        isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        // getting network status
        isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnabled && !isNetworkEnabled) {
            // no network provider is enabled
        } else {
            this.canGetLocation = true;
            if (isNetworkEnabled) {
                //updates will be send according to these arguments
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                }
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES,  this);
                Log.d("Network", "Network");
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        contenido.setText("ubicaciones"+ String.valueOf(latitude)+" ///" + String.valueOf(longitude));
                    }
                }
            }
            // if GPS Enabled get lat/long using GPS Services
            if (isGPSEnabled) {
                if (location == null) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME_BW_UPDATES,MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    Log.d("GPS Enabled", "GPS Enabled");
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
            }
        }
        return location;


    }

    @Override
    public void onLocationChanged(Location location) {
     //   Location l= getlocalizacion();
        contador++;
        if(habilitador) {
            String url = UBICACION + "/" + String.valueOf(location.getLatitude()) + "/" + String.valueOf(location.getLongitude()) + "/3";
            //String url=""+String.valueOf( location.getLatitude()) +"/"+String.valueOf( location.getLongitude()) +"--"+ contador;
            //asyncTask.execute(IP+"/choferes/lista","1");
            myBrowser.loadUrl(url);
            contenido.setText(url);
        }
        //myBrowser.loadUrl("https://proyectobaseleo.000webhostapp.com/es/");

        //sendubicacion=new  SendToServer();
        //sendubicacion.execute(url,"1");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    public class serviceTask extends  AsyncTask<String,Void,JSONObject> {
        private String debug="";
        @Override
        protected JSONObject doInBackground(String... params) {

            String cadena = params[0];
            URL url = null;
            String request = "";

            if (params[1] == "1") {
                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
                    //connection.setHeader("content-type", "application/json");

                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();


                    if (respuesta == HttpURLConnection.HTTP_OK) {
                        debug+="//entro if verifConexion"+ "\n";

                        InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada

                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReade

                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);        // Paso toda la entrada al StringBuilder
                            res.put(line);
                        }
                        respuestavalue= result.toString();
                        respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena

                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                return respuestaJSON;
                //return res;
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            final List<String> list = new ArrayList<>();
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("consulta");
                contenido.setText(jsonArray.toString());


            }catch(JSONException e){
                e.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(JSONObject jsonObject) {
            super.onCancelled(jsonObject);
        }
    }
    static class SendToServer extends AsyncTask<String, String, String> {
        private String debug="";

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        // @Override
      /*  protected String doInBackground(String... params) {
            String cadena = params[0];
            URL url = null;
            String request = "";

            if (params[1] == "1") {
                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
                    //connection.setHeader("content-type", "application/json");

                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();


                    if (respuesta == HttpURLConnection.HTTP_OK) {
                        debug+="//entro if verifConexion"+ "\n";

                        InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada

                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader

                        String line;
                      /*  while ((line = reader.readLine()) != null) {
                            result.append(line);        // Paso toda la entrada al StringBuilder
                            res.put(line);
                        }
                        respuestavalue= result.toString();
                        respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena

                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                return respuestavalue;
                //return res;
            }

            return respuestavalue;
        }*/
    }
}
