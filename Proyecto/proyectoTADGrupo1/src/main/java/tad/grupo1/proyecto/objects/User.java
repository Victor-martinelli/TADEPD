/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.grupo1.proyecto.objects;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Portatil
 */
public class User {
    private String username;
    private int suscriptores;
    private List<String> suscripciones;

    public User(String username, int suscriptores) {
        this.username = username;
        this.suscriptores = suscriptores;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public int getSuscriptores() {
        return suscriptores;
    }

    public void setSuscriptores(int suscriptores) {
        this.suscriptores = suscriptores;
    }

    public List<String> getSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(List<String> suscripciones) {
        this.suscripciones = suscripciones;
    }
    
    public void addSuscriptores()
    {
        suscriptores++;
    }
    
    public void removeSuscriptores()
    {
        suscriptores--;
    }
    
    
}
