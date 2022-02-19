package com.example.museumrest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class MuseumApi {
    private static final String PAINTING_URL = "http://127.0.0.1:8000/api/paintings";
    private static final String STATUES_URL = "http://127.0.0.1:8000/api/statues";

    public static List<Painting> getPaintings() throws IOException {
        Response response = RequestHandler.get(PAINTING_URL);
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        Type type = new TypeToken<List<Painting>>(){}.getType();
        return jsonConvert.fromJson(json,type);
    }
}
