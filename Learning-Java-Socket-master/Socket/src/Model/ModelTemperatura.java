/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Allan
 */
public class ModelTemperatura extends Object{
    
    String temperatura;
    Date fecha;

    public ModelTemperatura() {
    }

    public ModelTemperatura(String temperatura, Date fecha) {
        this.temperatura = temperatura;
        this.fecha = fecha;
    }
 //=========================================================
    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
