
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.junit.Test;

public class MarshalTest {

    @Test
    public void test() throws JAXBException {
        //Marshal
        SysParamConfigXDO xdo = new SysParamConfigXDO();
        xdo.addSysParam("ACCESSLOG_FILE_BY", "SYSTEM", "C", "AccessLog file desc");
        xdo.addSysParam("ACCESSLOG_WRITE_MODE", "DB", "D", "");
        xdo.addSysParam("CHANEG_BUTTON_IMAGES", "FALSE", "E", "Button Image URL, eh, boolean value. ...Wait, what?");
        JAXBContext jaxbCtx = JAXBContext.newInstance(SysParamConfigXDO.class, SysParamEntries.class);
        jaxbCtx.createMarshaller().marshal(xdo, System.out);
    }

}
