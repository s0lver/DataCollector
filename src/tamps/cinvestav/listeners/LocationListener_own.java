package tamps.cinvestav.listeners;

import android.location.Location;
import android.os.Bundle;
import tamps.cinvestav.DataCollector;
import tamps.cinvestav.records.DataRecord;
import tamps.cinvestav.translators.Translator;

public class LocationListener_own implements android.location.LocationListener{
    private DataCollector collector;
    private DataRecord[] buffer;
    private Translator translator;
    private int sizeOfBuffer;
    private int index;

    public LocationListener_own(DataCollector collector, int sizeOfBuffer, Translator translator) {
        this.collector = collector;
        this.sizeOfBuffer = sizeOfBuffer;
        this.buffer = new DataRecord[sizeOfBuffer];
        this.translator = translator;
        this.index = 0;
    }

    @Override
    public void onLocationChanged(Location location) {
            DataRecord record = translator.translate(location);
            buffer[index] = record;
            index++;

            if (index == buffer.length) {
                collector.fullQueue(buffer);
                // Bye 2 old buffer
                buffer = new DataRecord[sizeOfBuffer];
                index = 0;
            }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {    }

    @Override
    public void onProviderEnabled(String s) {    }

    @Override
    public void onProviderDisabled(String s) {    }
}
