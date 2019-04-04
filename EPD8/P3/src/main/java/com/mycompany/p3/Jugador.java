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
public class Jugador {
    
    private String nombre;
    
    private String dorsal;
    
    private Equipo Equipo;

    public Jugador(String nombre, String dorsal, Equipo Equipo) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.Equipo = Equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDorsal() {
        return dorsal;
    }

    public Equipo getEquipo() {
        return Equipo;
    }
    
    
    
    
}
