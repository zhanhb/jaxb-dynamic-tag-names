
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.junit.Test;

public class UnmarshalTest {

    @Test
    public void test() throws JAXBException {
        //Unmarshal
        Path xmlFile = Paths.get("path_to_the_saved_xml_file.xml");
        JAXBContext jaxbCtx = JAXBContext.newInstance(SysParamConfigXDO.class, SysParamEntries.class);
        SysParamConfigXDO xdo = (SysParamConfigXDO) jaxbCtx.createUnmarshaller().unmarshal(xmlFile.toFile());
        System.out.println(xdo.toString());
    }

}
