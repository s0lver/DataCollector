package tamps.cinvestav.records;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DataRecord implements CsvTranslatable {
    protected Date timestamp;
    protected SimpleDateFormat sdf;

    public DataRecord(long timestamp) {
        this.setTimestamp(new Date(timestamp));
        this.sdf = new SimpleDateFormat("dd-MM-yy HH:mm:ss.S");
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    protected String timestampToString() {
        return sdf.format(timestamp);
    }
}
