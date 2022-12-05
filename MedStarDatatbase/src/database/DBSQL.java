/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.time.Clock;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class DBSQL {

    public ResultSet get(String query) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        }catch (ClassNotFoundException e){
            System.out.println(e);
        }
    
        final String ID = "bwhitt7";
        final String PW = "COSC*4a53h";
        final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/bwhitt7db";
        String querys;
        ResultSet rs = null;
        try {
            Connection con = DriverManager.getConnection(SERVER, ID, PW);
            Statement stmt = con.createStatement();
            stmt.execute("use bwhitt7db");
            rs = stmt.executeQuery(query);
            //PreparedStatement updateStaff = null;


            //querys = "UPDATE shongdb.Branch SET city = 'Baltimore' WHERE branchNo = 'B002';";
            //updateStaff = con.prepareStatement(querys);
            //updateStaff.executeUpdate();
        }catch (SQLException e){
            System.err.println(e);
        }
        return rs;
    }//Main


    public DefaultTableModel makeTable(ResultSet rs){
        DefaultTableModel m = new DefaultTableModel();

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int col = rsmd.getColumnCount();

            for (int i = 0; i < col; i++){
                m.addColumn(rsmd.getColumnLabel(i+1));
            }

            while(rs.next()){
                String[] row = new String[col];
                for (int i = 0; i < col; i++){
                    row[i] = rs.getString(i+1);
                }
                m.addRow(row);
            }
        }catch (SQLException e){
            System.err.println(e);
        }
        
        return m;

    }

}