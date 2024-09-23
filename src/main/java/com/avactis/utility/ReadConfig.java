package com.avactis.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties properties;

    // Constructor to load the properties file
    public ReadConfig() {
        File src = new File("./Configuration/config.properties"); // Path to the config file
        try {
            FileInputStream fis = new FileInputStream(src);
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("Exception occurred while loading config file: " + e.getMessage());
        }
    }

    // Method to get application URL
    public String getApplicationURL() {
        return properties.getProperty("url");
    }

    // Method to get browser type
    public String getBrowser() {
        return properties.getProperty("browser");
    }

    // Method to get username
    public String getAdminURL() {
        return properties.getProperty("ADMIN_URL");
    }

    // Method to get password
    public String getAdminUserName() {
        return properties.getProperty("ADMIN_USER-NAME");
    }

    public String getAdminPassword() {
    	return properties.getProperty("@Vaishnavi#93");
    }
    
}
	

