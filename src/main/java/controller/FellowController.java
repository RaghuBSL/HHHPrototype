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
@Named("fellowController")
@SessionScoped
public class FellowController implements Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
   
    //
    private String level;
    private String trainerPosition;
    private Date startDate;
    private Date endDate;
    private String fellowCreateForm = "/Fellow/FellowCreate.xhtml";
    private String fellowEditForm = "/Fellow/FellowEdit.xhtml";
    private String round1 = "/Fellow/Round1.xhtml";
    private String round2 = "/Fellow/Round2.xhtml";
    private String round3 = "/Fellow/Round3.xhtml";
    
    private Boolean listButton = true;
    private Boolean createButton = true;
    @Inject
    session.AppMapBean appMapEJB;
    private List<Trainer> items ;
   
    private Trainer selected;
    
    
    public FellowController() {
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
   
    /**
     * @return the fellowCreateForm
     */
    public String getFellowCreateForm() {
        return fellowCreateForm;
    }

    /**
     * @param fellowCreateForm the fellowCreateForm to set
     */
    public void setFellowCreateForm(String fellowCreateForm) {
        this.fellowCreateForm = fellowCreateForm;
    }

    /**
     * @return the fellowEditForm
     */
    public String getFellowEditForm() {
        return fellowEditForm;
    }

    /**
     * @return the round1
     */
    public String getRound1() {
        return round1;
    }

    /**
     * @param round1 the round1 to set
     */
    public void setRound1(String round1) {
        this.round1 = round1;
    }

    /**
     * @return the round2
     */
    public String getRound2() {
        return round2;
    }

    /**
     * @param round2 the round2 to set
     */
    public void setRound2(String round2) {
        this.round2 = round2;
    }

    /**
     * @return the round3
     */
    public String getRound3() {
        return round3;
    }

    /**
     * @param round3 the round3 to set
     */
    public void setRound3(String round3) {
        this.round3 = round3;
    }

}
