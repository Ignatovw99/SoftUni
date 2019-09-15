package softuni.workshop.util;

public interface JsonParser {

    <T> String convertEntityToJson(T entity);

    <T> T convertEntityFromJson(String json, Class<T> clazz);
}