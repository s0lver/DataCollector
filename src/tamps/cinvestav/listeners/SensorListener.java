package tamps.cinvestav.listeners;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import tamps.cinvestav.DataCollector;
import tamps.cinvestav.records.DataRecord;
import tamps.cinvestav.translators.Translator;

public class SensorListener implements SensorEventListener {
    private DataCollector collector;
    private DataRecord[] buffer;
    private Translator translator;
    private int sizeOfQueue;
    private int index;

    public SensorListener(DataCollector collector, int sizeOfQueue, Translator translator) {
        this.collector = collector;
        this.sizeOfQueue = sizeOfQueue;
        this.buffer = new DataRecord[sizeOfQueue];
        this.translator = translator;
        this.index = 0;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        DataRecord record = translator.translate(sensorEvent);
        buffer[index] = record;
        index++;

        if (index == buffer.length) {
            collector.fullQueue(buffer);
            // Bye 2 old buffer
            buffer = new DataRecord[sizeOfQueue];
            index = 0;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // Nothing for now
    }
}