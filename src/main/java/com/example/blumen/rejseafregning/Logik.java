package com.example.blumen.rejseafregning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Blumen on 14-05-2016.
 */
public class Logik {

    public static String Bruger = "";
    public static String Pass = "";
    public static String url = "http://ec2-52-39-152-237.us-west-2.compute.amazonaws.com:8080/Rejseafregning/api/";

    public String stringFromURL(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        StringBuilder sb = new StringBuilder();
        String nl = rd.readLine();
        while (nl != null) {
            sb.append(nl + "\n");
            nl = rd.readLine();
        }
        return sb.toString();
    }

    public String putUrl(String url, String urlParameters) throws IOException
    {
        String resp = null;

        URL newURL = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) newURL.openConnection();
        connection.setReadTimeout(10000);
        connection.setConnectTimeout(15000);
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        String requestBody = urlParameters.toString();
        byte[] outputBytes = requestBody.getBytes();
        OutputStream output = connection.getOutputStream();
        output.write(outputBytes);

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = "";
        StringBuilder responseOutput = new StringBuilder();

        while((line = br.readLine()) != null)
        {
            responseOutput.append(line);
        }

        resp = responseOutput.toString();

        output.close();

        return resp;
    }
}
