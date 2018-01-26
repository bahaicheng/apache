package timetable;

import java.util.List;

public class ExternalDataEntity {

    private List<String> holidayList;

    private List<String> workList;

    public List<String> getHlist() {
        return holidayList;
    }

    public void setHlist(List<String> hlist) {
        this.holidayList = hlist;
    }

    public List<String> getWlist() {
        return workList;
    }

    public void setWlist(List<String> wlist) {
        this.workList = wlist;
    }
}
