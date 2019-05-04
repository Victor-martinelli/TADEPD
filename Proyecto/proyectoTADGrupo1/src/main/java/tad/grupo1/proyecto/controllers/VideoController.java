/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.grupo1.proyecto.controllers;

import com.vaadin.ui.Upload;
import java.io.FileOutputStream;
import tad.grupo1.proyecto.DAO.DAO;
import tad.grupo1.proyecto.objects.UserVideo;

/**
 *
 * @author Portatil
 */
public class VideoController {
    
    DAO dao = new DAO();
    
    //Esto incrementa el contador de visitas y coge el video (esto es para la vista de ver videos)
    public UserVideo playVideo(String username,String title)
    {
        dao.incrementVideoViews(title);
        return getVideo(username,title);
    }
    
    public FileOutputStream uploadVideo(String username,String filename,String mimeType, int typeCheck)
    {
        return dao.upload(username,filename,mimeType,typeCheck);
    }
    
    //Solamente coge la informacion del video
    public UserVideo getVideo(String username,String title)
    {
        return dao.getVideo(username,title);
    }
    
    public String getVideoThumbnail(String username,String title)
    {
        return dao.getVideoThumbnailPath(username,title);
    }
    
    public void publishComment(String title,String username,String comment)
    {
        dao.publishComment(title,username,comment);
    }
    
    public void likeVideo(String title,String username)
    {
        dao.likeVideo(title, username);
    }
    
    public void unlikeVideo(String title,String username)
    {
        dao.unlikeVideo(title, username);
    }
    
    public void dislikeVideo(String title,String username)
    {
        dao.dislikeVideo(title, username);
    }
    
    public void undislikeVideo(String title,String username)
    {
        dao.undislikeVideo(title, username);
    }

    
}
