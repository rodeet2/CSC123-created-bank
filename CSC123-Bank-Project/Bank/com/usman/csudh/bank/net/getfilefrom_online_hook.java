package com.usman.csudh.bank.net;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.usman.csudh.bank.conversionfilereaderonline;
import com.usman.csudh.bank.settings;

public class getfilefrom_online_hook extends conversionfilereaderonline {

    @Override
    public InputStream getInputStream() throws IOException {
        URL url = null;
		try {
			url = new URL(new settings().getWebservice_url());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        StringBuilder rs = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            rs.append(line);
            rs.append("\n");
        }

        System.out.print(rs.toString());

        reader.close();

        return new ByteArrayInputStream(rs.toString().getBytes());
    }
}
