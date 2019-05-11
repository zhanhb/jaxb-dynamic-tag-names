
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;

@SuppressWarnings("unchecked")
@XmlType
public class SysParamEntriesWrapper {

    /**
     * <p>
     * Here is the tricky part:
     * <ul>
     * <li>When this <code>SysParamEntriesWrapper</code> is created by yourself,
     * objects stored in this <code>entries</code> list is of type
     * SystemParamEntry</li>
     * <li>Yet during the unmarshalling process, this
     * <code>SysParamEntriesWrapper</code> is created by the JAXBContext, thus
     * objects stored in the <code>entries</code> is of type Element
     * actually.</li>
     * </ul>
     * </p>
     */
    private List<JAXBElement<SysParamEntry>> entries = new ArrayList<>();

    public SysParamEntriesWrapper() {
    }

    public void addEntry(String name, String value, String attr, String desc) {
        addEntry(new SysParamEntry(name, value, attr, desc));
    }

    public void addEntry(String name, String value) {
        addEntry(new SysParamEntry(name, value));
    }

    public void addEntry(SysParamEntry entry) {
        JAXBElement<SysParamEntry> bean = new JAXBElement<>(new QName("", entry.getName()), SysParamEntry.class, entry);
        entries.add(bean);
    }

    @XmlAnyElement
    public List<JAXBElement<SysParamEntry>> getEntries() {
        return entries;
    }

    public void setEntries(List<JAXBElement<SysParamEntry>> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "SysParammEntriesWrapper [entries=" + toMap() + "]";
    }

    public Map<String, SysParamEntry> toMap() {
        Map<String, SysParamEntry> retval = new HashMap<>();
        List<?> entries = this.entries;
        entries.stream().map(SysParamEntriesWrapper::convertToParamEntry).
                forEach(entry -> retval.put(entry.getName(), entry));;
        return retval;
    }

    private static SysParamEntry convertToParamEntry(Object entry) {
        String name = extractName(entry);
        String attr = extractAttr(entry);
        String desc = extractDesc(entry);
        String value = extractValue(entry);
        return new SysParamEntry(name, value, attr, desc);
    }

    @SuppressWarnings("unchecked")
    private static String extractName(Object entry) {
        return extractPart(entry, nameExtractors).orElse("");
    }

    @SuppressWarnings("unchecked")
    private static String extractAttr(Object entry) {
        return extractPart(entry, attrExtractors).orElse("");
    }

    @SuppressWarnings("unchecked")
    private static String extractDesc(Object entry) {
        return extractPart(entry, descExtractors).orElse("");
    }

    @SuppressWarnings("unchecked")
    private static String extractValue(Object entry) {
        return extractPart(entry, valueExtractors).orElse("");
    }

    private static <ObjType, RetType> Optional<RetType> extractPart(ObjType obj, Map<Class<?>, Function<? super ObjType, RetType>> extractFuncs) {
        for (Class<?> clazz : extractFuncs.keySet()) {
            if (clazz.isInstance(obj)) {
                return Optional.ofNullable(extractFuncs.get(clazz).apply(obj));
            }
        }
        return Optional.empty();
    }

    private static final Map<Class<?>, Function<? super Object, String>> nameExtractors = new HashMap<>();
    private static final Map<Class<?>, Function<? super Object, String>> attrExtractors = new HashMap<>();
    private static final Map<Class<?>, Function<? super Object, String>> descExtractors = new HashMap<>();
    private static final Map<Class<?>, Function<? super Object, String>> valueExtractors = new HashMap<>();

    static {
        nameExtractors.put(JAXBElement.class, jaxb -> ((JAXBElement<SysParamEntry>) jaxb).getName().getLocalPart());
        nameExtractors.put(Element.class, ele -> ((Element) ele).getLocalName());
        attrExtractors.put(JAXBElement.class, jaxb -> ((JAXBElement<SysParamEntry>) jaxb).getValue().getAttr());
        attrExtractors.put(Element.class, ele -> ((Element) ele).getAttribute("attr"));
        descExtractors.put(JAXBElement.class, jaxb -> ((JAXBElement<SysParamEntry>) jaxb).getValue().getDesc());
        descExtractors.put(Element.class, ele -> ((Element) ele).getAttribute("desc"));
        valueExtractors.put(JAXBElement.class, jaxb -> ((JAXBElement<SysParamEntry>) jaxb).getValue().getValue());
        valueExtractors.put(Element.class, ele -> ((Element) ele).getTextContent());
    }

}
