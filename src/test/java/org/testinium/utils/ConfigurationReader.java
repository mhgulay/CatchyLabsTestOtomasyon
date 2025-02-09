package org.testinium.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationReader {

    private static JSONObject config;

    static {
        try {
            FileReader reader = new FileReader("config.json");
            StringBuilder jsonContent = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                jsonContent.append((char) i);
            }
            reader.close();
            config = new JSONObject(jsonContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return config.optString(key, null);
    }

    public static String getBrowser() {
        return config.getString("browser");
    }

    public static String getBaseUrl() {
        return config.getString("url");
    }

    public static String getValidUsername() {
        return config.getJSONObject("valid_credentials").getString("username");
    }

    public static String getValidPassword() {
        return config.getJSONObject("valid_credentials").getString("password");
    }

    public static JSONArray getInvalidCredentials() {
        return config.getJSONArray("invalid_credentials");
    }
    public static String getMobileValue() {
        return config.getJSONObject("mobile").getString("value");
    }

    public static String getHeadlessValue() {
        return config.getJSONObject("headless").getString("value");
    }

    public static String getBaseUri() {
        return config.getString("baseUri");
    }
    public static JSONArray getPaths() {
        return config.getJSONArray("path");
    }
}
