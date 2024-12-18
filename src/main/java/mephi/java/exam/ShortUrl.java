package mephi.java.exam;


import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ShortUrl {

    public static String getShortUrl(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        URI uri = URI.create("https://clck.ru/--?url=" + url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        try{
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (Exception e) {
            return "Error occurred while sending GET request: " + e.getMessage();
        }
    }
}
