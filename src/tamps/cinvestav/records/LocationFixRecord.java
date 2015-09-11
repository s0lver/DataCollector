package tamps.cinvestav.records;

public class LocationFixRecord extends DataRecord {
    private double latitude;
    private double longitude;
    private double altitude;
    private float accuracy;
    private float speed;

    public LocationFixRecord(long timestamp, double latitude, double longitude,
                             double altitude, float accuracy, float speed) {
        super(timestamp);
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.accuracy = accuracy;
        this.speed = speed;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(latitude)
                .append(',')
                .append(latitude)
                .append(',')
                .append(longitude)
                .append(',')
                .append(altitude)
                .append(',')
                .append(speed)
                .append(',')
                .append(timestampToString());

        return sb.toString();

    }

    @Override
    public String toCsv() {
        return toString();
    }

}
