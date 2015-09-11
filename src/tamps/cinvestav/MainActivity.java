package tamps.cinvestav;

import android.app.Activity;
import android.hardware.Sensor;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import tamps.cinvestav.translators.AccelerometerRecordTranslator;
import tamps.cinvestav.translators.LocationFixTranslator;

public class MainActivity extends Activity {
    private Button btnReadings;
    private Switch swAccelerometer;
    private Switch swGPS;
    private Spinner spnMobilities;

    private DataCollector collectorAccelerometer;
    private DataCollector collectorLocation;

    private boolean readingLocation;
    private boolean readingAccelerometer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        this.btnReadings = (Button) findViewById(R.id.btnStartReadings);
        this.swAccelerometer = (Switch) findViewById(R.id.swCollectAccelerometer);
        this.swGPS = (Switch) findViewById(R.id.swCollectLocation);
        this.spnMobilities = (Spinner) findViewById(R.id.spnMobilities);

        this.readingAccelerometer = false;
        this.readingLocation = false;

        populateSpinner();
    }

    private void populateSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.mobilities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMobilities.setAdapter(adapter);
    }

    public void clickBtnReadings(View view) {
        String labelStartReadings = getString(R.string.labelStartReadings);
        String labelStopReadings = getString(R.string.labelStopReadings);

        String selectedMobility = spnMobilities.getSelectedItem().toString().toLowerCase();
        selectedMobility = selectedMobility.replace(' ', '_');

        if (btnReadings.getText().equals(labelStartReadings)) {
            // Start reading
            if (swAccelerometer.isChecked()) {
                readingAccelerometer = true;
                collectorAccelerometer = new SensorDataCollector(getApplicationContext(), Sensor.TYPE_ACCELEROMETER,
                        100, "acc-" + selectedMobility, new AccelerometerRecordTranslator());
                collectorAccelerometer.startReadings();
            }
            if (swGPS.isChecked()) {
                readingLocation = true;
                collectorLocation = new LocationDataCollector(getApplicationContext(), LocationManager.GPS_PROVIDER,
                        10, "gps-" + selectedMobility, new LocationFixTranslator());
                collectorLocation.startReadings();
            }
            btnReadings.setText(labelStopReadings);
        } else {
            // Stop reading
            btnReadings.setText(labelStartReadings);
            if (readingAccelerometer) {
                collectorAccelerometer.stopReadings();
            }
            if (readingLocation) {
                collectorLocation.stopReadings();
            }
            readingAccelerometer = false;
            readingLocation = false;
        }
    }
}
