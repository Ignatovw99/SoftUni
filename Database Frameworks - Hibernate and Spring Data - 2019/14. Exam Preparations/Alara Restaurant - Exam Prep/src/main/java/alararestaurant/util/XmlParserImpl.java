package alararestaurant.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;

public class XmlParserImpl implements XmlParser {

    @SuppressWarnings(value = "unchecked")
    @Override
    public <T> T convertXmlToEntity(String xml, Class<T> clazz) {
        T entity = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            entity = (T) unmarshaller.unmarshal(new File(xml));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public <T> String convertEntityToXml(T entity) {
        StringWriter xml = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(entity.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(entity, xml);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xml.toString();
    }
}
