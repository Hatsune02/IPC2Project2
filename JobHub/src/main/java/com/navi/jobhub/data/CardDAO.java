package com.navi.jobhub.data;

import com.navi.jobhub.model.Card;
import com.navi.jobhub.model.PhoneNumber;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.navi.jobhub.data.Conexion.*;
public class CardDAO {
    private static final String SQL_SELECT = "select * from cards";
    private static final String SQL_INSERT = "insert into cards values(?,?,?,?)";
    private static final String SQL_UPDATE = "update employers set owner_name=? card_number=? card_password=? where user_id=?";

    public List<Card> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Card> cards = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int userId = rs.getInt("user_id");
                String owner = rs.getString("owner_name");
                String number = rs.getString("card_number");
                String password = rs.getString("card_password");
                cards.add(Card.builder().userId(userId).owner(owner).number(number).password(password).build());
            }

        }catch (SQLException e){
            e.printStackTrace(System.out);
        }
        finally {
            try {
                close(ps);
                close(rs);
                close(con);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return cards;
    }

    public int insert(Card card){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setInt(1,card.getUserId());
            ps.setString(2,card.getOwner());
            ps.setString(1,card.getNumber());
            ps.setString(2,card.getPassword());
            records = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        finally {
            try {
                close(ps);
                close(con);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return records;
    }
}
