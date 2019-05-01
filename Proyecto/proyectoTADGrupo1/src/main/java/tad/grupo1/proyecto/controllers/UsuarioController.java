/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.grupo1.proyecto.controllers;

import tad.grupo1.proyecto.DAO.DAO;

/**
 *
 * @author Lydia
 */
public class UsuarioController {
    
    DAO dao = new DAO();
    
    public void registrarUsuario(String username, String email, String password){
        dao.insertUsuario(username, email, password);
    }
    
    public Boolean comprobarLogin(String username, String password){
        return dao.login(username, password);
    }
    
    public Boolean comprobarUsername(String username){
        return dao.getUsername(username);
    }
    
}
