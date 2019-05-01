/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.grupo1.proyecto.objects;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Portatil
 */
public class UserComment {
    
    private Date date;
    private String comment;
    private String username;

    public UserComment(Date date, String comment, String username) {
        this.date = date;
        this.comment = comment;
        this.username = username;
    }

    
    
    
    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 

       return formatter.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    
    
}
