/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import com.toedter.calendar.JCalendar;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;

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
    String tipo_etapa;
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

    public String getTipo_etapa() {
        return tipo_etapa;
    }

    public void setTipo_etapa(String tipo_etapa) {
        this.tipo_etapa = tipo_etapa;
    }
   
    public void InsertarEtapa(JSpinner paramid_etapa, JCalendar paramfecha_inicio, JTextField paramKilometro, JTextField paramOrigen,JTextField paramDestino, JSpinner paramTiempoEstimado,JComboBox paramTipoPuerto, JComboBox paramTipoEtapa){
 
        setId_etapa((int) paramid_etapa.getValue());
        setFecha_inicio(paramfecha_inicio.getDate().toString());
        setKilometros(Double.parseDouble(paramKilometro.getText()));
        setOrigen(paramOrigen.getText());
        setDestino(paramDestino.getText());
        setTiempo_estimado(Double.parseDouble(paramTiempoEstimado.getValue().toString()));
        
        
        
        
        
       
    }
        
        

    
}
