package clases;

import conection.c_conexion;
import java.sql.CallableStatement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
            
            JOptionPane.showMessageDialog(null, "Se insertaron los datos correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se insertaron los datos correctamente, ERROR: "+ e.toString());
        }
    }
    
}
