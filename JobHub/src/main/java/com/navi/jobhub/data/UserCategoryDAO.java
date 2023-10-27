package com.navi.jobhub.data;

import com.navi.jobhub.model.*;
import static com.navi.jobhub.data.Conexion.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserCategoryDAO {
    private static final String SQL_SELECT = "select * from user_categories";
    private static final String SQL_INSERT = "insert into user_categories values(?,?)";
    private static final String SQL_DELETE = "delete from user_categories where user_id=? and category_id=?";

    public List<UserCategory> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<UserCategory> userCategories = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int userId = rs.getInt("user_id");
                int categoryId = rs.getInt("category_id");
                userCategories.add(UserCategory.builder().userId(userId).categoryId(categoryId).build());
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
        return userCategories;
    }
    public int insert(UserCategory userCategory){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setInt(1,userCategory.getUserId());
            ps.setInt(2,userCategory.getCategoryId());
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
    public int delete(UserCategory userCategory){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1,userCategory.getUserId());
            ps.setInt(2,userCategory.getCategoryId());
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
