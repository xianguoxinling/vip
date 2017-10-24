package com.model.coin;

public class Coin 
{
    private String ID = null;
    private String name = null;
    private long num = 0; 
    private String createTime = null;
    public String getID()
    {
        return ID;
    }
    public void setID(String iD)
    {
        ID = iD;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public long getNum()
    {
        return num;
    }
    public void setNum(long num)
    {
        this.num = num;
    }
    public String getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }
    
}
