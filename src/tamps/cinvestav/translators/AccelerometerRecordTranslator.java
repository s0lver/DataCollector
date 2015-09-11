package tamps.cinvestav.translators;

import android.hardware.SensorEvent;
import tamps.cinvestav.records.AccelerometerRecord;
import tamps.cinvestav.records.DataRecord;

import java.util.Date;

public class AccelerometerRecordTranslator implements SensorRecordTranslator {
    @Override
    public DataRecord translate(SensorEvent sensorEvent) {
        DataRecord accelerometerRecord = new AccelerometerRecord(
                sensorEvent.values[0],
                sensorEvent.values[1],
                sensorEvent.values[2],
                System.currentTimeMillis());
        //sensorEvent.timestamp);

        return accelerometerRecord;
    }
}

