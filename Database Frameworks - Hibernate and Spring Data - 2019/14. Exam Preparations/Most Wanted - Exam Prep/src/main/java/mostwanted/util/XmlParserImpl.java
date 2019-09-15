package mostwanted.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class XmlParserImpl implements XmlParser {

    @SuppressWarnings(value = "unchecked")
    @Override
    public <O> O parseXml(Class<O> objectClass, String filePath) {
        O entity = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            entity = (O) unmarshaller.unmarshal(new FileReader(filePath));
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return entity;
    }
}