package utils;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WriteCSV {
    String outputFile = System.getProperty("user.dir") + File.separator + "outputfile.csv";
    File file = new File(outputFile);

    public void writeDataToCSV(HashMap<String, String[]> outputMapdata) throws IOException {
        // first create file object for file placed at location
        // specified by filepath

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
            // add data to csv
            for (int i = 0; i < outputMapdata.size(); i++) {
                System.out.println(i + " - " + "data to be saved");
                writer.writeNext(outputMapdata.get(String.valueOf(i)));
            }
            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
