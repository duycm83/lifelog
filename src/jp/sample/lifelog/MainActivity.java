package jp.sample.lifelog;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity {
    private GoogleMap mMap;
    private LocationManager lm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setUpMapIfNeeded();
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        LocationListener ll = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {

                Toast.makeText(getApplicationContext(),
                        location.getLatitude() + " " + location.getLongitude(),
                        Toast.LENGTH_LONG).show();

                mMap.addMarker(new MarkerOptions()
                        .position(
                                new LatLng(location.getLatitude(), location
                                        .getLongitude()))
                        .title("my position")
                        .icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                        location.getLatitude(), location.getLongitude()), 15.0f));

            }

			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO 自動生成されたメソッド・スタブ
				
			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO 自動生成されたメソッド・スタブ
				
			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO 自動生成されたメソッド・スタブ
				
			}
        };
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
        double lon = 139.857777;
        double lat = 35.716903;
//        lon = 139.457069;
//        lat = 35.805854;

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
        		lat, lon), 16.0f));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap != null) {
            return;
        }
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        if (mMap == null) {
            return;
        }
        // Initialize map options. For example:
        // mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
}
