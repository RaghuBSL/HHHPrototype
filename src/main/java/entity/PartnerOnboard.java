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
public class PartnerOnboard {
    private int onboardID;
    private int partnerID;
    private Date onboardDate;
    private String hhhRepName;
    private String remarks;
    private String participant;
    private String phone;
    private String mailID;

    /**
     * @return the onboardID
     */
    public int getOnboardID() {
        return onboardID;
    }

    /**
     * @param onboardID the onboardID to set
     */
    public void setOnboardID(int onboardID) {
        this.onboardID = onboardID;
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
     * @return the onboardDate
     */
    public Date getOnboardDate() {
        return onboardDate;
    }

    /**
     * @param onboardDate the onboardDate to set
     */
    public void setOnboardDate(Date onboardDate) {
        this.onboardDate = onboardDate;
    }

    /**
     * @return the hhhRepName
     */
    public String getHhhRepName() {
        return hhhRepName;
    }

    /**
     * @param hhhRepName the hhhRepName to set
     */
    public void setHhhRepName(String hhhRepName) {
        this.hhhRepName = hhhRepName;
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
     * @return the participant
     */
    public String getParticipant() {
        return participant;
    }

    /**
     * @param participant the participant to set
     */
    public void setParticipant(String participant) {
        this.participant = participant;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the mailID
     */
    public String getMailID() {
        return mailID;
    }

    /**
     * @param mailID the mailID to set
     */
    public void setMailID(String mailID) {
        this.mailID = mailID;
    }
    
    
}
