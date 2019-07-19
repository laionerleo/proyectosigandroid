package com.example.laioner.proyectosig;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
//import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.LinkedList;

public class vistamapa extends FragmentActivity implements OnMapReadyCallback, LocationListener {


    private GoogleMap mMap;
    private static final int LOCATION_REQUEST = 500;
    private LocationManager locManager;
    private LocationListener locListener;

    private static final String TAG = "MapActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    LinkedList<LatLng> listaruta;

    //vars
    private Boolean mLocationPermissionsGranted = false;
    double longitude;
    double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistamapa);
        listaruta = new LinkedList<>();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
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
    };


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        cargarmapa();

        for (int i = 0; i<listaruta.size()-1;i++){

            mMap.addPolyline(new PolylineOptions().add(listaruta.get(i), listaruta.get(i+1)).width(10).color(Color.GREEN));

        }






        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }






        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
       locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);
       Location myLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

  if (myLocation!=null){
            longitude = myLocation.getLongitude();
            latitude = myLocation.getLatitude();
            LatLng centro= new LatLng(latitude,longitude);
            mMap.addMarker(new MarkerOptions().position(centro).title("aqui esta el centro"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(centro));
            CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(centro, 15);
            mMap.moveCamera(camera);

        }else{
           LatLng centro= new LatLng(-17.851782,-63.199860);
            mMap.addMarker(new MarkerOptions().position(centro).title("aqui esta el centro"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(centro));
            CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(centro, 15);
            mMap.moveCamera(camera);
        }


    }

    public void cargarmapa(){
        //listaruta= new LinkedList<>();
        listaruta.add(new LatLng(-17.80350000000	,-63.08920000000));
        listaruta.add(new LatLng(-17.80340000000	,-63.08990000000));
        listaruta.add(new LatLng(-17.80310000000	,-63.09050000000));
        listaruta.add(new LatLng(-17.80290000000	,-63.09110000000));
        listaruta.add(new LatLng(-17.80280000000	,-63.09150000000));
        listaruta.add(new LatLng(-17.80260000000	,-63.09220000000));
        listaruta.add(new LatLng(-17.80250000000	,-63.09280000000));
        listaruta.add(new LatLng(-17.80230000000	,-63.09350000000));
        listaruta.add(new LatLng(-17.80210000000	,-63.09460000000));
        listaruta.add(new LatLng(-17.80210000000	,-63.09530000000));
        listaruta.add(new LatLng(-17.80200000000	,-63.09600000000));
        listaruta.add(new LatLng(-17.80220000000	,-63.09670000000));
        listaruta.add(new LatLng(-17.80220000000	,-63.09670000000));
        listaruta.add(new LatLng(-17.80190000000	,-63.09740000000));
        listaruta.add(new LatLng(-17.80200000000	,-63.09810000000));
        listaruta.add(new LatLng(-17.80190000000	,-63.09960000000));
        listaruta.add(new LatLng(-17.80090000000	,-63.09960000000));
        listaruta.add(new LatLng(-17.79960000000	,-63.09940000000));
        listaruta.add(new LatLng(-17.79900000000	,-63.09940000000));
        listaruta.add(new LatLng(-17.79820000000	,-63.09940000000));
        listaruta.add(new LatLng(-17.79810000000	,-63.10070000000));
        listaruta.add(new LatLng(-17.79800000000	,-63.10150000000));
        listaruta.add(new LatLng(-17.79800000000	,-63.10240000000));
        listaruta.add(new LatLng(-17.79790000000	,-63.10320000000));
        listaruta.add(new LatLng(-17.79790000000	,-63.10450000000));
        listaruta.add(new LatLng(-17.79790000000	,-63.10510000000));
        listaruta.add(new LatLng(-17.79790000000	,-63.10650000000));
        listaruta.add(new LatLng(-17.79790000000	,-63.10710000000));
        listaruta.add(new LatLng(-17.79780000000	,-63.10790000000));
        listaruta.add(new LatLng(-17.79780000000	,-63.10860000000));
        listaruta.add(new LatLng(-17.79780000000	,-63.10890000000));
        listaruta.add(new LatLng(-17.79770000000	,-63.11030000000));
        listaruta.add(new LatLng(-17.79770000000	,-63.11170000000));
        listaruta.add(new LatLng(-17.79760000000	,-63.11280000000));
        listaruta.add(new LatLng(-17.79750000000	,-63.11380000000));
        listaruta.add(new LatLng(-17.79750000000	,-63.11530000000));
        listaruta.add(new LatLng(-17.79740000000	,-63.11690000000));
        listaruta.add(new LatLng(-17.79730000000	,-63.11860000000));
        listaruta.add(new LatLng(-17.79720000000	,-63.11930000000));
        listaruta.add(new LatLng(-17.79710000000	,-63.12090000000));
        listaruta.add(new LatLng(-17.79700000000	,-63.12270000000));
        listaruta.add(new LatLng(-17.79700000000	,-63.12450000000));
        listaruta.add(new LatLng(-17.79690000000	,-63.12560000000));
        listaruta.add(new LatLng(-17.79690000000	,-63.12630000000));
        listaruta.add(new LatLng(-17.79680000000	,-63.12710000000));
        listaruta.add(new LatLng(-17.79640000000	,-63.12710000000));
        listaruta.add(new LatLng(-17.79630000000	,-63.12820000000));
        listaruta.add(new LatLng(-17.79610000000	,-63.12890000000));
        listaruta.add(new LatLng(-17.79740000000	,-63.12920000000));
        listaruta.add(new LatLng(-17.79730000000	,-63.13070000000));
        listaruta.add(new LatLng(-17.79720000000	,-63.13210000000));
        listaruta.add(new LatLng(-17.79690000000	,-63.13490000000));
        listaruta.add(new LatLng(-17.79630000000	,-63.13480000000));
        listaruta.add(new LatLng(-17.79560000000	,-63.13480000000));
        listaruta.add(new LatLng(-17.79500000000	,-63.13470000000));
        listaruta.add(new LatLng(-17.79510000000	,-63.13410000000));
        listaruta.add(new LatLng(-17.79440000000	,-63.13400000000));
        listaruta.add(new LatLng(-17.79380000000	,-63.13400000000));
        listaruta.add(new LatLng(-17.79370000000	,-63.13460000000));
        listaruta.add(new LatLng(-17.79310000000	,-63.13450000000));
        listaruta.add(new LatLng(-17.79250000000	,-63.13450000000));
        listaruta.add(new LatLng(-17.79170000000	,-63.13440000000));
        listaruta.add(new LatLng(-17.79150000000	,-63.13580000000));
        listaruta.add(new LatLng(-17.79150000000	,-63.13660000000));
        listaruta.add(new LatLng(-17.79130000000	,-63.13800000000));
        listaruta.add(new LatLng(-17.79130000000	,-63.13870000000));
        listaruta.add(new LatLng(-17.79120000000	,-63.13990000000));
        listaruta.add(new LatLng(-17.79110000000	,-63.14030000000));
        listaruta.add(new LatLng(-17.79080000000	,-63.14130000000));
        listaruta.add(new LatLng(-17.79030000000	,-63.14240000000));
        listaruta.add(new LatLng(-17.79010000000	,-63.14310000000));
        listaruta.add(new LatLng(-17.78990000000	,-63.14390000000));
        listaruta.add(new LatLng(-17.79000000000	,-63.14490000000));
        listaruta.add(new LatLng(-17.78960000000	,-63.14660000000));
        listaruta.add(new LatLng(-17.78960000000	,-63.14710000000));
        listaruta.add(new LatLng(-17.78920000000	,-63.14810000000));
        listaruta.add(new LatLng(-17.78870000000	,-63.14800000000));
        listaruta.add(new LatLng(-17.78760000000	,-63.14790000000));
        listaruta.add(new LatLng(-17.78720000000	,-63.14790000000));
        listaruta.add(new LatLng(-17.78600000000	,-63.14780000000));
        listaruta.add(new LatLng(-17.78600000000	,-63.14780000000));
        listaruta.add(new LatLng(-17.78540000000	,-63.14740000000));
        listaruta.add(new LatLng(-17.78530000000	,-63.14780000000));
        listaruta.add(new LatLng(-17.78480000000	,-63.14810000000));
        listaruta.add(new LatLng(-17.78530000000	,-63.14830000000));
        listaruta.add(new LatLng(-17.78570000000	,-63.14890000000));
        listaruta.add(new LatLng(-17.78560000000	,-63.15010000000));
        listaruta.add(new LatLng(-17.78560000000	,-63.15050000000));
        listaruta.add(new LatLng(-17.78560000000	,-63.15090000000));
        listaruta.add(new LatLng(-17.78560000000	,-63.15170000000));
        listaruta.add(new LatLng(-17.78520000000	,-63.15260000000));
        listaruta.add(new LatLng(-17.78530000000	,-63.15300000000));
        listaruta.add(new LatLng(-17.78550000000	,-63.15370000000));
        listaruta.add(new LatLng(-17.78540000000	,-63.15490000000));
        listaruta.add(new LatLng(-17.78540000000	,-63.15610000000));
        listaruta.add(new LatLng(-17.78510000000	,-63.15720000000));
        listaruta.add(new LatLng(-17.78470000000	,-63.15740000000));
        listaruta.add(new LatLng(-17.78460000000	,-63.15820000000));
        listaruta.add(new LatLng(-17.78490000000	,-63.15930000000));
        listaruta.add(new LatLng(-17.78510000000	,-63.16030000000));
        listaruta.add(new LatLng(-17.78550000000	,-63.16060000000));
        listaruta.add(new LatLng(-17.78590000000	,-63.16130000000));
        listaruta.add(new LatLng(-17.78590000000	,-63.16130000000));
        listaruta.add(new LatLng(-17.78640000000	,-63.16160000000));
        listaruta.add(new LatLng(-17.78710000000	,-63.16230000000));
        listaruta.add(new LatLng(-17.78760000000	,-63.16280000000));
        listaruta.add(new LatLng(-17.78790000000	,-63.16320000000));
        listaruta.add(new LatLng(-17.78770000000	,-63.16360000000));
        listaruta.add(new LatLng(-17.78750000000	,-63.16400000000));
        listaruta.add(new LatLng(-17.78720000000	,-63.16450000000));
        listaruta.add(new LatLng(-17.78700000000	,-63.16540000000));
        listaruta.add(new LatLng(-17.78660000000	,-63.16650000000));
        listaruta.add(new LatLng(-17.78610000000	,-63.16790000000));
        listaruta.add(new LatLng(-17.78540000000	,-63.16930000000));
        listaruta.add(new LatLng(-17.78490000000	,-63.17020000000));
        listaruta.add(new LatLng(-17.78480000000	,-63.17150000000));
        listaruta.add(new LatLng(-17.78480000000	,-63.17180000000));
        listaruta.add(new LatLng(-17.78400000000	,-63.17190000000));
        listaruta.add(new LatLng(-17.78310000000	,-63.17200000000));
        listaruta.add(new LatLng(-17.78250000000	,-63.17220000000));
        listaruta.add(new LatLng(-17.78200000000	,-63.17230000000));
        listaruta.add(new LatLng(-17.78090000000	,-63.17240000000));
        listaruta.add(new LatLng(-17.78020000000	,-63.17250000000));
        listaruta.add(new LatLng(-17.77960000000	,-63.17260000000));
        listaruta.add(new LatLng(-17.77970000000	,-63.17400000000));
        listaruta.add(new LatLng(-17.77990000000	,-63.17500000000));
        listaruta.add(new LatLng(-17.78010000000	,-63.17670000000));
        listaruta.add(new LatLng(-17.78030000000	,-63.17770000000));
        listaruta.add(new LatLng(-17.78050000000	,-63.17880000000));
        listaruta.add(new LatLng(-17.78050000000	,-63.17990000000));
        listaruta.add(new LatLng(-17.78060000000	,-63.18080000000));
        listaruta.add(new LatLng(-17.78070000000	,-63.18180000000));
        listaruta.add(new LatLng(-17.78080000000	,-63.18290000000));
        listaruta.add(new LatLng(-17.78090000000	,-63.18400000000));
        listaruta.add(new LatLng(-17.78100000000	,-63.18510000000));
        listaruta.add(new LatLng(-17.78100000000	,-63.18590000000));
        listaruta.add(new LatLng(-17.78110000000	,-63.18730000000));
        listaruta.add(new LatLng(-17.78120000000	,-63.18880000000));
        listaruta.add(new LatLng(-17.78060000000	,-63.18870000000));
        listaruta.add(new LatLng(-17.77970000000	,-63.18860000000));
        listaruta.add(new LatLng(-17.77900000000	,-63.18840000000));
        listaruta.add(new LatLng(-17.77830000000	,-63.18820000000));
        listaruta.add(new LatLng(-17.77750000000	,-63.19000000000));
        listaruta.add(new LatLng(-17.77640000000	,-63.18930000000));
        listaruta.add(new LatLng(-17.77530000000	,-63.18860000000));
        listaruta.add(new LatLng(-17.77480000000	,-63.18810000000));
        listaruta.add(new LatLng(-17.77430000000	,-63.18890000000));
        listaruta.add(new LatLng(-17.77380000000	,-63.18910000000));
        listaruta.add(new LatLng(-17.77380000000	,-63.18940000000));
        listaruta.add(new LatLng(-17.77360000000	,-63.19100000000));
        listaruta.add(new LatLng(-17.77340000000	,-63.19240000000));
        listaruta.add(new LatLng(-17.77320000000	,-63.19410000000));
        listaruta.add(new LatLng(-17.77310000000	,-63.19550000000));
        listaruta.add(new LatLng(-17.77280000000	,-63.19770000000));
        listaruta.add(new LatLng(-17.77250000000	,-63.19990000000));
        listaruta.add(new LatLng(-17.77240000000	,-63.20120000000));
        listaruta.add(new LatLng(-17.77200000000	,-63.20240000000));
        listaruta.add(new LatLng(-17.77200000000	,-63.20270000000));
        listaruta.add(new LatLng(-17.77210000000	,-63.20320000000));
        listaruta.add(new LatLng(-17.77190000000	,-63.20430000000));
        listaruta.add(new LatLng(-17.77190000000	,-63.20590000000));
        listaruta.add(new LatLng(-17.77170000000	,-63.20720000000));
        listaruta.add(new LatLng(-17.77150000000	,-63.20790000000));
        listaruta.add(new LatLng(-17.77130000000	,-63.20900000000));
        listaruta.add(new LatLng(-17.77120000000	,-63.21020000000));



    }

    @Override
    public void onLocationChanged(Location location) {
        longitude =location.getLongitude();
        latitude = location.getLatitude();
        LatLng centro= new LatLng(latitude,longitude);
        mMap.addMarker(new MarkerOptions().position(centro).title("aqui esta  nueva ubicacion"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(centro));
        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(centro, 15);
        mMap.moveCamera(camera);


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
}