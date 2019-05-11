
import java.util.ArrayList;
import java.util.List;

public class SysParamEntries {

    private List<SysParamEntry> entries = new ArrayList<>();

    public SysParamEntries() {
    }

    public SysParamEntries(List<SysParamEntry> entries) {
        super();
        this.entries = entries;
    }

    public void addEntry(SysParamEntry entry) {
        entries.add(entry);
    }

    public void addEntry(String name, String value) {
        addEntry(name, value, "C");
    }

    public void addEntry(String name, String value, String attr) {
        addEntry(name, value, attr, "");
    }

    public void addEntry(String name, String value, String attr, String desc) {
        entries.add(new SysParamEntry(name, value, attr, desc));
    }

    public List<SysParamEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<SysParamEntry> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "SystemParamEntries [entries=" + entries + "]";
    }
}
