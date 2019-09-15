package mostwanted.util;

public interface XmlParser {

    <O> O parseXml(Class<O> objectClass, String filePath);
}
