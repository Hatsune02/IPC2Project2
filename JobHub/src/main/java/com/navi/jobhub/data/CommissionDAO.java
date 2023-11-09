package com.navi.jobhub.data;

import com.navi.jobhub.model.Card;
import com.navi.jobhub.model.Commission;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.navi.jobhub.data.Conexion.close;
import static com.navi.jobhub.data.Conexion.getConnection;

public class CommissionDAO {
    private static final String SQL_SELECT = "select * from commissions";
    private static final String SQL_SELECT_COMMISSION = "select commission from commissions order by start_date desc limit 1";
    private static final String SQL_INSERT = "insert into commissions(commission) values(?)";
    private static final String SQL_UPDATE = "update commissions set end_date=? where commission_id=?";

    public List<Commission> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Commission> commissions = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("commission_id");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                double commission = rs.getDouble("commission");
                commissions.add(Commission.builder().id(id).startDate(startDate).endDate(endDate).commission(commission).build());
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
        return commissions;
    }
    public double actualCommission(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double commission = 0;

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_COMMISSION);
            rs = ps.executeQuery();
            while (rs.next()){
                commission = rs.getDouble("commission");
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
        return commission;
    }
    public int insert(double commission){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setDouble(1,commission);
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
    public int updateEndDate(Commission commission, Date endDate){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setDate(1,endDate);
            ps.setInt(2,commission.getId());
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
