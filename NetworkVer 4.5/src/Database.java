
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Kim B b
 */
public class Database {

    private Connection con;

    //Use JDBC to work with the database.
    public Database() {
        con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/network";
            String user = "root", passwd = "12345";
            con = (Connection) DriverManager.getConnection(url, user, passwd);
            System.out.println(con);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //the funcition to finish Database system
    public void FinishDB() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //A function that returns the information he has with that ID.
    public boolean login(String ID, String PW) {
        Statement stmt = null;
        ResultSet rs = null;
        String id = "";

        try {
            stmt = con.createStatement();
            String sql = "select id from person where id = '" + ID + "' and password = '" + PW + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                id = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(id + " 11111111 is denied.");
        }
        if (id.isEmpty()) {
            return false;
        }

        return true;
    }

    public boolean Signup(String id, String password, String name) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into person value (?,?,?)");
            ps.setString(1, id);
            ps.setString(2, password);
            ps.setString(3, name);

            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    //A function that returns the information he has with that ID.
    //get the information of project name, role, deadline, project number, email
    public ArrayList<String> ProjectDB(String ID) {

        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<String> result = new ArrayList<String>();

        try {
            stmt = con.createStatement();
            String sql = "select pro_name,role,date,pro_number,pro_email from pp natural join project where id = '" + ID + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                result.add(new String(rs.getString(1) + "/" + rs.getString(2) + "/" + rs.getString(3) + "/" + rs.getString(4) + "/" + rs.getString(5)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    //Returns the randomly generated pro_number as a function to add the project name, deadline, email, and creation date to the database named Project.
    public String InsertProject(String p_name, String date, String p_email, String startdate) {
        String p_num = "";
        try {
            PreparedStatement ps = con.prepareStatement("insert into project value (?,?,?,?,?)");

            System.out.println(CountProject());
            int count = CountProject() + 1;
            p_num = "" + count;
            ps.setString(1, p_num);
            ps.setString(2, p_name);
            ps.setString(3, date);
            ps.setString(4, p_email);
            ps.setString(5, startdate);

            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return p_num;
    }

    //A function that is required to create a pro_number only on the server when the project is created.
    public int CountProject() {
        Statement stmt = null;
        ResultSet rs = null;
        int result = 0;

        try {
            stmt = con.createStatement();
            String sql = "select max(pro_number) from project";
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                result = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //The function that adds the ID and each role to the database named pp.
    public boolean InsertPP(ArrayList<String> id_role, String p_num) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into pp value (?,?,?,?,?,?)");

            for (int i = 0; i < id_role.size(); i++) {
                String line = id_role.get(i);
                int var = line.indexOf(" ");
                String id = line.substring(0, var);
                int var2 = line.indexOf("]");
                String role = line.substring(var + 2, var2);
                System.out.println(id + " " + role);
                ps.setString(1, id);
                ps.setString(2, p_num);
                ps.setString(3, role);
                ps.setInt(4, 0);
                ps.setInt(5, 0);
                ps.setInt(6, 0);

                ps.executeUpdate();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    //A function that erase the selected data from the Project and PP database 
    public void DeleteDB(String p_num) {
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql1 = "delete from project where pro_number = '" + p_num + "'";
            String sql2 = "delete from pp where pro_number = '" + p_num + "'";
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    //A function that imports all the ID lists from a database named person.
    public ArrayList<String> IDlist() {

        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<String> result = new ArrayList<String>();

        try {
            stmt = con.createStatement();
            String sql = "select id from person";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                result.add(new String(rs.getString(1)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    //A function that takes the person's name with an id
    public String getName(String id) {

        Statement stmt = null;
        ResultSet rs = null;
        String result = new String();

        try {
            stmt = con.createStatement();
            String sql = "select name from person where id = '" + id + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                result = rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    //A function that checks if an ID exists in the database when creating an ID
    public boolean IDcheck(String ID) {
        Statement stmt = null;
        ResultSet rs = null;
        String id = "";

        try {
            stmt = con.createStatement();
            String sql = "select id from person where id = '" + ID + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                id = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (id.isEmpty()) {
            return false;
        }

        return true;
    }
    
    //A function that tells you which votes are in your chosen project.
    public ArrayList<String> Votelist(String pro_num) {

        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<String> result = new ArrayList<String>();

        try {
            stmt = con.createStatement();
            String sql = "select vote_name from vote where pro_number = '" + pro_num + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                result.add(new String(rs.getString(1)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    //A function that checks if a name can be used for the project when creating a voting DB.
    public boolean Votecheck(String vote_name, String pro_num) {
        Statement stmt = null;
        ResultSet rs = null;
        String id = "";

        try {
            stmt = con.createStatement();
            String sql = "select vote_name from vote where vote_name = '" + vote_name + "' and pro_number = '" + pro_num + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                id = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (id.isEmpty()) {
            return true;
        }

        return false;
    }

    //the function to insert values into vote database
    public void InsertVote(String vote_name, int p_num, int choice, String choice_name) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into vote value (?,?,?,?)");

            ps.setString(1, vote_name);
            ps.setInt(2, p_num);
            ps.setInt(3, choice);
            ps.setString(4, choice_name);

            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}
