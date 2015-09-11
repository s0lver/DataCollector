package tamps.cinvestav;

import android.content.Context;
import android.location.LocationManager;
import tamps.cinvestav.listeners.LocationListener_own;
import tamps.cinvestav.translators.Translator;

public class LocationDataCollector extends DataCollector{
    private LocationManager locationManager;
    private String locationProviderType;
    private LocationListener_own locationListenerOwn;

    public LocationDataCollector(Context context, String locationProviderType, int sizeOfBuffer, String filename, Translator translator) {
        super(context, sizeOfBuffer, filename, translator);
        this.locationProviderType = locationProviderType;
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        this.locationListenerOwn = new LocationListener_own(this, sizeOfBuffer, translator);

    }

    @Override
    public void startReadings() {
        locationManager.requestLocationUpdates(locationProviderType, 0, 0, locationListenerOwn);
    }

    @Override
    public void stopReadings() {
        locationManager.removeUpdates(locationListenerOwn);
        locationListenerOwn = new LocationListener_own(this, sizeOfBuffer, translator);
    }
}
