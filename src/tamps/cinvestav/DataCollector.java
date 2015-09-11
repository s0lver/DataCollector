package tamps.cinvestav;

import android.content.Context;
import tamps.cinvestav.records.CsvTranslatable;
import tamps.cinvestav.records.RecordWriter;
import tamps.cinvestav.translators.Translator;

import java.io.FileNotFoundException;

public abstract class DataCollector { // implements QueueListener
    private RecordWriter recordsWriter;
    protected Translator translator;
    protected int sizeOfBuffer;

    public DataCollector(Context context, int sizeOfBuffer, String fileName, Translator translator) {
        this.sizeOfBuffer = sizeOfBuffer;
        this.translator = translator;
        this.recordsWriter = new RecordWriter(fileName);
    }

    public abstract void startReadings();

    public abstract void stopReadings();

    public void fullQueue(CsvTranslatable[] records){
        // TODO Check with thread!
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    recordsWriter.writeFile(records);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        try {
            recordsWriter.writeFile(records);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while writing records :(");
        }
    }
}
