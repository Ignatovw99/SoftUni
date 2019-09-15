package softuni.workshop.util;

import javax.xml.bind.JAXBException;

public interface XmlParser {

    <T> T convertXmlToObject(Class<T> clazz, String path) throws JAXBException;

    <T> String convertObjectToXml(T entity) throws JAXBException;
}
