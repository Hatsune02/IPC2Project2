package com.navi.jobhub.data;

import com.navi.jobhub.model.User;
import static com.navi.jobhub.data.Conexion.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserDAO {
    private static final String SQL_SELECT = "select * from users";
    private static final String SQL_INSERT_ID = "insert into users values(?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_INSERT = "insert into users(user_type,user_name,username,user_password,address,email,CUI,birth_date, verify) values (?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update users set user_type=?, user_name=?, username=?, user_password=?, address=?, email=?, CUI=?, birth_date=?, verify=? where user_id=?";
    private static final String SQL_VALIDATE = "select count(id) as amount from users where username=? and user_password=?";
    public List<User> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("user_id");
                int type = rs.getInt("user_type");
                String name = rs.getString("user_name");
                String username = rs.getString("username");
                String password = rs.getString("user_password");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String CUI = rs.getString("CUI");
                Date birthDate = rs.getDate("birth_date");
                int verify = rs.getInt("verify");
                users.add(User.builder().id(id).type(type).name(name).username(username).password(password).
                        address(address).email(email).CUI(CUI).birth(birthDate).verify(verify).build());
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
        return users;
    }

    public int insert(User user){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setInt(1,user.getType());
            ps.setString(2,user.getName());
            ps.setString(3,user.getUsername());
            ps.setString(4,user.getPassword());
            ps.setString(5,user.getAddress());
            ps.setString(6,user.getEmail());
            ps.setString(7,user.getCUI());
            ps.setDate(8,user.getBirth());
            ps.setInt(9,user.getVerify());
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
    public int insertId(User user){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT_ID);
            ps.setInt(1,user.getId());
            ps.setInt(2,user.getType());
            ps.setString(3,user.getName());
            ps.setString(4,user.getUsername());
            ps.setString(5,user.getPassword());
            ps.setString(6,user.getAddress());
            ps.setString(7,user.getEmail());
            ps.setString(8,user.getCUI());
            ps.setDate(9,user.getBirth());
            ps.setInt(10,user.getVerify());
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
    public int update(User user){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setInt(1,user.getType());
            ps.setString(2,user.getName());
            ps.setString(3,user.getUsername());
            ps.setString(4,user.getPassword());
            ps.setString(5,user.getAddress());
            ps.setString(6,user.getEmail());
            ps.setString(7,user.getCUI());
            ps.setDate(8,user.getBirth());
            ps.setInt(9,user.getVerify());
            ps.setInt(10,user.getId());
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

    public boolean validate(User user){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_VALIDATE);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            rs = ps.executeQuery();

            while (rs.next()){
                records=rs.getInt("amount");
            }

        } catch (SQLException e) {
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
        return records != 0;
    }

}
