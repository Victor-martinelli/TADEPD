/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.grupo1.proyecto.controllers;

import tad.grupo1.proyecto.DAO.DAO;
import tad.grupo1.proyecto.objects.UserVideo;

/**
 *
 * @author Portatil
 */
public class VideoController {
    
    DAO dao = new DAO();
    
    public UserVideo getVideo(String username,String title)
    {
        return dao.getVideo(username,title);
    }
    
    public String getVideoThumbnail(String username,String title)
    {
        return dao.getVideoThumbnailPath(username,title);
    }
    
    public String getProfilePicture(String username)
    {
        return dao.getUserProfilePath(username);
    }
    
    
}
