package tamps.cinvestav.records;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordWriter {
    private String filePath;
    private String fileName;

    /***
     * Returns the default storage mount point
     * @return The default storage mount point
     */
    private String getStoragePath(){
        return Environment.getExternalStorageDirectory().getPath();
    }

    /***
     * Builds the full filename for the file to write records.
     * Builds the path of directories if needed.
     * @return Full path of file to write records later
     */
    private String getFilePath() {
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        SimpleDateFormat  sdf = new SimpleDateFormat("dd-MM-yy_HH-mm-ss");
        Date now = new Date(System.currentTimeMillis());

        StringBuilder sb = new StringBuilder(getStoragePath())
                .append('/')
                .append("context-collector/");

        createDirectoryIfNeeded(sb.toString());

        sb.append(fileName)
                .append(sdf.format(now))
                .append(".csv");

        return sb.toString();
    }

    /***
     * Create a directory path if it doesn't exists
     * @param path Path to be created
     */
    private void createDirectoryIfNeeded(String path) {
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }
    }

    /***
     * Creates a RecordWriter.
     * The filename will be completed with timestamp information
     * @param fileName The filename hint
     */
    public RecordWriter(String fileName) {
        this.fileName = fileName;
        this.filePath = getFilePath();
    }

    /***
     * Writes the specified Array of records to a File
     * @param records Array of CsvTranslatables to write
     * @throws FileNotFoundException
     */
    public void writeFile(CsvTranslatable[] records) throws FileNotFoundException {
        File file = new File(this.filePath);
        PrintWriter pw = new PrintWriter(new FileOutputStream(file));

        for (int i = 0; i < records.length; i++) {
            pw.println(records[i].toCsv());
        }

        pw.close();
    }
}
