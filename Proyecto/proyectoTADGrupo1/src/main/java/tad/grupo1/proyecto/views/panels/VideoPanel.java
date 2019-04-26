/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.grupo1.proyecto.views.panels;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;

/**
 *
 * @author Portatil
 */
public class VideoPanel extends CssLayout implements View{

    
    public VideoPanel()
    {
        setSizeFull();
        HorizontalSplitPanel mainLayout = new HorizontalSplitPanel();
        
        mainLayout.addComponent(new Label("REEEEEEEEEEEEEEE"));
        
    }
    
    
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
    
}
