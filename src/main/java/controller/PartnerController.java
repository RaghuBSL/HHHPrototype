/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.AuditInfra;
import entity.PartnerOnboard;
import entity.Partnermaster;
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
@Named("partnerController")
@SessionScoped
public class PartnerController implements Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private List<Partnermaster> items = null;
    private Partnermaster selected;
    private List<AuditInfra> infraItems;
    private AuditInfra infra;
    //
    private List<PartnerOnboard> onboardItems;
    private PartnerOnboard onboard;
    
    
    private String partnerType;         // N - NGO  C- Corp
    private Boolean listButton = true;
    private Boolean createButton = true;
    private String editScreenName;
    private String createScreenName;
    private String screening;
    @Inject
    session.AppMapBean appMapEJB;
    private List<Date>  auditList = new ArrayList<>();
    private Date selectedAuditDate;
    public PartnerController() {
        this.infraItems = new ArrayList<AuditInfra>();
        this.onboardItems = new ArrayList<PartnerOnboard>();
    }

    /**
     * @return the items
     */
    public List<Partnermaster> getItems() {
        return items;
    }

    @PostConstruct
    public void init() {
         Date date1 = new Date(2017, Calendar.JULY, 3);
         Date date2 = new Date(2017, Calendar.AUGUST, 3);

        infra = new AuditInfra();
        onboard= new PartnerOnboard();
        auditList.add(date1);
        auditList.add(date2);
        
    }
    public void infraGo() {
        infra.setAuditDate(selectedAuditDate);
    }

    public void addInfra() {
        
       // appMapEJB.getInfraCheckList().forEach((key, value) -> {\
       for(String key: appMapEJB.getInfraCheckList().keySet()) {
            if (  appMapEJB.getInfraCheckList().get(key)  == infra.getAuditID()) {
                infra.setDescription(key);
            }
        };
          System.out.println(" infra line added for " + infra.getDescription());
        if (infra.getStatusYes().equals("Yes")) {
            infra.setStatusNo("");
          //  infra.setStatusYes("Yes");
        } else {
            infra.setStatusNo("No");
            infra.setStatusYes("");
        }
        infraItems.add(infra);
        Date ad = infra.getAuditDate();  // restore date
        System.out.println(" infra line added for " + infra.getDescription() +  " size " + String.valueOf(infraItems.size()) );
        infra = new AuditInfra();
        infra.setAuditDate(ad);

    }
    public String onboardEntry() {
        System.out.println(" In Onboard Entry ");
        return( "/Partner/PartnerOnboarding.xhtml");
    }

    public void partnerTypeOnclick() {

        if (partnerType.equals("N")) {
            editScreenName = "/Partner/PartnerNGOEdit.xhtml";
            createScreenName = "/Partner/PartnerNGOCreate.xhtml";

        } else {
            editScreenName = "/Partner/PartnerCorpEdit.xhtml";
            createScreenName = "/Partner/PartnerCorpCreate.xhtml";

        }
        listButton = false;
        createButton = false;
        System.out.println(" Onclick done in radio " + partnerType + " " + editScreenName + " " + createScreenName);

    }

    /**
     * @param items the items to set
     */
    public void setItems(List<Partnermaster> items) {
        this.items = items;
    }

    /**
     * @return the selected
     */
    public Partnermaster getSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(Partnermaster selected) {
        this.selected = selected;
    }

    /**
     * @return the partnerType
     */
    public String getPartnerType() {
        return partnerType;
    }

    /**
     * @param partnerType the partnerType to set
     */
    public void setPartnerType(String partnerType) {
        this.partnerType = partnerType;
    }

    /**
     * @return the listButton
     */
    public Boolean getListButton() {
        return listButton;
    }

    /**
     * @param listButton the listButton to set
     */
    public void setListButton(Boolean listButton) {
        this.listButton = listButton;
    }

    /**
     * @return the createButton
     */
    public Boolean getCreateButton() {
        return createButton;
    }

    /**
     * @param createButton the createButton to set
     */
    public void setCreateButton(Boolean createButton) {
        this.createButton = createButton;
    }

    /**
     * @return the editScreenName
     */
    public String getEditScreenName() {
        return editScreenName;
    }

    /**
     * @return the createScreenName
     */
    public String getCreateScreenName() {
        return createScreenName;
    }

    /**
     * @return the infraItems
     */
    public List<AuditInfra> getInfraItems() {
        return infraItems;
    }

    /**
     * @param infraItems the infraItems to set
     */
    public void setInfraItems(List<AuditInfra> infraItems) {
        this.infraItems = infraItems;
    }

    /**
     * @return the infra
     */
    public AuditInfra getInfra() {
        return infra;
    }

    /**
     * @param infra the infra to set
     */
    public void setInfra(AuditInfra infra) {
        this.infra = infra;
    }

    /**
     * @return the auditList
     */
    public List<Date> getAuditList() {
        return auditList;
    }

    /**
     * @param auditList the auditList to set
     */
    public void setAuditList(List<Date> auditList) {
        this.auditList = auditList;
    }

    /**
     * @return the selectedAuditDate
     */
    public Date getSelectedAuditDate() {
        return selectedAuditDate;
    }

    /**
     * @param selectedAuditDate the selectedAuditDate to set
     */
    public void setSelectedAuditDate(Date selectedAuditDate) {
        this.selectedAuditDate = selectedAuditDate;
    }

    /**
     * @return the onboardItems
     */
    public List<PartnerOnboard> getOnboardItems() {
        return onboardItems;
    }

    /**
     * @param onboardItems the onboardItems to set
     */
    public void setOnboardItems(List<PartnerOnboard> onboardItems) {
        this.onboardItems = onboardItems;
    }

    /**
     * @return the onboard
     */
    public PartnerOnboard getOnboard() {
        return onboard;
    }

    /**
     * @param onboard the onboard to set
     */
    public void setOnboard(PartnerOnboard onboard) {
        this.onboard = onboard;
    }

    /**
     * @return the screening
     */
    public String getScreening() {
        return screening;
    }

    /**
     * @param screening the screening to set
     */
    public void setScreening(String screening) {
        this.screening = screening;
    }

}
