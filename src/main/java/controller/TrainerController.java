/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.AuditInfra;
import entity.PartnerOnboard;
import entity.Partnermaster;
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
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author S Ragothaman
 */
@Named("trainerController")
@SessionScoped
public class TrainerController implements Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
   
    //
    private String level;
    private String trainerPosition;
    private Date startDate;
    private Date endDate;
    private String trainerCreateForm = "/Trainer/TrainerCreate.xhtml";
    private String trainerEditForm = "/Trainer/TrainerEdit.xhtml";
    private Boolean listButton = true;
    private Boolean createButton = true;
    @Inject
    session.AppMapBean appMapEJB;
    private List<Trainer> items ;
   
    private Trainer selected;
    
    
    public TrainerController() {
    }

    @PostConstruct
    public void init() {
    }
    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the trainerPosition
     */
    public String getTrainerPosition() {
        return trainerPosition;
    }

    /**
     * @param trainerPosition the trainerPosition to set
     */
    public void setTrainerPosition(String trainerPosition) {
        this.trainerPosition = trainerPosition;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the trainerCreateForm
     */
    public String getTrainerCreateForm() {
        System.out.println("Trainer Create Called"   +  trainerCreateForm );
        return trainerCreateForm;
    }

    /**
     * @param trainerCreateForm the trainerCreateForm to set
     */
    public void setTrainerCreateForm(String trainerCreateForm) {
        this.trainerCreateForm = trainerCreateForm;
    }

    /**
     * @return the items
     */
    public List<Trainer> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<Trainer> items) {
        this.items = items;
    }

    /**
     * @return the trainerEditForm
     */
    public String getTrainerEditForm() {
         System.out.println("Trainer Edit Called" + trainerEditForm);
        return trainerEditForm;
    }

}
