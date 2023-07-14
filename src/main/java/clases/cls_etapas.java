/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import conection.c_conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Lorena
 */
public class cls_etapas {
    
    int id_etapa;
    double kilometros;
    String origen;
    String destino;
    double tiempo_estimado;
    String tipo_puerto;
    int tipo_etapa;
    String fecha_inicio;
     
     
     

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }


    public int getId_etapa() {
        return id_etapa;
    }

    public void setId_etapa(int id_etapa) {
        this.id_etapa = id_etapa;
    }

    public double getKilometros() {
        return kilometros;
    }

    public void setKilometros(double kilometros) {
        this.kilometros = kilometros;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getTiempo_estimado() {
        return tiempo_estimado;
    }

    public void setTiempo_estimado(double tiempo_estimado) {
        this.tiempo_estimado = tiempo_estimado;
    }

    public String getTipo_puerto() {
        return tipo_puerto;
    }

    public void setTipo_puerto(String tipo_puerto) {
        this.tipo_puerto = tipo_puerto;
    }

    public int getTipo_etapa() {
        return tipo_etapa;
    }

    public void setTipo_etapa(int tipo_etapa) {
        this.tipo_etapa = tipo_etapa;
    }

    
   
    public void InsertarEtapa(JSpinner paramid_etapa, JDateChooser paramfecha_inicio, JTextField paramKilometro, JTextField paramOrigen, JTextField paramDestino, JSpinner paramTiempoEstimado, JComboBox paramTipoPuerto, JComboBox paramTipoEtapa){
        
        Date mFecha = paramfecha_inicio.getDate();
        long fecha = mFecha.getTime();
        java.sql.Date fecha_sql = new java.sql.Date(fecha);
        
        setId_etapa((int) paramid_etapa.getValue());
        setFecha_inicio(fecha_sql.toString());
        setKilometros(Double.parseDouble(paramKilometro.getText()));
        setOrigen(paramOrigen.getText());
        setDestino(paramDestino.getText());
        setTiempo_estimado(Double.parseDouble(paramTiempoEstimado.getValue().toString()));
        setTipo_puerto(paramTipoPuerto.getSelectedItem().toString());
        setTipo_etapa(paramTipoEtapa.getSelectedIndex() +1);
        
        
        
        c_conexion conexion  = new c_conexion();
        
        String consulta ="INSERT INTO etapas VALUES(?,?,?,?,?,?,?,?);";
        
        try {
            
            CallableStatement cs = conexion.establishConnection().prepareCall (consulta);
            
            cs.setInt(1, getId_etapa());
            cs.setString(2,getFecha_inicio());
            cs.setDouble(3, getKilometros());
            cs.setString(4, getOrigen());
            cs.setString(5,getDestino());
            cs.setDouble(6, getTiempo_estimado());
            cs.setString(7, getTipo_puerto());
            cs.setInt(8, getTipo_etapa());
            
            cs.execute();
            
            System.out.println("Se insertaron los datos correctamente");
            
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"No se insertaron los datos correctamente"+e.toString());
        }
    }
    
    public void mostrar_etapas(JTable tabla_etapas){
        c_conexion conexion = new c_conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        
        TableRowSorter<TableModel> ordenar_tabla = new TableRowSorter<>(modelo);
        tabla_etapas.setRowSorter(ordenar_tabla);
        
        String query = "";
        
        modelo.addColumn("Id Etapa");
        modelo.addColumn("Fecha Inicio");
        modelo.addColumn("Kilometros");
        modelo.addColumn("Origen");
        modelo.addColumn("Destino");
        modelo.addColumn("Tiempo Estimado");
        modelo.addColumn("Tipo Puerto");
        modelo.addColumn("Tipo Etapa");
        
        query = "SELECT id_etapa, fecha_inicio, kilometros, origen, destino, tiempo_estimado, tipo_puerto, tipo FROM etapas INNER JOIN tipo_etapa ON etapas.tipo_etapa = tipo_etapa.id_tipo_etapa;";
        
        tabla_etapas.setModel(modelo);
        
        String[] datos = new String[8];
        Statement st;
        
        try {
            
            st = conexion.establishConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                
                
                
                modelo.addRow(datos);
            }
            
            tabla_etapas.setModel(modelo);
            System.err.println("Datos de la tabla cargados");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al llamar los datos de la tabla, ERROR: "+ e.toString());
        }
        
    }
   public void seleccionar_etapa(JTable tabla_etapa, JSpinner paramid_etapa, JDateChooser paramfecha_inicio, JTextField paramKilometro, JTextField paramOrigen, JTextField paramDestino, JSpinner paramTiempoEstimado, JComboBox paramTipoPuerto, JComboBox paramTipoEtapa){
        
        Calendar calendario = new GregorianCalendar(paramfecha_inicio.getCalendar().get(Calendar.YEAR), paramfecha_inicio.getCalendar().get(Calendar.MARCH), paramfecha_inicio.getCalendar().get(Calendar.DAY_OF_MONTH));
       
        try {
            
            int fila = tabla_etapa.getSelectedRow();
            
            if(fila >= 0){
                
                paramid_etapa.setValue(tabla_etapa.getValueAt(fila, 0));
                paramfecha_inicio.setDate(calendario.getTime());
                paramKilometro.setText(tabla_etapa.getValueAt(fila,2).toString());
                paramOrigen.setText(tabla_etapa.getValueAt(fila,3).toString());
                paramDestino.setText(tabla_etapa.getValueAt(fila,4).toString());
                paramTiempoEstimado.setValue(tabla_etapa.getValueAt(fila,5));
                paramTipoPuerto.setSelectedItem(tabla_etapa.getValueAt(fila,6));
                paramTipoEtapa.setSelectedItem(tabla_etapa.getValueAt(fila,7));
                
                
                
            }else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de seleccion, ERROR: " + e.toString());
        }  
    }
    public void modificar( JSpinner paramid_etapa, JDateChooser paramfecha_inicio, JTextField paramKilometro, JTextField paramOrigen, JTextField paramDestino, JSpinner paramTiempoEstimado, JComboBox paramTipoPuerto, JComboBox paramTipoEtapa){
        
        Date mFecha = paramfecha_inicio.getDate();
        long fecha = mFecha.getTime();
        java.sql.Date fecha_sql = new java.sql.Date(fecha);
        
        setId_etapa((int) paramid_etapa.getValue());
        setFecha_inicio(fecha_sql.toString());
        setKilometros(Double.parseDouble(paramKilometro.getText()));
        setOrigen(paramOrigen.getText());
        setDestino(paramDestino.getText());
        setTiempo_estimado(Double.parseDouble(paramTiempoEstimado.getValue().toString()));
        setTipo_puerto(paramTipoPuerto.getSelectedItem().toString());
        setTipo_etapa(paramTipoEtapa.getSelectedIndex() +1);
        
        c_conexion conexion = new c_conexion();
        
        String query = "UPDATE etapas SET fecha_inicio = ?,kilometros = ?, origen = ?,destino  = ? ,tiempo_estimado = ?,tipo_puerto = ?,tipo_etapa = ? WHERE id_etapa = ?;";
        
        try {
            CallableStatement cs = conexion.establishConnection().prepareCall(query);
            
            cs.setInt(1, getId_etapa());
            cs.setString(2,getFecha_inicio());
            cs.setDouble(3, getKilometros());
            cs.setString(4, getOrigen());
            cs.setString(5,getDestino());
            cs.setDouble(6, getTiempo_estimado());
            cs.setString(7, getTipo_puerto());
            cs.setInt(8, getTipo_etapa());
            
            
            cs.execute();
            System.out.println("Modificado con exito");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al actualizar, ERROR: "+ e.toString());
            
        }
    }
   
    public void eliminar_etapa(JSpinner paramid_etapa){
        
        setId_etapa(Integer.parseInt((String) paramid_etapa.getValue()));
        
        c_conexion conexion = new c_conexion();
        
        String query = "DELETE FROM etapas WHERE id_etapa = ?;";
        
        
        try {
            CallableStatement cs = conexion.establishConnection().prepareCall(query);

            cs.setInt(1, getId_etapa());
            cs.execute();
            System.out.println("Eliminado con exito");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al eliminar, ERROR: "+ e.toString());
            
        }
        
    }
   
}
