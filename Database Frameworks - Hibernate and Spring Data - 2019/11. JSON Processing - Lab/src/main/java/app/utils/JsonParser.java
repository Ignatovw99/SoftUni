package app.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

@Component
public final class JsonParser {

    private Gson gson;

    public JsonParser() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    public <T> String toJson(T entity) {
        return this.gson.toJson(entity);
    }

    public <T> T fromJson(String json, Class<T> entityClass) {
        return this.gson.fromJson(json, entityClass);
    }
}
