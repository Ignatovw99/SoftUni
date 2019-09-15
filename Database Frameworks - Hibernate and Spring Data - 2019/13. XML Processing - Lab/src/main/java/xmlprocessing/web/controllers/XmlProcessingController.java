package xmlprocessing.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import xmlprocessing.domain.dto.UserRootDto;
import xmlprocessing.domain.dto.UserXmlDto;
import xmlprocessing.services.UserService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

@Controller
public class XmlProcessingController implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public XmlProcessingController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.exportToXml();
        this.importFromXml();
    }

    private void importFromXml() throws JAXBException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<user_app>\n" +
                "    <users>\n" +
                "        <user>\n" +
                "            <firstName>Pesho</firstName>\n" +
                "            <lastName>Peshov</lastName>\n" +
                "            <age>15</age>\n" +
                "            <cars>\n" +
                "                <car>\n" +
                "                    <name>Audi</name>\n" +
                "                </car>\n" +
                "                <car>\n" +
                "                    <name>Porsche</name>\n" +
                "                </car>\n" +
                "                <car>\n" +
                "                    <name>BMW</name>\n" +
                "                </car>\n" +
                "            </cars>\n" +
                "        </user>\n" +
                "    </users>\n" +
                "</user_app>";

        StringReader reader = new StringReader(xml);

        JAXBContext jaxbContext = JAXBContext.newInstance(UserRootDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        UserRootDto userRootDto = (UserRootDto) unmarshaller.unmarshal(reader);

        System.out.println(userRootDto);
    }

    private void exportToXml() throws JAXBException {
        UserRootDto userRootDto = new UserRootDto();
        List<UserXmlDto> allUsers = this.userService.getAllUsers();
        userRootDto.setUserXmlDtos(this.userService.getAllUsers());

        JAXBContext context = JAXBContext.newInstance(UserRootDto.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(userRootDto, writer);
        System.out.println(writer);
    }
}
