
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
import java.util.Random;

/**
 *
 * @author Kim B b
 */
public class Database {

   private Connection con;
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

   public void FinishDB() {
      try {
         if (con != null && !con.isClosed())
            con.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

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
      }
      finally{
          if (id.isEmpty()) {
         return false;
         //System.out.println(id +" 222222222 is denied.");
          }
          else{
          return true;
          }
      }
      // 나중에 이거 statement 변수들 다 전역변수화 시켜서 FinishDB에서 다 close해줄 것.      
   }

   public boolean Signup(String id, String password, String name) {
      try {
         PreparedStatement ps = con.prepareStatement("insert into person value (?,?,?)");
         ps.setString(1, id);
         ps.setString(2, password);
         ps.setString(3, name);

         ps.executeUpdate();
      } catch (Exception ex) {
         System.out.println("here1");
          ex.printStackTrace();
         return false;
      }
      System.out.println("here2");
      return true;
   }
   
   public ArrayList<String> ProjectDB(String ID) {
      
      Statement stmt = null;
      ResultSet rs = null;
      ArrayList<String> result = new ArrayList<String>();
      
      
      try {
         stmt = con.createStatement();
         String sql = "select pro_name,role,date from pp natural join project where id = '" + ID + "'";
         rs = stmt.executeQuery(sql);

         while (rs.next()) {
            result.add(new String(rs.getString(1) + "/" + rs.getString(2) + "/" +rs.getString(3)));
         }
         
         
      } catch (SQLException e) {
         e.printStackTrace();
      }

      return result;
      
   }
   
   //프로젝트이름과 마감기한을 토대로 project DB 내  pnum 속성을 서버에서 임의로 리턴하고 저장
   public String InsertProject(String p_name, String date) {
      String p_num = "";
      try {
         PreparedStatement ps = con.prepareStatement("insert into project value (?,?,?)");
         Random rand = new Random();
         p_num=""+rand.nextInt(10000);
         ps.setString(1, p_num);
         ps.setString(2, p_name);
         ps.setString(3, date);

         ps.executeUpdate();
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      return p_num;
   }
   
   //위의 함수에서 받은 pnum을 갖고 pp DB 완성
   public boolean InsertPP(String id, String p_num, String role) {
      try {
         PreparedStatement ps = con.prepareStatement("insert into pp value (?,?,?)");
         ps.setString(1, id);
         ps.setString(2, p_num);
         ps.setString(3, role);

         ps.executeUpdate();
      } catch (Exception ex) {
         ex.printStackTrace();
         return false;
      }
      return true;
   }
   
   
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
}