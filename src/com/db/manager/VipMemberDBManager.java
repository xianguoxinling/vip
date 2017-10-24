package com.db.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.model.vip.VipMember;
import com.until.errorcode.MAGICCODE;

public class VipMemberDBManager
{
    protected DBManager dbManager = null;
    
    public int createVipMember(VipMember vipmember)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "INSERT INTO vipmember (uuid,customer,customer_realname,phone,magic_key,create_time) VALUES (?,?,?,?,?,?)";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, vipmember.getUuid());
            statement.setString(2, vipmember.getCustomer());
            statement.setString(3, vipmember.getCustomerRealName());
            statement.setString(4, vipmember.getPhone());
            statement.setString(5, vipmember.getMagicKey());
            statement.setTimestamp(6, new java.sql.Timestamp(new java.util.Date().getTime()));
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
        return MAGICCODE.OK;
    }
    
    public VipMember queryVipMember(String uuid)
    {

        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT * FROM vipmember WHERE uuid = ?";
        PreparedStatement statement;
        VipMember vipmember = null;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(1, uuid);
            ResultSet resultSet = statement.executeQuery();
            DBUntil dbUntil = new DBUntil();
            while (resultSet.next())
            {
                vipmember = dbUntil.getvipmemberFromResult(resultSet);
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

        return vipmember;

    }
    
    public VipMember queryVipMemberByID(String id)
    {

        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT * FROM vipmember WHERE id = ?";
        PreparedStatement statement;
        VipMember vipmember = null;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            DBUntil dbUntil = new DBUntil();
            while (resultSet.next())
            {
                vipmember = dbUntil.getvipmemberFromResult(resultSet);
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

        return vipmember;

    }
    
    public VipMember queryVipMemberByUserID(String userID)
    {

        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT * FROM vipmember WHERE customer = ?";
        PreparedStatement statement;
        VipMember vipmember = null;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();
            DBUntil dbUntil = new DBUntil();
            while (resultSet.next())
            {
                vipmember = dbUntil.getvipmemberFromResult(resultSet);
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

        return vipmember;
    }
    
    public int queryApply(String magicKey,List<VipMember> vipMemberList)
    {

        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT * FROM vipmember WHERE magic_key = ? and state = ?";
        PreparedStatement statement;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(1, magicKey);
            statement.setString(2, "apply");
            ResultSet resultSet = statement.executeQuery();
            DBUntil dbUntil = new DBUntil();
            while (resultSet.next())
            {
                VipMember vipMember = dbUntil.getvipmemberFromResult(resultSet);
                vipMemberList.add(vipMember);
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
    
    public int queryAllMember(String magicKey,List<VipMember> vipMemberList)
    {

        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT * FROM (vipmember a left join vipcard b on a.card = b.id) left join coin c on a.customer = c.person WHERE a.magic_key = ? and a.state = ?";
        PreparedStatement statement;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(1, magicKey);
            statement.setString(2, "ok");
            ResultSet resultSet = statement.executeQuery();
            DBUntil dbUntil = new DBUntil();
            while (resultSet.next())
            {
                VipMember vipMember = dbUntil.getvipmemberFromResult2(resultSet);
                vipMemberList.add(vipMember);
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
    
    public double queryBlance(String uuid)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT blance FROM vipmember WHERE uuid = ?";
        PreparedStatement statement;
        double blance = 0.0;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(1, uuid);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                blance = resultSet.getDouble("blance");
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

        return blance;
    }
    
    public int updateBlance(String uuid,double blance)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "update vipmember set blance = ? where uuid = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, blance);
            statement.setString(2, uuid);
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
    
    public int updateState(String id,String state)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        System.out.println("ID:"+id);
        System.out.println("STATE:"+state);
        String sql = "update vipmember set state = ?,create_time=? where id = ?";
//        String sql = "update vipmember set state = ? where id = ?";
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, state);
            statement.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
            statement.setLong(3, Long.parseLong(id));
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
    
    public int updateCard(String id,String cardID)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "update vipmember set card = ? where id = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cardID);
            statement.setLong(2, Long.parseLong(id));
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
    
    public double queryDiscount(String userID,String magicKey)
    {
        double discount = 1;
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT b.discount FROM vipmember a left join vipcard b on a.card=b.id WHERE a.magic_key = ? and a.customer = ?";
        PreparedStatement statement;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(1, magicKey);
            statement.setString(2, userID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                discount = resultSet.getDouble("b.discount");
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
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

        return discount;
    }
}
