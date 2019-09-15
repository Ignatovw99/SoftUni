package softuni.workshop.util;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonParserImpl implements JsonParser {

    private final Gson gson;

    @Autowired
    public JsonParserImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <T> String convertEntityToJson(T entity) {
        return this.gson.toJson(entity);
    }

    @Override
    public <T> T convertEntityFromJson(String json, Class<T> clazz) {
        return this.gson.fromJson(json, clazz);
    }
}
