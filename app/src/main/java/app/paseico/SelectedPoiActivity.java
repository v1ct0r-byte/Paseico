package app.paseico;

import androidx.fragment.app.FragmentActivity;
import app.paseico.data.PointOfInterest;
import app.paseico.data.Route;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SelectedPoiActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private PointOfInterest selectedPoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_poi);

        // Obtain the PointOfInterest selected in RouteInformationActivity
        selectedPoi = (PointOfInterest) getIntent().getExtras().get("poi");
        Log.d("selectedPoi", selectedPoi.getName());


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

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

        // Add a marker in selectedPoi position and move the camera
        LatLng selectedPoiPosition = new LatLng(selectedPoi.getLatitude(), selectedPoi.getLongitude());
        mMap.addMarker(new MarkerOptions().position(selectedPoiPosition).title(selectedPoi.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(selectedPoiPosition));

    }
}