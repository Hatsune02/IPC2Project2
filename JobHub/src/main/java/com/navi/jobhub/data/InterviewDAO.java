package com.navi.jobhub.data;

import com.navi.jobhub.model.*;
import static com.navi.jobhub.data.Conexion.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class InterviewDAO {
    private static final String SQL_SELECT = "select * from interviews";
    private static final String SQL_INSERT_ID = "insert into interviews values(?,?,?,?,?,?,?,?)";
    private static final String SQL_INSERT = "insert into interviews(offer_id,user_id,interview_date,interview_time,location,state,notes) values (?,?,?,?,?,?,?)";

    public List<Interview> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Interview> interviews = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("interview_id");
                int offerId = rs.getInt("offer_id");
                int userId = rs.getInt("user_id");
                Date date = rs.getDate("interview_date");
                Time time = rs.getTime("interview_time");
                String location = rs.getString("location");
                String state = rs.getString("state");
                String notes = rs.getString("notes");
                interviews.add(Interview.builder().id(id).offerId(offerId).userId(userId).date(date)
                        .time(time).location(location).state(state).notes(notes).build());
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
        return interviews;
    }

    public int insert(Interview interview){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setInt(1,interview.getOfferId());
            ps.setInt(2,interview.getUserId());
            ps.setDate(3,interview.getDate());
            ps.setTime(4,interview.getTime());
            ps.setString(5,interview.getLocation());
            ps.setString(6,interview.getState());
            ps.setString(7,interview.getNotes());
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
    public int insertId(Interview interview){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT_ID);
            ps.setInt(1,interview.getId());
            ps.setInt(2,interview.getOfferId());
            ps.setInt(3,interview.getUserId());
            ps.setDate(4,interview.getDate());
            ps.setTime(5,interview.getTime());
            ps.setString(6,interview.getLocation());
            ps.setString(7,interview.getState());
            ps.setString(8,interview.getNotes());
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
