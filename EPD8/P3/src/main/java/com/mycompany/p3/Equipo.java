/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.p3;

/**
 *
 * @author Portatil
 */
public class Equipo {
    
    private String nombre;
    private String nombreEstadio;

    public Equipo(String nombre, String nombreEstadio) {
        this.nombre = nombre;
        this.nombreEstadio = nombreEstadio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreEstadio() {
        return nombreEstadio;
    }
    
    
    
    
}
