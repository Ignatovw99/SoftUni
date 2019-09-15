package alararestaurant.util;

public interface XmlParser {

    <T> T convertXmlToEntity(String xml, Class<T> clazz);

    <T> String convertEntityToXml(T entity);
}
