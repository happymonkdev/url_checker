import utils.HandlePropertyFile;
import utils.LogIt;
import utils.ReadCSV;
import utils.WriteCSV;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class Main {

    static Logger logger = LogIt.getLogger(Main.class.getName());
    static HandlePropertyFile handlePropertyFile;
    static {
        try {
            handlePropertyFile = new HandlePropertyFile();
        }catch (NullPointerException npe){
            logger.info("--> No configuration file 'config.properties' found ");
        }
    }

    public static void main(String as[]) throws IOException {
        int urlColumnNumber;
        String csvFileName;

        try {
            csvFileName = handlePropertyFile.getCsvFileName();
        } catch (NullPointerException er) {
            csvFileName = "urls.csv";
            logger.info("No file specified in properties file therefore default file name is " + csvFileName);
        }

        if (csvFileName.length() == 0) {
            logger.info("No file specified in properties file therefore default file name is " + csvFileName);
            csvFileName = "urls.csv";
        }
        try {
            urlColumnNumber = Integer.parseInt(handlePropertyFile.getSheetURLColumnNumber());
        } catch (NumberFormatException e) {
            urlColumnNumber = 0;
            logger.info("there is no column specified so default column is 0th" + urlColumnNumber);
        }
       catch (NullPointerException npe){
           urlColumnNumber = 0;
           logger.info("missing config file");
       }

        ReadCSV readCSV = null;
        String fileName = System.getProperty("user.dir") + File.separator + csvFileName;
        try {
            readCSV = new ReadCSV(fileName);
        } catch (FileNotFoundException exp) {
            logger.info("Either add file urls.csv with urls at 0th column or as per properties file");
        }
        List<String> urls = null;
        try {
            urls = readCSV.readUrlFromCSV(urlColumnNumber);
        } catch (ArrayIndexOutOfBoundsException o) {
            logger.info("Make sure the correct column index is specified to pick url from file " + fileName);
        }
        logger.info("if list urls is null means its has not read the csv file completely");
        logger.info("if list urls size is 0 .. then please check the for correct column in csv file");
        String[] header = {"URL", "response-code"};
        WriteCSV writeCSV = new WriteCSV();
        int j = 0;
        HashMap<String, String[]> hmap = new HashMap<String, String[]>();
        hmap.put(String.valueOf(j++), header);
        for (int i = 0; i < urls.size(); i++, j++) {
            int t = getHttpUrlConnection(urls.get(i)).getResponseCode();
            String[] data = {urls.get(i), String.valueOf(t)};
            hmap.put(String.valueOf(j), data);
            logger.info(i + " - " + Arrays.toString(data));
        }
        writeCSV.writeDataToCSV(hmap);
    }

    public static HttpURLConnection getHttpUrlConnection(String inputUrl) throws IOException {

        URL url = new URL(inputUrl);
        // HttpURLConnection connection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // connection.setRequestProperty("Connection", "close");
        connection.setRequestMethod("GET");
        connection.connect();
        return connection;
    }
}
