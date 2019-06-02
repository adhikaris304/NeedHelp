package com.example.needhelp.map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.needhelp.model.LatitudeLongitude;
import com.example.practice.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class SearchDestinationActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private AutoCompleteTextView etPlace;
    private Button btnSearchD;
    private List<LatitudeLongitude> latitudeLongitudeslist;
    Marker marker;
    CameraUpdate center, zoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_destination);

        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        etPlace=findViewById(R.id.etPlace);
        btnSearchD=findViewById(R.id.btnSearchD);

        fillArrayListAndSetAdapter();

        btnSearchD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etPlace.getText().toString()))
                {
                    etPlace.setError("Please enter a place name");
                    return;
                }

                int position=SearchArrayList(etPlace.getText().toString());
                if (position> -1)
                    loadMap(position);
                else
                    Toast.makeText(SearchDestinationActivity.this, "Location not found by name:"+etPlace.getText().toString(), Toast.LENGTH_SHORT).show();
            }

            private void loadMap(int position) {
                if(marker!=null)
                {
                    marker.remove();
                }
                double latitude=latitudeLongitudeslist.get(position).getLat();
                double longitude=latitudeLongitudeslist.get(position).getLon();
                String marker=latitudeLongitudeslist.get(position).getMarker();
                center=CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude));
                zoom=CameraUpdateFactory.zoomTo(17);

                marker=mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(marker));
                mMap.moveCamera(center);
                mMap.animateCamera(zoom);
                mMap.getUiSettings().setZoomControlsEnabled(true);

            }
        });


    }

    private void fillArrayListAndSetAdapter(){
        latitudeLongitudeslist=new ArrayList<>();
        latitudeLongitudeslist.add(new LatitudeLongitude(27.7134481, 85.3241922, "nagpokhari"));
        latitudeLongitudeslist.add(new LatitudeLongitude(27.7181749, 85.33173212, "nagpokhari"));
        latitudeLongitudeslist.add(new LatitudeLongitude(27.7127827, 85.3265391, "nagpokhari"));

        String[] data=new String[latitudeLongitudeslist.size()];

        for (int i =0, i<data.length; i++){
            data[i]=latitudeLongitudeslist.get(i).getMarker();
        }

        ArrayAdapter<String> adapter =new ArrayAdapter<>(SearchDestinationActivity.this,android.R.layout.simple_list_item_1,
                data);

        etPlace.setAdapter(adapter);
        etPlace.setThreshold(1);
    }

    public int SearchArrayList(String name){
        for (int i=0; i<latitudeLongitudeslist.size(); i++){
            if(latitudeLongitudeslist.get(i).getMarker().contains(name)){
                return i;
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        center= CameraUpdateFactory.newLatLng(new LatLng(27.7134481, 85.3241922));
        zoom=CameraUpdateFactory.zoomTo(15);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }


}
