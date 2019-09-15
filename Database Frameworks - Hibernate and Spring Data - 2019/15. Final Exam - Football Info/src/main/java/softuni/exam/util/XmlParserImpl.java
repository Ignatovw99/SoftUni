package softuni.exam.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlParserImpl implements XmlParser {

    @SuppressWarnings(value = "unchecked")
    @Override
    public <T> T convertXmlToEntity(Class<T> clazz, String xmlPath) {
        T entity = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            entity = (T) unmarshaller.unmarshal(new File(xmlPath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
