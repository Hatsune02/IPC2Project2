package com.navi.jobhub.data;

import com.navi.jobhub.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.navi.jobhub.data.Conexion.close;
import static com.navi.jobhub.data.Conexion.getConnection;

public class CategoryDAO {
    private static final String SQL_SELECT = "select * from categories";
    private static final String SQL_INSERT_ID = "insert into categories values(?,?,?)";
    private static final String SQL_INSERT = "insert into categories(category_name,category_description) values (?,?)";
    private static final String SQL_UPDATE = "update users set category_name=?, category_description=? where category_id=?";

    public List<Category> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Category> categories = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("category_id");
                String name = rs.getString("category_name");
                String description = rs.getString("category_description");
                categories.add(Category.builder().id(id).name(name).description(description).build());
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
        return categories;
    }
    public Category viewCategory(int id){
        for(Category category:select()){
            if(category.getId() == id) return category;
        }
        return null;
    }

    public int insert(Category category){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1,category.getName());
            ps.setString(2,category.getDescription());
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

    public int insertId(Category category){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT_ID);
            ps.setInt(1,category.getId());
            ps.setString(2,category.getName());
            ps.setString(3,category.getDescription());
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

    public int update(Category category){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1,category.getName());
            ps.setString(2,category.getDescription());
            ps.setInt(3,category.getId());

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
