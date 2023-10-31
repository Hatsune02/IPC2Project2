package com.navi.jobhub.data;

import com.navi.jobhub.model.*;
import static com.navi.jobhub.data.Conexion.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RequestDAO {
    private static final String SQL_SELECT = "select * from requests";
    private static final String SQL_INSERT_ID = "insert into requests values(?,?,?,?,?)";
    private static final String SQL_INSERT = "insert into requests(offer_id,user_id,message,sate) values (?,?,?,?)";
    private static final String SQL_UPDATE = "update requests set message=?, state=? where request_id=?";

    public List<Request> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Request> requests = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("request_id");
                int offerId = rs.getInt("offer_id");
                int userId = rs.getInt("user_id");
                String message = rs.getString("message");
                String sate = rs.getString("sate");
                requests.add(Request.builder().id(id).offerId(offerId).userId(userId)
                        .message(message).state(sate).build());
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
        return requests;
    }
    public List<Request> requestsOffer(int offerId){
        return select().stream().filter(r -> r.getOfferId()==offerId).collect(Collectors.toList());
    }

    public List<Request> requestsUser(int userId){
        return select().stream().filter(r -> r.getUserId()==userId).collect(Collectors.toList());
    }
    public Request viewRequest(int id){
        for(Request request:select()){
            if(request.getId()==id) return request;
        }
        return null;
    }

    public int insert(Request request){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setInt(1,request.getOfferId());
            ps.setInt(2,request.getUserId());
            ps.setString(3,request.getMessage());
            ps.setString(4,request.getState());
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
    public int insertId(Request request){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT_ID);
            ps.setInt(1,request.getId());
            ps.setInt(2,request.getOfferId());
            ps.setInt(3,request.getUserId());
            ps.setString(4,request.getMessage());
            ps.setString(5,request.getState());
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
    public int update(Request request){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1,request.getMessage());
            ps.setString(2,request.getState());
            ps.setInt(3,request.getId());
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
