package cardealer.util;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class JsonParser {

    private final Gson gson;

    @Autowired
    public JsonParser(Gson gson) {
        this.gson = gson;
    }

    public <T> T parseEntityFromJson(String json, Class<T> tClass) {
        return this.gson.fromJson(json, tClass);
    }

    public <T> String parseEntityToJson(T entity) {
        return this.gson.toJson(entity);
    }
}
