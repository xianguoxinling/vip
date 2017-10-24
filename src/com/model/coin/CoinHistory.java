package com.model.coin;

public class CoinHistory
{
	private String person = null;
	private int num = 0;
	private int increase  = 0;
	private String reason = null;
	private String date = null;
	
	public String getPerson()
	{
		return person;
	}
	public void setPerson(String person)
	{
		this.person = person;
	}
	public int getNum()
	{
		return num;
	}
	public void setNum(int num)
	{
		this.num = num;
	}
	public int getIncrease()
	{
		return increase;
	}
	public void setIncrease(int increase)
	{
		this.increase = increase;
	}
	public String getReason()
	{
		return reason;
	}
	public void setReason(String reason)
	{
		this.reason = reason;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
