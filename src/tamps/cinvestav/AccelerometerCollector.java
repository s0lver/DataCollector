package tamps.cinvestav;

import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometerCollector {
    private SensorEventListener accelerometerEventListener;
    private SensorManager sensorManager;

    public AccelerometerCollector(Context context) {

        this.accelerometerEventListener = createAccelerometerListener();
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(this.accelerometerEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stopReadings() {
        sensorManager.unregisterListener(this.accelerometerEventListener);
    }

    private void saveToFile(AccelerometerRegister accelerometerRegister) {
        throw new RuntimeException("Not implemented yet");
    }

    private SensorEventListener createAccelerometerListener() {
        return new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                AccelerometerRegister accelerometerRegister = new AccelerometerRegister(
                        sensorEvent.values[0],
                        sensorEvent.values[1],
                        sensorEvent.values[2],
                        sensorEvent.timestamp
                );

                saveToFile(accelerometerRegister);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }
}
