package com.navi.jobhub.data;

import com.navi.jobhub.model.*;

import static com.navi.jobhub.data.Conexion.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JobOfferDAO {
    private static final String SQL_SELECT = "select * from job_offers";
    private static final String SQL_INSERT_ID = "insert into job_offers values(?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_INSERT = "insert into job_offers(offer_name,offer_description,employer_id,category_id,state,created_at,end_at,salary,modality,location,details) values (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update job_offers set offer_name=?,offer_description=?,employer_id=?,category_id=?,state=?,created_at=?,end_at=?,salary=?,modality=?,location=?,details=? where offer_id=?";

    public List<JobOffer> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JobOffer> jobOffers = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("offer_id");
                String name = rs.getString("offer_name");
                String description = rs.getString("offer_description");
                int employerId = rs.getInt("employer_id");
                int categoryId = rs.getInt("category_id");
                String state = rs.getString("state");
                Date created = rs.getDate("created_at");
                Date end = rs.getDate("end_at");
                double salary = rs.getDouble("salary");
                String modality = rs.getString("modality");
                String location = rs.getString("location");
                String details = rs.getString("details");
                int choseUser = rs.getInt("chosen_user");
                jobOffers.add(JobOffer.builder().id(id).name(name).description(description).
                        employerId(employerId).categoryId(categoryId).state(state).created(created).
                        end(end).salary(salary).modality(modality).location(location).details(details).
                        chosenUser(choseUser).build());
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
        return jobOffers;
    }

    public List<JobOffer> jobOffersEmployer(int employerId){
        return select().stream().filter(j -> j.getEmployerId() == employerId).collect(Collectors.toList());
    }

    public JobOffer viewJobOffer(int id){
        for(JobOffer jobOffer:select()){
            if(jobOffer.getId() == id) return jobOffer;
        }
        return null;
    }

    public int insertId(JobOffer jobOffer){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT_ID);
            ps.setInt(1,jobOffer.getId());
            ps.setString(2,jobOffer.getName());
            ps.setString(3,jobOffer.getDescription());
            ps.setInt(4,jobOffer.getEmployerId());
            ps.setInt(5,jobOffer.getCategoryId());
            ps.setString(6,jobOffer.getState());
            ps.setDate(7,jobOffer.getCreated());
            ps.setDate(8,jobOffer.getEnd());
            ps.setDouble(9,jobOffer.getSalary());
            ps.setString(10,jobOffer.getModality());
            ps.setString(11,jobOffer.getModality());
            ps.setString(12,jobOffer.getDetails());
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
    public int insert(JobOffer jobOffer){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1,jobOffer.getName());
            ps.setString(2,jobOffer.getDescription());
            ps.setInt(3,jobOffer.getEmployerId());
            ps.setInt(4,jobOffer.getCategoryId());
            ps.setString(5,jobOffer.getState());
            ps.setDate(6,jobOffer.getCreated());
            ps.setDate(7,jobOffer.getEnd());
            ps.setDouble(8,jobOffer.getSalary());
            ps.setString(9,jobOffer.getModality());
            ps.setString(10,jobOffer.getModality());
            ps.setString(11,jobOffer.getDetails());
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
    public int update(JobOffer jobOffer){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1,jobOffer.getName());
            ps.setString(2,jobOffer.getDescription());
            ps.setInt(3,jobOffer.getEmployerId());
            ps.setInt(4,jobOffer.getCategoryId());
            ps.setString(5,jobOffer.getState());
            ps.setDate(6,jobOffer.getCreated());
            ps.setDate(7,jobOffer.getEnd());
            ps.setDouble(8,jobOffer.getSalary());
            ps.setString(9,jobOffer.getModality());
            ps.setString(10,jobOffer.getModality());
            ps.setString(11,jobOffer.getDetails());
            ps.setInt(12,jobOffer.getId());
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
