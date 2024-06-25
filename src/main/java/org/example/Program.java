package org.example;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Program {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        Transcript transcript = new Transcript();
        transcript.setAudio_url(HTTP);

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);
        System.out.println(jsonRequest);

        HttpRequest postRequest = HttpRequest.newBuilder().
                uri(new URI("https://api.assemblyai.com/v2/transcript"))
                .header("Authorization",API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(postResponse.body());

        transcript = gson.fromJson(postResponse.body(),Transcript.class);
        System.out.println(transcript.getId());

        HttpRequest getRequest = HttpRequest.newBuilder().
                uri(new URI("https://api.assemblyai.com/v2/transcript/" + transcript.getId()))
                .header("Authorization",API_KEY)
                .build();

        boolean res = true;
        while (res) {
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            transcript = gson.fromJson(getResponse.body(), Transcript.class);

            System.out.println(transcript.getStatus());
            if ("completed".equals(transcript.getStatus()) || "error".equals(transcript.getStatus())){
                res = false;
            }Thread.sleep(1000);
        }

        System.out.println("Transcription completed");
        System.out.println(transcript.getText());


    }
    public static final String API_KEY = "1225f771a7714fd7939f8c20ad052195";
    public static final String HTTP = "https://github.com/yakov152005/JavaApi/blob/main/Thirsty.mp4?raw=true";
}
