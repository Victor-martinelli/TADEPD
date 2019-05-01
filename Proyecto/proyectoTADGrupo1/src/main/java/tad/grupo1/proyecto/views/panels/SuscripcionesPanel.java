/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.grupo1.proyecto.views.panels;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import tad.grupo1.proyecto.controllers.VideoController;

/**
 *
 * @author Lydia
 */
public class SuscripcionesPanel extends CssLayout implements View{
    
    VideoController vc = new VideoController();

    public SuscripcionesPanel() {
        
    }   
    

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
    
}
