package data_import;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.List;

public interface CloudDataImporter {
    List<String[]>readDataFromCloud() throws IOException, CsvException;
}
