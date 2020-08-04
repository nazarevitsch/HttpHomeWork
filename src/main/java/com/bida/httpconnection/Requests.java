package com.bida.httpconnection;

import com.bida.httpconnection.domain.Category;
import com.bida.httpconnection.domain.Status;
import com.bida.httpconnection.domain.Tag;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.time.Duration;

public class Requests {

    private static Requests requests;

    private Requests(){}

    public void doPostByIdNameStatus(int id, String name, Status status) throws Exception{
        String uri = "https://petstore.swagger.io/v2/pet/" + id;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .timeout(Duration.ofMinutes(1))
                .header("accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("name=" + name + "&status=" + status.getName()))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    public void doPutUpdateExistingPet(int id, String name, Category category, String[] photosURL, Tag[] tags, Status status) throws Exception{
        String uri = "https://petstore.swagger.io/v2/pet";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .timeout(Duration.ofMinutes(1))
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(generateJSONForPutPets(id, name, category, photosURL, tags, status).toString()))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    public void doDeletePetByID(int id) throws Exception{
        String uri = "https://petstore.swagger.io/v2/pet/" + id;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .timeout(Duration.ofMinutes(1))
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    public void doGetByPetID(int id) throws Exception{
        String uri = "https://petstore.swagger.io/v2/pet/" + id;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .timeout(Duration.ofMinutes(1))
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    public void doGetPetsByStatus(Status status) throws Exception{
        String uri = "https://petstore.swagger.io/v2/pet/findByStatus?status=" + status.getName();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .timeout(Duration.ofMinutes(1))
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    public void doPostNewImage(int id, String filePath) throws Exception{
        String uri = "https://petstore.swagger.io/v2/pet/" + id + "/uploadImage";
        byte[] array = Files.readAllBytes(Paths.get(filePath));
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .timeout(Duration.ofMinutes(1))
                .header("accept", "application/json")
                .header("Content-Type", "multipart/form-data")
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get(filePath)))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    public void doPostNewPet(int id, String name, Category category, String[] photosURL, Tag[] tags, Status status) throws Exception{
        String uri = "https://petstore.swagger.io/v2/pet";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .timeout(Duration.ofMinutes(1))
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(generateJSONForPutPets(id, name, category, photosURL, tags, status).toString()))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    private JSONObject generateJSONForPutPets(int id, String name, Category category, String[] photosURL, Tag[] tags, Status status){
        JSONObject body = new JSONObject();
        body.put("id", id);
        body.put("status", status.getName());
        body.put("name", name);

        JSONObject categoryJSON = new JSONObject();
        categoryJSON.put("id", category.getId());
        categoryJSON.put("name", category.getName());

        body.put("tag", tags);
        body.put("category", categoryJSON);
        body.put("photoUrls", photosURL);

        return body;
    }

    public static Requests createRequests(){
        if (requests == null){requests = new Requests();}
        return requests;
    }
}
