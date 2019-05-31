package utils;

import java.io.*;
import java.util.Properties;

public class HandlePropertyFile {
    private Properties prop = new Properties();
    private InputStream input;
    public static String configFile;

    static {
        configFile = System.getProperty("user.dir") + File.separator + "config.properties";
    }

    public HandlePropertyFile() {
        this(configFile);
    }

    public HandlePropertyFile(String fileNameString) {
        try {
            input = new FileInputStream(fileNameString);
        } catch (FileNotFoundException e) {
        }
        try {
            prop.load(input);
            // below loggers can be removed after completion of tetrail integration
        } catch (IOException e) {
        }
    }

    public String getSheetURLColumnNumber() {
        return (String) prop.get("columnNumber");
    }

    public String getCsvFileName() {
        return (String) prop.get("fileName");
    }
}