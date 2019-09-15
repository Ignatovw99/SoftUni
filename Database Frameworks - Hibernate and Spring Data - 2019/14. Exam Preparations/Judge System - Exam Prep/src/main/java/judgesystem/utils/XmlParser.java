package judgesystem.utils;

public interface XmlParser {

    <T> T convertFromXml(Class<T> clazz, String xmlContent);
}
