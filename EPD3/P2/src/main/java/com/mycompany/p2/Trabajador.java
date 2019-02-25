/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.p2;

import java.util.Date;

/**
 *
 * @author Lydia
 */
public class Trabajador {
    
    public String nombre;
    public String apellidos;
    public String dni;
    public String horaEntrada;
    public String horaSalida;
    public Date fecha;

    public Trabajador(String nombre, String apellidos, String dni, String horaEntrada, String horaSalida, Date fecha) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        String fechaFormat = fecha.getDate() + "/" + (fecha.getMonth()+1) + "/" + (fecha.getYear()+1900);
        return nombre + " " + apellidos + ", DNI: " + dni + ", Hora entrada: " + horaEntrada + ", Hora salida: " + horaSalida + ", Fecha: " + fechaFormat;
    }
    
    
}
