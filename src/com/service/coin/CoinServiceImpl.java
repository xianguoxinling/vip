package com.service.coin;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.db.manager.CoinDBManager;
import com.platform.base.CoinBase;
import com.service.interfaces.CoinServiceInterface;
import com.until.errorcode.MAGICCODE;

public class CoinServiceImpl implements CoinServiceInterface
{
	private CoinDBManager coinDBManager = null;

	public CoinServiceImpl()
	{
		setCoinDBManager(new CoinDBManager());
	}

	@Override
	public int createCoinInfo(String person,String keyID)
	{
		
		int result = MAGICCODE.ERROR;
		
		if (null == person || "".equals(person))
		{
			return result;
		}
		
		result = coinDBManager.checkUserExist(person,keyID);
		if(MAGICCODE.COIN_SYSTEM_CREATED == result)
		{
			return MAGICCODE.OK;
		}
		result = coinDBManager.createCoinInfo(person,keyID);
		if(MAGICCODE.OK != result)
		{
			
		}
		return result;
	}

	@Override
	public int updateCoin(String person, String reason, long increase,String keyID)
	{
		int result = 0;
		if (null == person || "".equals(person))
		{
			return 1;
		}
		
		result = coinDBManager.checkUserExist(person,keyID);
		if(MAGICCODE.COIN_SYSTEM_NOT_CREATE == result)
		{
			result = coinDBManager.createCoinInfo(person,keyID);
		}
		
		result  = coinDBManager.updateCoin(person, reason, increase,keyID);
		if(MAGICCODE.OK != result)
		{
			
		}
		return result ;
	}

	public CoinDBManager getCoinDBManager()
	{
		return coinDBManager;
	}

	public void setCoinDBManager(CoinDBManager coinDBManager)
	{
		this.coinDBManager = coinDBManager;
	}

	public int hasSignToday(String person,String keyID)
	{
		int result = MAGICCODE.OK;
		
		result = coinDBManager.checkUserExist(person,keyID);
		if(MAGICCODE.COIN_SYSTEM_NOT_CREATE == result)
		{
			result = coinDBManager.createCoinInfo(person,keyID);
		}
		
		String dateLastSign = coinDBManager.queryLastSignDate(person,keyID);
		System.out.println("DDDDDD2:"+dateLastSign);
		
		
		
		
		if(null == dateLastSign)
		{
			return MAGICCODE.COID_SIGN_NOT_TODAY;
		}else
		{
			Date date = new java.util.Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateNowStr = sdf.format(date); 
			
			System.out.println("DDDDDD1:"+dateNowStr);
			
			if(dateLastSign.equals(dateNowStr))
			{
				return MAGICCODE.COIN_SIGN_TODAY;
			}
		}
		return MAGICCODE.COID_SIGN_NOT_TODAY;
	}
	
	public int queryCoinNum(String person,String keyID)
	{
		int num = 0;
		int result = MAGICCODE.OK;
		
		result = coinDBManager.checkUserExist(person,keyID);
		if(MAGICCODE.COIN_SYSTEM_NOT_CREATE == result)
		{
			result = coinDBManager.createCoinInfo(person,keyID);
		}
		
		num = coinDBManager.queryCoinNum(person,keyID);
		if(MAGICCODE.DB_ERROR == num)
		{
			
		}
		
		return num;
		
	}
	
	@Override
	public int sign(String person,String keyID)
	{
		
		int result = MAGICCODE.OK;
		
		result = coinDBManager.checkUserExist(person,keyID);
		if(MAGICCODE.COIN_SYSTEM_NOT_CREATE == result)
		{
			System.out.println("COIN SYSTEM HAS CREATE!");
			result = coinDBManager.createCoinInfo(person,keyID);
		}
		int hasSignToday = hasSignToday(person,keyID);
		if(MAGICCODE.COIN_SIGN_TODAY == hasSignToday)
		{
			return MAGICCODE.COIN_SIGN_TODAY;
		}
		
	    result = updateCoin(person,"sign",CoinBase.COINSIGN,keyID);
		if(MAGICCODE.OK != result)
		{
			
		}
		return result;
	}
	
	public static void main(String args[])
	{
		Date date = new java.util.Date();
//		int day = date.toString()
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateNowStr = sdf.format(date); 
		System.out.println(dateNowStr);
	}
}
