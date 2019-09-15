package softuni.workshop.util;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;

@Component
public class XmlParserImpl implements XmlParser {

    @Override
    @SuppressWarnings(value = "unchecked")
    public <T> T convertXmlToObject(Class<T> clazz, String path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new File(path));
    }

    @Override
    public <T> String convertObjectToXml(T entity) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(entity.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(entity, stringWriter);
        return stringWriter.toString();
    }
}
