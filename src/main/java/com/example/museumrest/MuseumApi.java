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

    public static List<Statue> getStatues() throws IOException {
        Response response = RequestHandler.get(STATUES_URL);
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        Type type = new TypeToken<List<Statue>>(){}.getType();
        return jsonConvert.fromJson(json,type);
    }

    public static Painting addPainting(Painting newP) throws IOException {
        Gson jsonConvert = new Gson();
        String paintingJson = jsonConvert.toJson(newP);
        Response response = RequestHandler.post(PAINTING_URL, paintingJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return jsonConvert.fromJson(json, Painting.class);
    }

    public static Statue addStatue(Statue newStatue) throws IOException {
        Gson jsonConvert = new Gson();
        String statueJson = jsonConvert.toJson(newStatue);
        Response response = RequestHandler.post(STATUES_URL, statueJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return jsonConvert.fromJson(json, Statue.class);
    }

    public static Painting editPainting(Painting toEdit) throws IOException {
        Gson jsonConvert = new Gson();
        String paintingJson = jsonConvert.toJson(toEdit);
        Response response = RequestHandler.put(PAINTING_URL + "/" + toEdit.getId(), paintingJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return jsonConvert.fromJson(json, Painting.class);
    }

    public static Statue editStatue(Statue toEdit) throws IOException {
        Gson jsonConvert = new Gson();
        String statueJson = jsonConvert.toJson(toEdit);
        Response response = RequestHandler.put(STATUES_URL + "/" + toEdit.getId(), statueJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return jsonConvert.fromJson(json, Statue.class);
    }

    public static boolean deletePainting(int id) throws IOException {
        Response response = RequestHandler.delete(PAINTING_URL+ "/" + id);

        Gson jsonConvert = new Gson();
        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return response.getResponseCode() == 204;
    }

    public static boolean deleteStatue(int id) throws IOException {
        Response response = RequestHandler.delete(STATUES_URL+ "/" + id);

        Gson jsonConvert = new Gson();
        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return response.getResponseCode() == 204;
    }
}
