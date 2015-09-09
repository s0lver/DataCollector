package tamps.cinvestav;

import java.util.Date;

public class AccelerometerRegister {
    private float x, y, z;
    private Date timestamp;

    public AccelerometerRegister(float x, float y, float z, long timestamp) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.timestamp = new Date(timestamp);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(timestamp.toString())
                .append(',')
                .append(x)
                .append(',')
                .append(y)
                .append(',')
                .append(z);

        return sb.toString();
    }
}
