package org.acme.model;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.jboss.logging.Logger;

public class LogReader {
    public final static String DEFAULT_FILENAME = "dataframe.csv";
    public final static int DEFAULT_MAX_SIZE = 50;
    private static final Logger logger = Logger.getLogger("acme.org");

    private int maxSize = DEFAULT_MAX_SIZE;
    private String filename = DEFAULT_FILENAME;

    public LogReader setMaxSize(int size) {
        this.maxSize = size;
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public LogReader setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public List<Pollution> read() {
        logger.info("Working Directory = " + System.getProperty("user.dir"));
        ReversedLinesFileReader reader;
        List<Pollution> logs = new ArrayList<Pollution>();
        try {
            reader = new ReversedLinesFileReader(new File(filename), Charset.defaultCharset());
            String current;
            while ((current = reader.readLine()) != null && logs.size() < DEFAULT_MAX_SIZE) {
                String [] tokens = current.split(",");
                if(tokens[0].length() > 0){
                    Pollution pollution = new Pollution();
                    for(int i=1; i<11; i++){
                        pollution.setPart(i -1, Integer.parseInt(tokens[i]));
                    }
                    pollution.setTimestamp(Long.parseLong(tokens[11].split("\\.")[0]));
                    logs.add(pollution);
                }
            }
            reader.close();
        } catch (Exception e) {
            logger.warn("I have some problem to read file: " + filename, e);
        }
        return logs;
    }

    public Sample readSample(int index) {
        ReversedLinesFileReader reader;
        Sample sample = new Sample();
        int counter = 0;
        try {
            reader = new ReversedLinesFileReader(new File(filename), Charset.defaultCharset());
            String current;
            while ((current = reader.readLine()) != null && counter < DEFAULT_MAX_SIZE) {
                String [] tokens = current.split(",");
                if(tokens[0].length() > 0){
                    if(counter == 0){
                        sample.setStartTime(Long.parseLong(tokens[11].split("\\.")[0]));
                    }
                    if(counter == (DEFAULT_MAX_SIZE - 1)){
                        sample.setEndTime(Long.parseLong(tokens[11].split("\\.")[0]));
                    }
                    sample.setValue(counter++, Integer.parseInt(tokens[index]));
                }
            }
            reader.close();
        } catch (Exception e) {
            logger.warn("I have some problem to read file: " + filename, e);
        }
        return sample;
    }
}
