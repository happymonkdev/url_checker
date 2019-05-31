package utils;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class ReadCSV {
    CSVReader reader;
    static Logger logger = LogIt.getLogger(ReadCSV.class.getName());

    public ReadCSV(String fileName) throws FileNotFoundException {
        reader = new CSVReader(new FileReader(fileName));
    }

    public List<String> readUrlFromCSV(int sheetURLColumnNumber) throws IOException {
        List list = new ArrayList<String>();
        List<String[]> records = reader.readAll();
        Iterator<String[]> iterator = records.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            String[] str = iterator.next();
            String urlString = str[sheetURLColumnNumber];
            System.out.println("Checking for " + ++i + "-->" + urlString);
            if (isValidUrl(urlString)) {
                list.add(urlString);
            } else {
                logger.info("Not a correct URL " + urlString);
            }
        }
        return list;
    }

    public boolean isValidUrl(String url) {
        /* Try creating a valid URL */
        try {
            new URL(url).toURI();
            return true;
        }
        // If there was an Exception
        // while creating URL object
        catch (Exception e) {
            return false;
        }
    }
}