/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.AuditInfra;
import entity.PartnerOnboard;
import entity.Partnermaster;
import entity.TraineeList;
import entity.Trainer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author S Ragothaman
 */
@Named("traineeController")
@SessionScoped
public class TraineeController implements Serializable {

    
    
    private int activeIndex = 1;
    private Boolean disabledTab0 = true;
    private Boolean disabledTab1 = false;
    private Boolean disabledTab2 = true;
    private Boolean disabledTab3 = true;
    
    //
    
    private TraineeList selected;
    private List<TraineeList> items;
    
    @Inject
    session.AppMapBean appMapEJB;
    
    
    public TraineeController() {
    }

    @PostConstruct
    public void init() {
    }

    public void tabNext() {
        if( activeIndex < 2 ) {
            activeIndex++;
        }
        switch( activeIndex) {
            case 1:
                disabledTab0 = true;
                disabledTab1 = false;
                disabledTab2 = true;
                disabledTab3 = true;
                break;
             case 2:
                disabledTab0 = true;
                disabledTab1 = true;
                disabledTab2 = false;
                disabledTab3 = true;
                break;
             case 3:        // Not in the screen Provisional
                disabledTab0 = true;
                disabledTab1 = true;
                disabledTab2 = true;
                disabledTab3 = false;
                break;    
        }
        System.out.println("tab next " + String.valueOf( activeIndex));
    }
    public void tabPrev() {
        if( activeIndex > 0 ) {
            activeIndex--;
        }
         switch( activeIndex) {
            case 0:
                disabledTab0 = false;
                disabledTab1 = true;
                disabledTab2 = true;
                disabledTab3 = true;
                break;
             case 1:
                disabledTab0 = true;
                disabledTab1 = false;
                disabledTab2 = true;
                disabledTab3 = true;
                break;
             case 2:
                disabledTab0 = true;
                disabledTab1 = true;
                disabledTab2 = false;
                disabledTab3 = true;
                break;    
        }
        System.out.println("tab Prev " + String.valueOf( activeIndex));
        
    }

    /**
     * @return the activeIndex
     */
    public int getActiveIndex() {
        return activeIndex;
    }

    /**
     * @param activeIndex the activeIndex to set
     */
    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }

    /**
     * @return the disabledTab0
     */
    public Boolean getDisabledTab0() {
        return disabledTab0;
    }

    /**
     * @param disabledTab0 the disabledTab0 to set
     */
    public void setDisabledTab0(Boolean disabledTab0) {
        this.disabledTab0 = disabledTab0;
    }

    /**
     * @return the disabledTab1
     */
    public Boolean getDisabledTab1() {
        return disabledTab1;
    }

    /**
     * @param disabledTab1 the disabledTab1 to set
     */
    public void setDisabledTab1(Boolean disabledTab1) {
        this.disabledTab1 = disabledTab1;
    }

    /**
     * @return the disabledTab2
     */
    public Boolean getDisabledTab2() {
        return disabledTab2;
    }

    /**
     * @param disabledTab2 the disabledTab2 to set
     */
    public void setDisabledTab2(Boolean disabledTab2) {
        this.disabledTab2 = disabledTab2;
    }

    /**
     * @return the disabledTab3
     */
    public Boolean getDisabledTab3() {
        return disabledTab3;
    }

    /**
     * @param disabledTab3 the disabledTab3 to set
     */
    public void setDisabledTab3(Boolean disabledTab3) {
        this.disabledTab3 = disabledTab3;
    }

    /**
     * @return the selected
     */
    public TraineeList getSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(TraineeList selected) {
        this.selected = selected;
    }

    /**
     * @return the items
     */
    public List<TraineeList> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<TraineeList> items) {
        this.items = items;
    }
}
