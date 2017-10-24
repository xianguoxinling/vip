package com.service.interfaces;

public interface CoinServiceInterface
{
	public int createCoinInfo(String person,String keyID);
	public int updateCoin(String person, String reason, long increase,String keyID);
	public int sign(String person,String keyID);
	public int queryCoinNum(String person,String keyID);
	public int hasSignToday(String person,String keyID);
	
	
}
