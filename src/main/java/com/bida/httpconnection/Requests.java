package com.bida.httpconnection;

import com.bida.httpconnection.domain.Category;
import com.bida.httpconnection.domain.Tag;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Requests {

    public void doPost(int id, String name, Category category, String[] photosURL, Tag[] tags) throws Exception{
        URL url = new URL("https://petstore.swagger.io/v2/pet");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        JSONObject body = generateJSONForPutPets(id, name, category, photosURL, tags);

        System.out.println(body.toString());

        OutputStream os = connection.getOutputStream();
        byte[] input = body.toString().getBytes("utf-8");
        os.write(input, 0, input.length);

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }

    private JSONObject generateJSONForPutPets(int id, String name, Category category, String[] photosURL, Tag[] tags){
        JSONObject body = new JSONObject();
        body.put("id", id);
        body.put("status", "available");
        body.put("name", name);

        JSONObject categoryJSON = new JSONObject();
        categoryJSON.put("id", category.getId());
        categoryJSON.put("name", category.getName());

        body.put("tag", tags);
        body.put("category", categoryJSON);
        body.put("photoUrls", photosURL);

        return body;
    }
}
