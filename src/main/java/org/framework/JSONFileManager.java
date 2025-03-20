package org.framework;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

public class JSONFileManager {

    private JsonObject jsonObject;

    public JSONFileManager(String jsonFile){
        initializeObject(jsonFile);
    }

    public String getTestData(String jsonPath){
        Object testData = getJsonTestData(jsonPath);
        return testData != null ? String.valueOf(testData) : null;
    }

    private Object getJsonTestData(String jsonPath) {
        Object testData;

        try {
            testData = JsonPath.read(String.valueOf(jsonObject), jsonPath);
        } catch (PathNotFoundException ex) {
            System.out.println("Couldn't find the desired path [" + jsonPath + "]");
            throw new PathNotFoundException(ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return testData;
    }

    private void initializeObject(String fileName){
        String filePath = PropertiesUtils.getPropertyValue("TESTDATAFILES_PATH") + fileName + ".json";

        try (FileReader reader = new FileReader(filePath)) {
            this.jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find the desired file [" + fileName + "]");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
