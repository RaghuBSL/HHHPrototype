/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author S Ragothaman
 */
public class AuditInfra {
    private int infraID;
    private int partnerID;
    private Date auditDate;
    private int auditID;
    private String description;
    private String statusYes;
    private String statusNo;
    private Integer quantity;
    private Date completionDate;
    private String remarks;
    private String score;

    /**
     * @return the infraID
     */
    public int getInfraID() {
        return infraID;
    }

    /**
     * @param infraID the infraID to set
     */
    public void setInfraID(int infraID) {
        this.infraID = infraID;
    }

    /**
     * @return the partnerID
     */
    public int getPartnerID() {
        return partnerID;
    }

    /**
     * @param partnerID the partnerID to set
     */
    public void setPartnerID(int partnerID) {
        this.partnerID = partnerID;
    }

    /**
     * @return the auditDate
     */
    public Date getAuditDate() {
        return auditDate;
    }

    /**
     * @param auditDate the auditDate to set
     */
    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    /**
     * @return the auditID
     */
    public int getAuditID() {
        return auditID;
    }

    /**
     * @param auditID the auditID to set
     */
    public void setAuditID(int auditID) {
        this.auditID = auditID;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the statusYes
     */
    public String getStatusYes() {
        return statusYes;
    }

    /**
     * @param statusYes the statusYes to set
     */
    public void setStatusYes(String statusYes) {
        this.statusYes = statusYes;
    }

    /**
     * @return the statusNo
     */
    public String getStatusNo() {
        return statusNo;
    }

    /**
     * @param statusNo the statusNo to set
     */
    public void setStatusNo(String statusNo) {
        this.statusNo = statusNo;
    }

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the completionDate
     */
    public Date getCompletionDate() {
        return completionDate;
    }

    /**
     * @param completionDate the completionDate to set
     */
    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    /**
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return the score
     */
    public String getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(String score) {
        this.score = score;
    }
    
    
}
