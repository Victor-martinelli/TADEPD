/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.grupo1.proyecto.objects;

/**
 *
 * @author Portatil
 */
//Esto solamente lo utiliza para guardar archivos
import com.vaadin.server.Page;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import static tad.grupo1.proyecto.views.MainScreen.vc;
import static tad.grupo1.proyecto.views.MainUI.session;
import tad.grupo1.proyecto.views.panels.SubirVideoPanel;

public class FileUploader implements Upload.Receiver ,SucceededListener {

    
    private String username;
    /*
            0 --> Comprobar imagen
            1 --> Comprobar video
     */
    private int typeCheck;
    private CssLayout layout;
    private String title;

    public FileUploader(CssLayout layout, String username, int typeCheck) {
        this.username = username;
        this.typeCheck = typeCheck;
        this.layout=layout;
    }

    public FileUploader(CssLayout layout, String username, int typeCheck,String title) {
        this.username = username;
        this.typeCheck = typeCheck;
        this.layout = layout;
        this.title = title;
    }

    public OutputStream receiveUpload(String filename,
            String mimeType) {
        // Create upload stream
        FileOutputStream fos = null; // Stream to write to
        if(typeCheck==1)
        {
            
            session.setAttribute("currentVideo",filename.substring(0, filename.indexOf(".")));
            fos = vc.uploadVideo(username, filename);
            
        }
        else
        {
            fos = vc.uploadThumnbail(username,title, filename);
        }

        return fos; // Return the output stream to write to
    }
    
    //What happends when upload is successul
    public void uploadSucceeded(SucceededEvent event) {
        //Estabamos comprobando un video
       if(typeCheck==1)
       {
           Notification.show("Exito",
                  "El video se ha subido correctamente",
                  Notification.Type.HUMANIZED_MESSAGE);
           
           SubirVideoPanel aux = (SubirVideoPanel)layout;
           
           aux.createSecondUploader();
           
       }
    }
    
    public Upload createThumbUploadForm()
    {
    
        return new Upload("Sube la imagen aqui",this);
    }
    
    public Upload createVideoUploadForm()
    {
    
        return new Upload("Sube el video aqui",this);
    }

};
