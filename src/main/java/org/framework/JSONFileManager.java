package org.framework;

import java.io.FileNotFoundException;
import java.io.FileReader;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

public class JSONFileManager {

    private String jsonFilePath;
    private FileReader reader = null;

    public JSONFileManager(String jsonFilePath){
        this.jsonFilePath = jsonFilePath;
        initializeReader();
    }

    public String getTestData(String jsonPath){
        Object testData = getJsonTestData(jsonPath);
        return testData != null ? String.valueOf(testData) : null;
    }

    private Object getJsonTestData(String jsonPath) {
        Object testData = null;
        initializeReader();

        try {
            JsonObject jsonObject = new JsonParser().parse(reader).getAsJsonObject();
            testData = JsonPath.read(String.valueOf(jsonObject), jsonPath);
        } catch (PathNotFoundException ex) {
            System.out.println("Couldn't find the desired path [" + jsonPath + "]");
            throw new PathNotFoundException(ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return testData;
    }

    private void initializeReader(){
        reader = null;

        try {
            reader = new FileReader(jsonFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find the desired file [" + jsonFilePath + "]");
            throw new RuntimeException(e);
        }
    }
}
