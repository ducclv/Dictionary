/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.awt.Component;
import java.awt.Frame;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Connect extends Frame{
    private static final String USERNAME= "root";
    private static final String PASSWORD= "";
    private static final String CONN_STRING= "jdbc:mysql://localhost:3307/data";
    public static void main(String arg[]) {

//        Update("admin", "admin_fixed_not_bug");//chỗ nnày này
//        Update("haha","cười");
            
        //Insert("","");
        //Delete("admins");
        //System.err.println(Search("word"));
    }
    private static final Component frame = null;
    private static Connection conn=null;
    public static void Connect(){
        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    public static String Search(String word) {
        ResultSet rs = null;
        try
        {
            if(conn==null)
                Connect();
            String sql="select * from tbl_edict where word = ?";
            PreparedStatement st = null;
            st= conn.prepareStatement(sql);
            st.setString(1, word);
            rs = st.executeQuery();
            // Kiểm tra điều kiện 
            if (!rs.first()){
                return "Không tìm được từ này";
            } else {
                String result=rs.getNString("detail");
                result=result.replaceAll("<C><F><I><N><Q>", "");
                result=result.replaceAll("</Q></N></I></F></C>", "");
                result=result.replaceAll("@", "");
                return result;
            }
        }catch (SQLException e) {
            return "";
        }
    }
    public static String Search(int idx) {
        ResultSet rs = null;
        try
        {
            if(conn==null)
                Connect();
            String sql="select * from tbl_edict where idx = ?";
            PreparedStatement st = null;
            st= conn.prepareStatement(sql);
            st.setInt(1, idx);
            rs = st.executeQuery();
            if (!rs.first()){
                return "Không tìm được từ này";
            } else {
                String result=rs.getNString("detail");
                result=result.replaceAll("<C><F><I><N><Q>", "");
                result=result.replaceAll("</Q></N></I></F></C>", "");
                result=result.replaceAll("@", "");
                return result;
            }
        }catch (SQLException e) {
            return "";
        }
    }
    public static void Update(String word,String detail) {
        
        ResultSet rs = null;
        try
        {
            if(conn==null)
                Connect();
            String sql="select * from tbl_edict where word = ?";
            PreparedStatement st = null;
            st= conn.prepareStatement(sql);
            st.setString(1, word);// cái này nghĩa là gì// điền word vào dấu ? đầu tiên
            rs = st.executeQuery();
            if (!rs.first()){
                System.out.println ("Không tìm được từ này");
                JOptionPane.showMessageDialog(frame, "Không tìm được từ này");
            } else {
                int idx = rs.getInt("idx");
                sql = "Update tbl_edict set detail = ? where idx =?";
                st=conn.prepareStatement(sql);
                st.setString(1, detail);
                st.setInt(2, idx);
                if(st.executeUpdate() > 0){
                    System.out.println("Done");
                    JOptionPane.showMessageDialog(frame, "Từ đã được cập nhật");
                } else {
                    System.out.println("Update error");
                }
            }
        }catch (SQLException e) {
            System.err.println(e);
        }
    }
    public static void Insert(String word,String detail) {
        ResultSet rs = null;
        try
        {
            if(conn==null)
                Connect();
            String sql="select * from tbl_edict where word = ?";
            PreparedStatement st = null;
            st= conn.prepareStatement(sql);
            st.setString(1, word);// cái này nghĩa là gì// điền word vào dấu ? đầu tiên
            rs = st.executeQuery();
            if (rs.first()){
                System.out.println ("Từ này đã tồn tại");
                JOptionPane.showMessageDialog(frame, "Từ này đã tồn tại");
            } else {
                sql = "Insert into  tbl_edict (word,detail) values (?,?)";
                st=conn.prepareStatement(sql);
                st.setString(1, word);
                st.setString(2, detail);
                if(st.executeUpdate() > 0){
                    System.out.println("Done");
                    JOptionPane.showMessageDialog(frame, "Thêm từ thành công");
                } else {
                    System.out.println("Insert error");
                }
            }
        }catch (SQLException e) {
            System.err.println(e);
        }
    }
    public static void Delete(String word) {
        ResultSet rs = null;
        try
        {
            if(conn==null)
                Connect();
            String sql="select * from tbl_edict where word = ?";
            PreparedStatement st = null;
            st= conn.prepareStatement(sql);
            st.setString(1, word);// cái này nghĩa là gì// điền word vào dấu ? đầu tiên
            rs = st.executeQuery();
            if (!rs.first()){
                System.out.println ("Từ này không tồn tại");
                JOptionPane.showMessageDialog(frame, "Từ này không tồn tại");
            } else {
                int idx = rs.getInt("idx");
                sql = "Delete from tbl_edict where idx = ?";
                st=conn.prepareStatement(sql);
                st.setInt(1, idx);
                if(st.executeUpdate() > 0){
                    System.out.println("Done");
                    JOptionPane.showMessageDialog(frame, "Xóa từ thành công");
                } else {
                    System.out.println("Delete error");
                }
            }
        }catch (SQLException e) {
            System.err.println(e);
        }
    }
    public static ArrayList<Word> DictionarySearch (String word){
        ArrayList<Word>result = new ArrayList<>();
        if(!word.isEmpty()){
            ResultSet rs= null;
            try {
                if(conn==null)
                    Connect();
                String sql="select * from tbl_edict where word like N'"+word+"%' order by word asc limit 25";
                PreparedStatement st = null;
                st= conn.prepareStatement(sql);
                rs = st.executeQuery();
                if(rs.first()){
                    do{
                        result.add(new Word(rs.getInt("idx"),rs.getNString("word")));
                    }while(rs.next());
                }else{
                    result = null;
                }
            }catch (SQLException e) {
                result = null;
                System.err.println(e);
            }
        }else{
            result=null;
        }
        return result;
    }

   
}
