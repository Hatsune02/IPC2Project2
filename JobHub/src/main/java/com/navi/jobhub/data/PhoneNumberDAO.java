package com.navi.jobhub.data;

import com.navi.jobhub.model.PhoneNumber;

import static com.navi.jobhub.data.Conexion.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneNumberDAO {
    private static final String SQL_SELECT = "select * from phone_numbers";
    private static final String SQL_INSERT = "insert into phone_numbers values(?,?)";
    private static final String SQL_DELETE = "delete from phone_numbers where phone_number=?";

    public List<PhoneNumber> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<PhoneNumber> phoneNumbers = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int userId = rs.getInt("user_id");
                String number = rs.getString("phone_number");
                phoneNumbers.add(PhoneNumber.builder().userId(userId).number(number).build());
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
        return phoneNumbers;
    }

    public List<PhoneNumber> phoneNumbersUser(int id){
        return select().stream().filter(p -> p.getUserId()== id).collect(Collectors.toList());
    }

    public List<String> numbersUser(int id){
        List<String> numbers = new ArrayList<>();
        for(PhoneNumber p: select()){
            if(p.getUserId() == id) numbers.add(p.getNumber());
        }
        return numbers;
    }

    public int insert(PhoneNumber phoneNumber){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setInt(1,phoneNumber.getUserId());
            ps.setString(2,phoneNumber.getNumber());
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
    public int delete(PhoneNumber phoneNumber){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setString(1,phoneNumber.getNumber());
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
