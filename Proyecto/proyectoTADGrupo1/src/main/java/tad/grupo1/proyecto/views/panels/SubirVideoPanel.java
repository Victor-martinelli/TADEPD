/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.grupo1.proyecto.views.panels;

import com.vaadin.navigator.View;
import com.vaadin.server.AbstractErrorMessage;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import tad.grupo1.proyecto.objects.FileUploader;
import static tad.grupo1.proyecto.views.MainScreen.vc;

/**
 *
 * @author Portatil
 */
public class SubirVideoPanel extends CssLayout implements View{
    
    private VerticalLayout content;
    
    public SubirVideoPanel(String username)
    {
    
        VerticalLayout labels = new VerticalLayout();
        
        Label title = new Label("<h2>Subida de Videos</h2>",ContentMode.HTML);
        Label warning = new Label("Ten en cuenta que el titulo del video sera el nombre del archivo sin la extension",ContentMode.HTML);
        
        content = new VerticalLayout();
        
        labels.addComponents(title,warning);
        labels.setSizeFull();
        labels.setMargin(false);
        labels.setSpacing(false);
        content.setSizeFull();
        
        labels.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
        labels.setComponentAlignment(warning, Alignment.MIDDLE_CENTER);
        
        content.addComponents(labels,createUploadForm(username));
        
        content.setComponentAlignment(labels, Alignment.MIDDLE_RIGHT);
        
        
        
        
        addComponent(content);
        
    }
    
    public Upload createUploadForm(String username)
    {
    
        return new Upload("Sube el video aqui",new FileUploader(username,1));
    }
    
}
