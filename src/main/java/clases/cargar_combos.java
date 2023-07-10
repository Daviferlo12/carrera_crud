/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import conection.c_conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author davif
 */
public class cargar_combos {
    
    public void rellenar_combo(String table, String valor, JComboBox combo){
        String query = "SELECT " + valor + " FROM " + table + ";";
        
        Statement st;
        c_conexion cn = new c_conexion();
        Connection conection = cn.establishConnection();
        
        try {
            st = conection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
            
                combo.addItem(rs.getString(valor));
                
            }
        } catch (Exception e) {
        }
    }
    
    
    
    
    // Rellenar combo datos sin repetir
    
    public void rellenar_combo_SinRepetir(String table, String valor, JComboBox combo){
        String query = "SELECT DISTINCT " + valor + " FROM " + table + ";";
        
        Statement st;
        c_conexion cn = new c_conexion();
        Connection conection = cn.establishConnection();
        
        try {
            st = conection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
            
                combo.addItem(rs.getString(valor));
                
            }
        } catch (Exception e) {
        }
    }
    
}
