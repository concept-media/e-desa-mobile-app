package lab.aikibo.e_desa;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by tamami on 2/26/18.
 */

public class PanicButton {

    Context mContext;
    LocationManager locationManager;
    Location location;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    PanicButton(Context c) {
        mContext = c;
    }

    public void init() {
        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    @JavascriptInterface
    public void getCoordinate() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(mContext.getPackageManager()) != null) {
            //mContext.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            mContext.startActivity(takePictureIntent);
        }
        Toast.makeText(mContext, "Lokasi : " + location.getLatitude() + " - " + location.getLongitude(), Toast.LENGTH_LONG).show();
        // kirim koordinat ke server
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location loc) {
            //Toast.makeText(MainActivity.this, "Lokasi : " +
            //        location.getLatitude() + " - " + location.getLongitude(), Toast.LENGTH_LONG).show();
            //setGpsParameters(location);
            location = loc;
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

}
