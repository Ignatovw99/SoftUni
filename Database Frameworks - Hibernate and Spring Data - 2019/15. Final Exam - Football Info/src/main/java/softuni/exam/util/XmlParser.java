package softuni.exam.util;

public interface XmlParser {

    <T> T convertXmlToEntity(Class<T> clazz, String xmlPath);
}
