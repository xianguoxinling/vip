package com.db.manager;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.coin.CoinHistory;
import com.model.coupon.Coupon;
import com.model.vip.VipMember;
import com.model.vip.VipCard;

public class DBUntil
{
    public CoinHistory getCoinHistoryFromResultSet(ResultSet resultSet)
    {
        CoinHistory coinHistory = new CoinHistory();
        try
        {
            coinHistory.setPerson(resultSet.getString("person"));
            coinHistory.setIncrease(resultSet.getInt("increase"));
            coinHistory.setNum(resultSet.getInt("num"));
            coinHistory.setReason(resultSet.getString("reason"));
            coinHistory.setDate(resultSet.getString("increase_time"));

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return coinHistory;
    }

    public VipMember getvipmemberFromResult(ResultSet resultSet)
    {
        VipMember vipCard = new VipMember();
        try
        {
            vipCard.setId(resultSet.getString("id"));
            vipCard.setCustomer(resultSet.getString("customer"));
            vipCard.setCustomerRealName(resultSet.getString("customer_realname"));
            vipCard.setMagicKey(resultSet.getString("magic_key"));
            vipCard.setPhone(resultSet.getString("phone"));
            vipCard.setUuid(resultSet.getString("uuid"));
            vipCard.setCreateTime(resultSet.getString("create_time"));
            vipCard.setBlance(resultSet.getDouble("blance"));
            vipCard.setCardID(resultSet.getString("card"));
            vipCard.setState(resultSet.getString("state"));
            
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return vipCard;
    }
    
    public VipMember getvipmemberFromResult2(ResultSet resultSet)
    {
        VipMember vipCard = new VipMember();
        try
        {
            vipCard.setId(resultSet.getString("a.id"));
            vipCard.setCustomer(resultSet.getString("a.customer"));
            vipCard.setCustomerRealName(resultSet.getString("a.customer_realname"));
            vipCard.setMagicKey(resultSet.getString("a.magic_key"));
            vipCard.setPhone(resultSet.getString("a.phone"));
            vipCard.setUuid(resultSet.getString("a.uuid"));
            vipCard.setCreateTime(resultSet.getString("a.create_time"));
            vipCard.setBlance(resultSet.getDouble("a.blance"));
            vipCard.setCardID(resultSet.getString("a.card"));
            vipCard.setState(resultSet.getString("a.state"));
            vipCard.setCardName(resultSet.getString("b.name"));
            vipCard.setCoinNum(resultSet.getLong("c.num"));
            
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return vipCard;
    }
    
    public VipCard queryVipCardFromResultSet(ResultSet resultSet)
    {
        VipCard vipCard = new VipCard();
        try
        {
            vipCard.setId(resultSet.getString("id"));
            vipCard.setContactAddress(resultSet.getString("contact_address"));
            vipCard.setContactNumber(resultSet.getString("contact_number"));
            vipCard.setCreateTime(resultSet.getString("update_time"));
            vipCard.setDiscount(resultSet.getDouble("discount"));
            vipCard.setIntroduction(resultSet.getString("introduction"));
            vipCard.setMagicKey(resultSet.getString("magic_key"));
            vipCard.setName(resultSet.getString("name"));
            vipCard.setPic(resultSet.getString("pic"));
            vipCard.setLevel(resultSet.getInt("level"));
            
        }catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return vipCard;
    }
    
    public Coupon queryCouponFromResultSet(ResultSet resultSet)
    {
        Coupon coupon = new Coupon();
        try
        {
            coupon.setId(resultSet.getString("id"));
            coupon.setBeginTime(resultSet.getString("begin_time"));
            coupon.setCreateTime(resultSet.getString("apply_time"));
            coupon.setEndTime(resultSet.getString("end_time"));
            coupon.setFaceValue(resultSet.getDouble("face_value"));
            coupon.setMagic_key(resultSet.getString("magic_key"));
            coupon.setMinimumLimit(resultSet.getDouble("min_limit"));
            coupon.setName(resultSet.getString("name"));
        }catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        
        return coupon;
    }
    
    public Coupon queryCouponLefJoinFromResultSet(ResultSet resultSet)
    {
        Coupon coupon = new Coupon();
        try
        {
            coupon.setId(resultSet.getString("a.id"));
            coupon.setBeginTime(resultSet.getString("a.begin_time"));
            coupon.setCreateTime(resultSet.getString("a.apply_time"));
            coupon.setEndTime(resultSet.getString("a.end_time"));
            coupon.setFaceValue(resultSet.getDouble("a.face_value"));
            coupon.setMagic_key(resultSet.getString("a.magic_key"));
            coupon.setMinimumLimit(resultSet.getDouble("a.min_limit"));
            coupon.setName(resultSet.getString("a.name"));
            coupon.setState(resultSet.getString("b.state"));
        }catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        
        return coupon;
    }
}
