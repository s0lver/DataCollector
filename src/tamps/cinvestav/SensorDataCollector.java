package tamps.cinvestav;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import tamps.cinvestav.listeners.SensorListener;
import tamps.cinvestav.translators.Translator;

public class SensorDataCollector extends DataCollector{
    private SensorEventListener sensorEventListener;
    private SensorManager sensorManager;
    private Sensor sensor;

    public SensorDataCollector(Context context, int sensorType, int sizeOfBuffer,
                               String fileName, Translator translator) {
        super(context, sizeOfBuffer, fileName, translator);

        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        this.sensor = sensorManager.getDefaultSensor(sensorType);
        this.sensorEventListener = new SensorListener(this, sizeOfBuffer, translator);
    }

    @Override
    public void startReadings() {
        // sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void stopReadings() {
        sensorManager.unregisterListener(this.sensorEventListener);
        sensorEventListener = new SensorListener(this, sizeOfBuffer, translator);
    }
}
