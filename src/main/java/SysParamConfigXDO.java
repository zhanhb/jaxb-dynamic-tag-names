
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "sys-config")
public class SysParamConfigXDO {

    private SysParamEntries sysParams = new SysParamEntries();

    public SysParamConfigXDO() {
    }

    public void addSysParam(String name, String value, String attr, String desc) {
        sysParams.addEntry(name, value, attr, desc);
    }

    @XmlElement(name = "sys-params")
    @XmlJavaTypeAdapter(SysParamEntriesAdapter.class)
    public SysParamEntries getSysParams() {
        return sysParams;
    }

    public void setSysParams(SysParamEntries sysParams) {
        this.sysParams = sysParams;
    }

    @Override
    public String toString() {
        return "SysParamConfigXDO [sysParams=" + sysParams + "]";
    }

}
