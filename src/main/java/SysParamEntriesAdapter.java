
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SysParamEntriesAdapter extends XmlAdapter<SysParamEntriesWrapper, SysParamEntries> {

    @Override
    public SysParamEntries unmarshal(SysParamEntriesWrapper v) throws Exception {
        SysParamEntries retval = new SysParamEntries();
        v.toMap().values().stream().forEach(retval::addEntry);
        return retval;
    }

    @Override
    public SysParamEntriesWrapper marshal(SysParamEntries v) throws Exception {
        SysParamEntriesWrapper entriesWrapper = new SysParamEntriesWrapper();
        v.getEntries().forEach(entriesWrapper::addEntry);
        return entriesWrapper;
    }
}
