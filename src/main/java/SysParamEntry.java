
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType
public class SysParamEntry {

    private String name;
    private String value = "";
    private String attr = "";
    private String desc = "";

    public SysParamEntry() {
    }

    public SysParamEntry(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }

    public SysParamEntry(String name, String value, String attr) {
        super();
        this.name = name;
        this.value = value;
        this.attr = attr;
    }

    public SysParamEntry(String name, String value, String attr, String desc) {
        super();
        this.name = name;
        this.value = value;
        this.attr = attr;
        this.desc = desc;
    }

    @XmlTransient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @XmlAttribute(name = "attr")
    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    @XmlAttribute(name = "desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "SystemParamEntry [name=" + name + ", value=" + value + ", attr=" + attr + ", desc=" + desc + "]";
    }

}
