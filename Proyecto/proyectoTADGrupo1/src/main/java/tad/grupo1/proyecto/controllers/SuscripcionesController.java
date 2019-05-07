/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.grupo1.proyecto.controllers;

import java.util.List;
import tad.grupo1.proyecto.model.DAO;

/**
 *
 * @author Lydia
 */
public class SuscripcionesController {
    
    DAO dao = new DAO();
    
    public Object getSuscripciones(String username){
        return dao.getSuscripciones(username);
    }
    
    public Object getVideoSuscrito(String username){
        return dao.getSuscritoVideo(username);
    }
    
}
