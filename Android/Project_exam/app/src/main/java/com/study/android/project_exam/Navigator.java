package com.study.android.project_exam;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Navigator extends AppCompatActivity {
    private static final String TAG = "lecture";

    SupportMapFragment mapFragment;
    GoogleMap map;

    MarkerOptions myLocationMarker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d(TAG,"GoogleMap is ready");
                map = googleMap;
                showCurrentLocation();
            }
        });
        try{
            MapsInitializer.initialize(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void callBtnClicked(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:01090497786"));
        startActivity(intent);
    }
    public void emailBtnClicked(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:yh9189@gmail.com"));
        startActivity(intent);
    }

    public void naviBtnClicked(View v){
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }else {
            requestMyLocation();
        }
    }

    private void requestMyLocation(){
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        try{
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location!=null){
                roadmap(location);
                Log.d(TAG,"내 위치 : "+location.getLatitude()+","+location.getLongitude());
            }
        }catch (SecurityException e){
            e.printStackTrace();
            Log.d(TAG,"에러발생 : "+e.getMessage());
        }
    }
    private void roadmap(Location location){

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/dir/"+latitude+","+longitude+"/37.5492995,126.9187274"));
        startActivity(intent);
    }


    private void showCurrentLocation(){
        LatLng curPoint = new LatLng(37.5492995,126.9187274);
        //map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint,15));
        //애니메이션 있고 없음은 move or animate로 구분됨
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(curPoint,15));
        showMyLocationMarker();
    }

    private void showMyLocationMarker(){
        if(myLocationMarker==null){
            myLocationMarker = new MarkerOptions();
            myLocationMarker.position(new LatLng(37.5492995,126.9187274));
            myLocationMarker.title("PLACIDO COFFEE\n");
            myLocationMarker.snippet("어서오세요!");
            myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
            map.addMarker(myLocationMarker);
        }else {
            myLocationMarker.position(new LatLng(37.5492995,126.9187274));
        }
    }

    protected void onPause(){
        super.onPause();
    }

    protected void onResume(){
        super.onResume();
    }
}
