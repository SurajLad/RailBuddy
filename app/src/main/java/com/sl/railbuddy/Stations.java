package com.sl.railbuddy;

public class Stations
{
    String sloc,sname,nop;

    public Stations()
    {
    }
    public Stations(String sloc, String sname, String nop)
    {
        this.sloc = sloc;
        this.sname = sname;
        this.nop = nop;
    }
    public String getSloc()
    {
        return sloc;
    }
    public void setSloc(String sloc)
    {
        this.sloc = sloc;
    }
    public String getSname()
    {
        return sname;
    }
    public void setSname(String sname)
    {
        this.sname = sname;
    }
    public String getNop()
    {
        return nop;
    }
    public void setNop(String nop)
    {
        this.nop = nop;
    }
}
