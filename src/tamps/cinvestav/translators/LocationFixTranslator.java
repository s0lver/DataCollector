package tamps.cinvestav.translators;

import android.location.Location;
import tamps.cinvestav.records.DataRecord;
import tamps.cinvestav.records.LocationFixRecord;

public class LocationFixTranslator implements LocationTranslator {
    @Override
    public DataRecord translate(Location location) {
        DataRecord locationFixRecord = new LocationFixRecord(
                location.getTime(),
                location.getLatitude(),
                location.getLongitude(),
                location.getAltitude(),
                location.getAccuracy(),
                location.getSpeed()
        );
        return locationFixRecord;
    }
}
