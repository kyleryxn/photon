package com.github.kyleryxn.photon.api;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Service
public class GsonService {
    private final Gson gson;

    public GsonService(Gson gson) {
        this.gson = gson;
    }

    public String toJson(Object object) {
        return gson.toJson(object);
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

}
