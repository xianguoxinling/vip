package com.db.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.model.coupon.Coupon;
import com.model.coupon.CouponState;
import com.until.errorcode.MAGICCODE;

public class CouponDBManager
{
    protected DBManager dbManager = null;

    public int createCouponInfo(Coupon coupon)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "INSERT INTO coupon (face_value,min_limit,name,magic_key,begin_time,end_time,apply_time) VALUES (?,?,?,?,?,?,?)";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, coupon.getFaceValue());
            statement.setDouble(2, coupon.getMinimumLimit());
            statement.setString(3, coupon.getName());
            statement.setString(4, coupon.getMagic_key());
            statement.setString(5, coupon.getBeginTime());
            statement.setString(6, coupon.getEndTime());
            statement.setTimestamp(7, new java.sql.Timestamp(new java.util.Date().getTime()));
            statement.executeUpdate();

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
        return MAGICCODE.OK;
    }

    public int applyCoupon(String userID,String couponID, String uuid,String keyID)
    {

        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "INSERT INTO coupon_person(userid,couponid,uuid,magic_key,apply_time) VALUES (?,?,?,?,?)";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.parseLong(userID));
            statement.setLong(2, Long.parseLong(couponID));
            statement.setString(3, uuid);
            statement.setString(4, keyID);
            statement.setTimestamp(5, new java.sql.Timestamp(new java.util.Date().getTime()));
            statement.executeUpdate();

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
        return MAGICCODE.OK;

    }

    public int queryStoreCoupon(List<Coupon> couponList,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT * FROM coupon WHERE magic_key = ?";
        PreparedStatement statement;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(1, magicKey);
            ResultSet resultSet = statement.executeQuery();
            DBUntil dbUntil = new DBUntil();
            while (resultSet.next())
            {
                Coupon coupon = dbUntil.queryCouponFromResultSet(resultSet);
                couponList.add(coupon);
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

        return MAGICCODE.OK;
    }

    public int queryStoreCanUseCoupon(List<Coupon> couponList,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT * FROM coupon WHERE magic_key = ? and end_time >= ?";
        PreparedStatement statement;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(1, magicKey);
            statement.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
            ResultSet resultSet = statement.executeQuery();
            DBUntil dbUntil = new DBUntil();
            while (resultSet.next())
            {
                Coupon coupon = dbUntil.queryCouponFromResultSet(resultSet);
                couponList.add(coupon);
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

        return MAGICCODE.OK;
    }
    
    public Coupon queryCoupon(String userID,String uuid,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT * FROM coupon a left join coupon_person b on a.id=b.couponid WHERE a.magic_key = ? and b.userid = ? and b.uuid = ?";
        PreparedStatement statement;
        Coupon coupon = null;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(1, magicKey);
            statement.setLong(2, Long.parseLong(userID));
            statement.setString(3, uuid);
            ResultSet resultSet = statement.executeQuery();
            DBUntil dbUntil = new DBUntil();
            while (resultSet.next())
            {
                coupon = dbUntil.queryCouponLefJoinFromResultSet(resultSet);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
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

        return coupon;
    }
    
    public int queryPersonCoupon(List<Coupon> couponList,String userID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT * FROM coupon a left join coupon_person b on a.id=b.couponid WHERE a.magic_key = ? and b.userid = ? and a.end_time >= ? and b.state=?";
        PreparedStatement statement;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(1, magicKey);
            statement.setString(2, userID);
            statement.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
            statement.setString(4, CouponState.UNUSED);
            ResultSet resultSet = statement.executeQuery();
            DBUntil dbUntil = new DBUntil();
            while (resultSet.next())
            {
                Coupon coupon = dbUntil.queryCouponFromResultSet(resultSet);
                couponList.add(coupon);
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

        return MAGICCODE.OK;
    }
    
    public int updatePersonCouponState(String userID,String state,String uuid,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "update coupon_person set state = ? where userid = ? and uuid =? and magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,state);
            statement.setString(2, userID);
            statement.setString(3, uuid);
            statement.setString(4, magicKey);
            
            statement.executeUpdate();

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
        return MAGICCODE.OK;
    }
    
    public int updateCoupon(Coupon coupon)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "update coupon set face_value = ?, min_limit =?, name=?, begin_time=?, end_time=? where id = ? and magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, coupon.getFaceValue());
            statement.setDouble(2, coupon.getMinimumLimit());
            statement.setString(3, coupon.getName());
            statement.setString(4, coupon.getBeginTime());
            statement.setString(5, coupon.getEndTime());
            statement.setString(6, coupon.getId());
            statement.setString(7, coupon.getMagic_key());
            
            statement.executeUpdate();

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
        return MAGICCODE.OK;
    }
    
    public int deleteCoupon(String couponID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "delete from coupon where id = ? and magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, couponID);
            statement.setString(2, magicKey);
            
            statement.executeUpdate();

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
        return MAGICCODE.OK;
    }
    
    public int queryPersonHasApplyCoupon(String userID,String couponID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT * FROM coupon_person WHERE userid = ? and couponid = ? and magic_key = ?";
        PreparedStatement statement;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.parseLong(userID));
            statement.setLong(2, Long.parseLong(couponID));
            statement.setString(3, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                return MAGICCODE.COUPON_HAS_APPLY;
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

        return MAGICCODE.OK;
    }
}
