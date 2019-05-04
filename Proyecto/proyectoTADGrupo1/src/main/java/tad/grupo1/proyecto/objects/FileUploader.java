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
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import static tad.grupo1.proyecto.views.MainScreen.vc;

public class FileUploader implements Upload.Receiver {

    
    private String username;
    /*
            0 --> Comprobar imagen
            1 --> Comprobar video
     */
    private int typeCheck;

    public FileUploader(String username, int typeCheck) {
        this.username = username;
        this.typeCheck = typeCheck;
    }

    public OutputStream receiveUpload(String filename,
            String mimeType) {
        // Create upload stream
        FileOutputStream fos = null; // Stream to write to
        fos = vc.uploadVideo(username, filename, mimeType, typeCheck);

        return fos; // Return the output stream to write to
    }

};
