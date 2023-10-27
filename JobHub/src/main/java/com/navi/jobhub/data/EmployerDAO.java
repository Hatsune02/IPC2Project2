package com.navi.jobhub.data;

import com.navi.jobhub.model.Employer;
import com.navi.jobhub.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.navi.jobhub.data.Conexion.*;

public class EmployerDAO {
    private static final String SQL_SELECT = "select * from employers";
    private static final String SQL_INSERT = "insert into employers values(?,?,?)";
    private static final String SQL_UPDATE = "update employers set mission=?, vision=? where employer_id=?";

    public List<Employer> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Employer> employers = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("employer_id");
                String mission = rs.getString("mission");
                String vision = rs.getString("vision");
                var employer = new Employer();
                employer.setId(id);
                employer.setMission(mission);
                employer.setVision(vision);
                employers.add(employer);
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
        return employers;
    }

    public int insert(Employer employer){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setInt(1,employer.getId());
            ps.setString(2,employer.getMission());
            ps.setString(3,employer.getVision());
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

    public int update(Employer employer){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1,employer.getMission());
            ps.setString(2,employer.getVision());
            ps.setInt(3,employer.getId());
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
