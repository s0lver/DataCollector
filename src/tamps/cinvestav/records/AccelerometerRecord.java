package tamps.cinvestav.records;

public class AccelerometerRecord extends DataRecord implements CsvTranslatable {
    private float x, y, z;

    public AccelerometerRecord(float x, float y, float z, long timestamp) {
        super(timestamp);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(x)
                .append(',')
                .append(y)
                .append(',')
                .append(z)
                .append(',')
                .append(timestampToString());

        return sb.toString();
    }

    public String toCsv() {
        return toString();
    }
}
