package edu.sdsc.awesome.awesomeWeb;

public class UniqueID {


    private long uniqID;
    private String dsName;


    public UniqueID(long uniqID, String dsName) {
        this.uniqID = uniqID;
        this.dsName = dsName;
    }

    public long getUniqID() {
        return uniqID;
    }

    public void setUniqID(long uniqID) {
        this.uniqID = uniqID;
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }
}
