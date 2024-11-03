package com.avactis.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DButils {

	
	public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://127.0.0.1:3306/avactis";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(url, username, password);
    }

    public static List<String[]> getProductData() throws Exception {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM avactis");

        List<String[]> products = new ArrayList<>();
        while (rs.next()) {
            String[] product = {
                rs.getString("category"),
                rs.getString("name"),
                rs.getString("price"),
                rs.getString("quantity")
            };
            products.add(product);
        }
        conn.close();
        return products;
    }
	
}
