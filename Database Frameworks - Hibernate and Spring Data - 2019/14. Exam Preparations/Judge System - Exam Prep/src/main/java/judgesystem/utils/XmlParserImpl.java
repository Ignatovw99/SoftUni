package judgesystem.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class XmlParserImpl implements XmlParser {

    @SuppressWarnings(value = "unchecked")
    @Override
    public <T> T convertFromXml(Class<T> clazz, String xmlContent) {
        T entity = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            entity = (T) unmarshaller.unmarshal(new StringReader(xmlContent));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
