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
public class cls_corredores {
    
    int dorsal;
    String nombre;
    String nacionalidad;
    int equipo;

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getEquipo() {
        return equipo;
    }

    public void setEquipo(int equipo) {
        this.equipo = equipo;
    }
    
    public void InsertarCorredor(JTextField paramDorsal, JTextField paramNombre, JComboBox paramNacionalidad, JComboBox combo){
        
        setDorsal(Integer.parseInt(paramDorsal.getText()));
        setNombre(paramNombre.getText());
        setNacionalidad(paramNacionalidad.getSelectedItem().toString());
        setEquipo(combo.getSelectedIndex() + 1);
        
        c_conexion conexion = new c_conexion();
        
        String query = "insert into corredores values(?,?,?,?)";
        
        try {
            
            CallableStatement cs = conexion.establishConnection().prepareCall(query);
            
            cs.setInt(1, getDorsal());
            cs.setString(2, getNombre());
            cs.setString(3, getNacionalidad());
            cs.setInt(4, getEquipo());
            
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se guardo Correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se insertaron los datos correctamente, ERROR: "+ e.toString());
        }
    }
    
    public void mostrar_corredores(JTable tabla_corredores){
        c_conexion conexion = new c_conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        
        TableRowSorter<TableModel> ordenar_tabla = new TableRowSorter<>(modelo);
        tabla_corredores.setRowSorter(ordenar_tabla);
        
        String query = "";
        
        modelo.addColumn("Dorsal");
        modelo.addColumn("Nombre");
        modelo.addColumn("Nacionalidad");
        modelo.addColumn("Equipo");
        
        query = "SELECT c.dorsal, c.nombre, c.nacionalidad, e.marca_patro FROM corredores c INNER JOIN equipos e ON c.id_equipo = e.id_equipo;";
        
        tabla_corredores.setModel(modelo);
        
        String[] datos = new String[4];
        Statement st;
        
        try {
            
            st = conexion.establishConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                
                modelo.addRow(datos);
            }
            
            tabla_corredores.setModel(modelo);
            System.err.println("Datos de la tabla cargados");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al llamar los datos de la tabla, ERROR: "+ e.toString());
        }
        
    }
    
    
    public void seleccionar_corredor(JTable tabla_corredores, JTextField p_dorsal, JTextField p_nombre, JComboBox p_combo, JComboBox p_equipo){
        
        try {
            
            int fila = tabla_corredores.getSelectedRow();
            
            if(fila >= 0){
                
                p_dorsal.setText(tabla_corredores.getValueAt(fila, 0).toString());
                p_nombre.setText(tabla_corredores.getValueAt(fila, 1).toString());
                p_combo.setSelectedItem(tabla_corredores.getValueAt(fila, 2).toString());
                p_equipo.setSelectedItem(tabla_corredores.getValueAt(fila, 3).toString());
                
            }else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de seleccion, ERROR: " + e.toString());
        }
        
    }
    
    public void modificar(JTextField p_dorsal, JTextField p_nombre, JComboBox p_nacionalidad, JComboBox p_equipo){
        setDorsal(Integer.parseInt(p_dorsal.getText()));
        setNombre(p_nombre.getText());
        setNacionalidad(p_nacionalidad.getSelectedItem().toString());
        setEquipo(p_equipo.getSelectedIndex()+1);
        
        
        c_conexion conexion = new c_conexion();
        
        String query = "UPDATE corredores c SET c.nombre = ?, c.nacionalidad = ?, c.id_equipo = ? WHERE  c.dorsal = ?;";
        
        try {
            CallableStatement cs = conexion.establishConnection().prepareCall(query);
            
            cs.setString(1, getNombre());
            cs.setString(2, getNacionalidad());
            cs.setInt(3, getEquipo());
            cs.setInt(4, getDorsal());
            
            cs.execute();
            System.out.println("Modificado con exito");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al actualizar, ERROR: "+ e.toString());
            
        }
    }
    
    
    public void eliminar_correcdor(JTextField p_dorsal){
        
        setDorsal(Integer.parseInt(p_dorsal.getText()));
        
        c_conexion conexion = new c_conexion();
        
        String query = "DELETE FROM corredores c WHERE c.dorsal = ?;";
        
        
                try {
            CallableStatement cs = conexion.establishConnection().prepareCall(query);

            cs.setInt(1, getDorsal());
            cs.execute();
            System.out.println("Eliminado con exito");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al eliminar, ERROR: "+ e.toString());
            
        }
        
    }
    
}
