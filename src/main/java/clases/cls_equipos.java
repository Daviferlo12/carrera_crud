/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import conection.c_conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author davif
 */
public class cls_equipos {
    
    int id_equipo;
    String marca_patro;
    String nombre_tecnico;

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getMarca_patro() {
        return marca_patro;
    }

    public void setMarca_patro(String marca_patro) {
        this.marca_patro = marca_patro;
    }

    public String getNombre_tecnico() {
        return nombre_tecnico;
    }

    public void setNombre_tecnico(String nombre_tecnico) {
        this.nombre_tecnico = nombre_tecnico;
    }
    
    public void InsertarEquipo(JTextField p_id_equipo, JTextField p_marca_patro, JTextField p_tecnico){
        
        setId_equipo(Integer.parseInt(p_id_equipo.getText()));
        setMarca_patro(p_marca_patro.getText());
        setNombre_tecnico(p_tecnico.getText());
        
        c_conexion conexion = new c_conexion();
        
        String query = "insert into equipos values(?,?,?)";
        
        try {
            
            CallableStatement cs = conexion.establishConnection().prepareCall(query);
            
            cs.setInt(1, getId_equipo());
            cs.setString(2, getMarca_patro());
            cs.setString(3, getNombre_tecnico());
            
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se creo un equipo correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se insertaron los datos correctamente, ERROR: "+ e.toString());
        }
    }
    
    public void mostrar_equipos(JTable tabla_equipos){
        c_conexion conexion = new c_conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        
        TableRowSorter<TableModel> ordenar_tabla = new TableRowSorter<>(modelo);
        tabla_equipos.setRowSorter(ordenar_tabla);
        
        String query = "";
        
        modelo.addColumn("Id Equipo");
        modelo.addColumn("Marca Patrocinadora");
        modelo.addColumn("Tecnico");
        
        query = "SELECT * FROM equipos;";
        
        tabla_equipos.setModel(modelo);
        
        String[] datos = new String[3];
        Statement st;
        
        try {
            
            st = conexion.establishConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                
                modelo.addRow(datos);
            }
            
            tabla_equipos.setModel(modelo);
            System.err.println("Datos de la tabla cargados");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al llamar los datos de la tabla, ERROR: "+ e.toString());
        }
        
    }
    
    
    public void seleccionar_equipo(JTable tabla_equipos, JTextField p_id_equipo, JTextField p_marca_patro, JTextField p_tecnico){
        
        try {
            
            int fila = tabla_equipos.getSelectedRow();
            
            if(fila >= 0){
                
                p_id_equipo.setText(tabla_equipos.getValueAt(fila, 0).toString());
                p_marca_patro.setText(tabla_equipos.getValueAt(fila, 1).toString());
                p_tecnico.setText(tabla_equipos.getValueAt(fila, 2).toString());
                
            }else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de seleccion, ERROR: " + e.toString());
        }
        
    }
    
    public void modificar(JTextField p_id_equipo, JTextField p_marca_patro, JTextField p_tecnico){
        
        setId_equipo(Integer.parseInt(p_id_equipo.getText()));
        setMarca_patro(p_marca_patro.getText());
        setNombre_tecnico(p_tecnico.getText());
        
        
        c_conexion conexion = new c_conexion();
        
        String query = "UPDATE equipos SET marca_patro = ?, nombre_tecnico = ? WHERE id_equipo = ?;";
        
        try {
            CallableStatement cs = conexion.establishConnection().prepareCall(query);
            
            cs.setString(1, getMarca_patro());
            cs.setString(2, getNombre_tecnico());;
            cs.setInt(3, getId_equipo());
            
            cs.execute();
            System.out.println("Modificado con exito");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al actualizar, ERROR: "+ e.toString());
            
        }
    }
    
    
    public void eliminar_equipo(JTextField p_dorsal){
        
        setId_equipo(Integer.parseInt(p_dorsal.getText()));
        
        c_conexion conexion = new c_conexion();
        
        String query = "DELETE FROM equipos WHERE id_equipo = ?;";
        
        
        try {
            CallableStatement cs = conexion.establishConnection().prepareCall(query);

            cs.setInt(1, getId_equipo());
            cs.execute();
            System.out.println("Eliminado con exito");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al eliminar, ERROR: "+ e.toString());
            
        }
        
    }
    
}
