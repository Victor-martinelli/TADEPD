package com.mycompany.playvideotest;

import javax.servlet.annotation.WebServlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.Page;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;

import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Video;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

        class FileUploader implements Receiver {

            private File file;

            public OutputStream receiveUpload(String filename,
                    String mimeType) {
                // Create upload stream
                FileOutputStream fos = null; // Stream to write to
                try {
                    // Open the file for writing.
                    file = new File(basepath + File.separator+"videos"+File.separator+filename);
                    fos = new FileOutputStream(file);
                } catch (final java.io.FileNotFoundException e) {
                    new Notification("Could nasepath + File.separator+\"videos\"+File.separator+filenameot open file<br/>",
                            e.getMessage(),
                            Notification.Type.ERROR_MESSAGE)
                            .show(Page.getCurrent());
                    return null;
                }
                return fos; // Return the output stream to write to
            }
        };
        
        Upload upload = new Upload("Upload it here", new FileUploader());


        /*
        Video sample = new Video();
        final Resource mp4Resource = new FileResource(
                new File(basepath+File.separator+"videos"+File.separator+"despacito2.mp4"));
        sample.setSource(mp4Resource);
        sample.setSizeFull();
        sample.setHtmlContentAllowed(true);
        sample.setWidth("640px");
        sample.setHeight("320px");
        sample.setAltText("Can't play media");
        
        //layout.addComponent(sample);
        layout.addComponent(new Label(basepath+File.separator+"videos"+File.separator+"despacito2.mp4"));
        //layout.setMargin(true);
        //layout.setSpacing(true);
        

         */
        layout.addComponent(upload);
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlsetMarginet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
