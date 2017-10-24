package com.db.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.until.errorcode.MAGICCODE;

public class CoinDBManager
{
	protected DBManager dbManager = null;

	public int createCoinInfo(String userID,String keyID)
	{
		dbManager = DBManager.getInstance();
		Connection connection = null;
		String sql = "INSERT INTO coin (person,num,coin_time,coin_key) VALUES (?,?,?,?)";

		try
		{
			connection = dbManager.getConection();
			connection.setAutoCommit(false);
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userID);
			statement.setInt(2, 0);
			statement.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
			statement.setString(4, keyID);
			statement.executeUpdate();

			connection.commit();
		} catch (SQLException e)
		{
			int result = RollBackManager.dealRollback(connection);
			e.printStackTrace();
			return result;
		} finally
		{
			try
			{
				if (null != connection)
				{
					connection.close();
				}

			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return 0;
	}

	public int updateCoin(String userID, String reason, long increase,String keyID)
	{
		dbManager = DBManager.getInstance();
		Connection connection = null;
		String sql1 = "SELECT num FROM coin WHERE person = ? and coin_key = ?";
		String sql2 = "INSERT INTO coin_history (person,num,increase,reason,increase_time,coin_key) VALUES (?,?,?,?,?,?)";
		String sql3 = "update coin set num = ? where person = ? and coin_key = ? ";

		int num = 0;

		try
		{
			connection = dbManager.getConection();
			connection.setAutoCommit(false);
			PreparedStatement statement = connection.prepareStatement(sql1);
			statement.setString(1, userID);
			statement.setString(2, keyID);
			
			ResultSet resultSet = statement.executeQuery();
			connection.commit();

			while (resultSet.next())
			{
				num = resultSet.getInt("num");
			}

			long newCoin = num + increase;

			System.out.println(newCoin);

			if (0 > newCoin)
			{
				return MAGICCODE.COIN_NOT_ENOUGH;
			}

			PreparedStatement statement2 = connection.prepareStatement(sql2);
			statement2.setString(1, userID);
			statement2.setLong(2, newCoin);
			statement2.setLong(3, increase);
			statement2.setString(4, reason);
			statement2.setTimestamp(5, new java.sql.Timestamp(new java.util.Date().getTime()));
			statement2.setString(6, keyID);
			statement2.executeUpdate();

			PreparedStatement statement3 = connection.prepareStatement(sql3);
			statement3.setLong(1, newCoin);
			statement3.setString(2, userID);
			statement3.setString(3, keyID);
			statement3.executeUpdate();

			connection.commit();
		} catch (SQLException e)
		{
			int result = RollBackManager.dealRollback(connection);
			e.printStackTrace();
			return result;
		} finally
		{
			try
			{
				if (null != connection)
				{
					connection.close();
				}

			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return MAGICCODE.OK;

	}

	public int checkUserExist(String userID,String keyID)
	{

		dbManager = DBManager.getInstance();
		Connection connection = null;
		String sql1 = "SELECT person FROM coin WHERE person = ? and coin_key = ?";
		PreparedStatement statement;
		try
		{
			connection = dbManager.getConection();
			connection.setAutoCommit(true);
			statement = connection.prepareStatement(sql1);
			statement.setString(1, userID);
			statement.setString(2, keyID);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next())
			{
				String name = resultSet.getString("person");
				if (null != name)
				{
//					System.out.println("COIN_SYSTEM_CREATED:"+name);
					return MAGICCODE.COIN_SYSTEM_CREATED;
				}
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (null != connection)
				{
					connection.close();
				}

			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		return MAGICCODE.COIN_SYSTEM_NOT_CREATE;

	}

	public String queryLastSignDate(String userID,String keyID)
	{
		dbManager = DBManager.getInstance();
		Connection connection = null;
		String sql1 = "SELECT increase_time FROM coin_history WHERE person = ? and reason = ? and coin_key =? order by increase_time desc limit ?,?";
		PreparedStatement statement;
		String lastDate = null;
		try
		{
			connection = dbManager.getConection();
			connection.setAutoCommit(true);
			statement = connection.prepareStatement(sql1);
			statement.setString(1, userID);
			statement.setString(2, "sign");
			statement.setString(3, keyID);
			statement.setInt(4, 0);
			statement.setInt(5, 1);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next())
			{
				Date date = new java.util.Date(resultSet.getDate("increase_time").getTime());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				lastDate = sdf.format(date);
				return lastDate;
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (null != connection)
				{
					connection.close();
				}

			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		return lastDate;
	}

	public int queryCoinNum(String person,String keyID)
	{
		dbManager = DBManager.getInstance();
		Connection connection = null;
		String sql = "SELECT num FROM coin WHERE person = ? and coin_key = ?";
		PreparedStatement statement;
		int num = 0;
		try
		{
			connection = dbManager.getConection();
			connection.setAutoCommit(true);
			statement = connection.prepareStatement(sql);
			statement.setString(1, person);
			statement.setString(2, keyID);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next())
			{
				num = resultSet.getInt("num");
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
			return MAGICCODE.DB_ERROR;
		} finally
		{
			try
			{
				if (null != connection)
				{
					connection.close();
				}

			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		System.out.println("COIN NUM:"+num);
		
		return num;
	}

//	public int dealRollback(Connection connection)
//	{
//		int result = 0;
//		try
//		{
//			connection.rollback();
//
//		} catch (SQLException e1)
//		{
//			result = MAGICCODE.DB_ERROR;
//			e1.printStackTrace();
//		}
//		return result;
//
//	}

}
