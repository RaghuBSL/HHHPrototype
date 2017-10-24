/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author S Ragothaman
 */
@Entity
@Table(name = "partnermaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partnermaster.findAll", query = "SELECT p FROM Partnermaster p"),
    @NamedQuery(name = "Partnermaster.findByPartnerID", query = "SELECT p FROM Partnermaster p WHERE p.partnerID = :partnerID"),
    @NamedQuery(name = "Partnermaster.findByPartnerType", query = "SELECT p FROM Partnermaster p WHERE p.partnerType = :partnerType"),
    @NamedQuery(name = "Partnermaster.findByPartnerLegalname", query = "SELECT p FROM Partnermaster p WHERE p.partnerLegalname = :partnerLegalname"),
    @NamedQuery(name = "Partnermaster.findByProjectname", query = "SELECT p FROM Partnermaster p WHERE p.projectname = :projectname"),
    @NamedQuery(name = "Partnermaster.findByCompanyType", query = "SELECT p FROM Partnermaster p WHERE p.companyType = :companyType"),
    @NamedQuery(name = "Partnermaster.findByRegdHOPlace", query = "SELECT p FROM Partnermaster p WHERE p.regdHOPlace = :regdHOPlace"),
    @NamedQuery(name = "Partnermaster.findByCsrHead", query = "SELECT p FROM Partnermaster p WHERE p.csrHead = :csrHead"),
    @NamedQuery(name = "Partnermaster.findByCsrName", query = "SELECT p FROM Partnermaster p WHERE p.csrName = :csrName"),
    @NamedQuery(name = "Partnermaster.findByCsrMailID", query = "SELECT p FROM Partnermaster p WHERE p.csrMailID = :csrMailID"),
    @NamedQuery(name = "Partnermaster.findByCsrPhone", query = "SELECT p FROM Partnermaster p WHERE p.csrPhone = :csrPhone"),
    @NamedQuery(name = "Partnermaster.findByFactoryLocation", query = "SELECT p FROM Partnermaster p WHERE p.factoryLocation = :factoryLocation"),
    @NamedQuery(name = "Partnermaster.findByFactoryVillage", query = "SELECT p FROM Partnermaster p WHERE p.factoryVillage = :factoryVillage"),
    @NamedQuery(name = "Partnermaster.findByFactoryNearestTown", query = "SELECT p FROM Partnermaster p WHERE p.factoryNearestTown = :factoryNearestTown"),
    @NamedQuery(name = "Partnermaster.findByFactoryState", query = "SELECT p FROM Partnermaster p WHERE p.factoryState = :factoryState"),
    @NamedQuery(name = "Partnermaster.findByFactoryLocalCSRRep", query = "SELECT p FROM Partnermaster p WHERE p.factoryLocalCSRRep = :factoryLocalCSRRep"),
    @NamedQuery(name = "Partnermaster.findByCsrProgramName", query = "SELECT p FROM Partnermaster p WHERE p.csrProgramName = :csrProgramName"),
    @NamedQuery(name = "Partnermaster.findByCsrFocusArea", query = "SELECT p FROM Partnermaster p WHERE p.csrFocusArea = :csrFocusArea"),
    @NamedQuery(name = "Partnermaster.findByCsrInceptionYear", query = "SELECT p FROM Partnermaster p WHERE p.csrInceptionYear = :csrInceptionYear"),
    @NamedQuery(name = "Partnermaster.findByCsrCoverage", query = "SELECT p FROM Partnermaster p WHERE p.csrCoverage = :csrCoverage"),
    @NamedQuery(name = "Partnermaster.findByCsrCourses", query = "SELECT p FROM Partnermaster p WHERE p.csrCourses = :csrCourses"),
    @NamedQuery(name = "Partnermaster.findByCsrPlacementRecord", query = "SELECT p FROM Partnermaster p WHERE p.csrPlacementRecord = :csrPlacementRecord"),
    @NamedQuery(name = "Partnermaster.findByCsrAnnualBudget", query = "SELECT p FROM Partnermaster p WHERE p.csrAnnualBudget = :csrAnnualBudget"),
    @NamedQuery(name = "Partnermaster.findByCsrBudgetCenter", query = "SELECT p FROM Partnermaster p WHERE p.csrBudgetCenter = :csrBudgetCenter"),
    @NamedQuery(name = "Partnermaster.findByCsrBudgetApproval", query = "SELECT p FROM Partnermaster p WHERE p.csrBudgetApproval = :csrBudgetApproval"),
    @NamedQuery(name = "Partnermaster.findByCsrBoardName", query = "SELECT p FROM Partnermaster p WHERE p.csrBoardName = :csrBoardName"),
    @NamedQuery(name = "Partnermaster.findByHallAvailability", query = "SELECT p FROM Partnermaster p WHERE p.hallAvailability = :hallAvailability"),
    @NamedQuery(name = "Partnermaster.findByNumberOfLocation", query = "SELECT p FROM Partnermaster p WHERE p.numberOfLocation = :numberOfLocation"),
    @NamedQuery(name = "Partnermaster.findByHostelsAvailable", query = "SELECT p FROM Partnermaster p WHERE p.hostelsAvailable = :hostelsAvailable"),
    @NamedQuery(name = "Partnermaster.findByITinfrsDeails", query = "SELECT p FROM Partnermaster p WHERE p.iTinfrsDeails = :iTinfrsDeails"),
    @NamedQuery(name = "Partnermaster.findByAssistInMobilization", query = "SELECT p FROM Partnermaster p WHERE p.assistInMobilization = :assistInMobilization"),
    @NamedQuery(name = "Partnermaster.findByOtherNGODetails", query = "SELECT p FROM Partnermaster p WHERE p.otherNGODetails = :otherNGODetails"),
    @NamedQuery(name = "Partnermaster.findByAssistCanvassing", query = "SELECT p FROM Partnermaster p WHERE p.assistCanvassing = :assistCanvassing"),
    @NamedQuery(name = "Partnermaster.findByCaptiveFoundation", query = "SELECT p FROM Partnermaster p WHERE p.captiveFoundation = :captiveFoundation"),
    @NamedQuery(name = "Partnermaster.findByTrainerAccomodation", query = "SELECT p FROM Partnermaster p WHERE p.trainerAccomodation = :trainerAccomodation"),
    @NamedQuery(name = "Partnermaster.findByPowerBackup", query = "SELECT p FROM Partnermaster p WHERE p.powerBackup = :powerBackup"),
    @NamedQuery(name = "Partnermaster.findBySpecificTemplate", query = "SELECT p FROM Partnermaster p WHERE p.specificTemplate = :specificTemplate"),
    @NamedQuery(name = "Partnermaster.findByIntegrateCourses", query = "SELECT p FROM Partnermaster p WHERE p.integrateCourses = :integrateCourses"),
    @NamedQuery(name = "Partnermaster.findByIntegrateDetail", query = "SELECT p FROM Partnermaster p WHERE p.integrateDetail = :integrateDetail"),
    @NamedQuery(name = "Partnermaster.findByCriteriaAccepted", query = "SELECT p FROM Partnermaster p WHERE p.criteriaAccepted = :criteriaAccepted"),
    @NamedQuery(name = "Partnermaster.findByRelateAreas", query = "SELECT p FROM Partnermaster p WHERE p.relateAreas = :relateAreas"),
    @NamedQuery(name = "Partnermaster.findByTransportPotential", query = "SELECT p FROM Partnermaster p WHERE p.transportPotential = :transportPotential"),
    @NamedQuery(name = "Partnermaster.findByLegalPersion", query = "SELECT p FROM Partnermaster p WHERE p.legalPersion = :legalPersion"),
    @NamedQuery(name = "Partnermaster.findByErpTimeline", query = "SELECT p FROM Partnermaster p WHERE p.erpTimeline = :erpTimeline"),
    @NamedQuery(name = "Partnermaster.findByPaymentPersonName", query = "SELECT p FROM Partnermaster p WHERE p.paymentPersonName = :paymentPersonName"),
    @NamedQuery(name = "Partnermaster.findByPartyAddress", query = "SELECT p FROM Partnermaster p WHERE p.partyAddress = :partyAddress"),
    @NamedQuery(name = "Partnermaster.findByCity", query = "SELECT p FROM Partnermaster p WHERE p.city = :city"),
    @NamedQuery(name = "Partnermaster.findByPincode", query = "SELECT p FROM Partnermaster p WHERE p.pincode = :pincode"),
    @NamedQuery(name = "Partnermaster.findByTrusteename", query = "SELECT p FROM Partnermaster p WHERE p.trusteename = :trusteename"),
    @NamedQuery(name = "Partnermaster.findByContactPhone", query = "SELECT p FROM Partnermaster p WHERE p.contactPhone = :contactPhone"),
    @NamedQuery(name = "Partnermaster.findByMailID", query = "SELECT p FROM Partnermaster p WHERE p.mailID = :mailID"),
    @NamedQuery(name = "Partnermaster.findByNsdc", query = "SELECT p FROM Partnermaster p WHERE p.nsdc = :nsdc"),
    @NamedQuery(name = "Partnermaster.findByDduGky", query = "SELECT p FROM Partnermaster p WHERE p.dduGky = :dduGky"),
    @NamedQuery(name = "Partnermaster.findByPmky", query = "SELECT p FROM Partnermaster p WHERE p.pmky = :pmky"),
    @NamedQuery(name = "Partnermaster.findByPartnerStatus", query = "SELECT p FROM Partnermaster p WHERE p.partnerStatus = :partnerStatus"),
    @NamedQuery(name = "Partnermaster.findByLastUpdatedAt", query = "SELECT p FROM Partnermaster p WHERE p.lastUpdatedAt = :lastUpdatedAt"),
    @NamedQuery(name = "Partnermaster.findByUpdatedBy", query = "SELECT p FROM Partnermaster p WHERE p.updatedBy = :updatedBy")})
public class Partnermaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "partnerID")
    private Integer partnerID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "partnerType")
    private String partnerType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "partnerLegalname")
    private String partnerLegalname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "projectname")
    private String projectname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "companyType")
    private String companyType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "regdHOPlace")
    private String regdHOPlace;
    @Size(max = 40)
    @Column(name = "csrHead")
    private String csrHead;
    @Size(max = 40)
    @Column(name = "csrName")
    private String csrName;
    @Size(max = 64)
    @Column(name = "csrMailID")
    private String csrMailID;
    @Size(max = 12)
    @Column(name = "csrPhone")
    private String csrPhone;
    @Size(max = 40)
    @Column(name = "factoryLocation")
    private String factoryLocation;
    @Size(max = 40)
    @Column(name = "factoryVillage")
    private String factoryVillage;
    @Size(max = 40)
    @Column(name = "factoryNearestTown")
    private String factoryNearestTown;
    @Size(max = 40)
    @Column(name = "factoryState")
    private String factoryState;
    @Size(max = 40)
    @Column(name = "factoryLocalCSRRep")
    private String factoryLocalCSRRep;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "csrProgramName")
    private String csrProgramName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "csrFocusArea")
    private String csrFocusArea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "csrInceptionYear")
    private String csrInceptionYear;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "csrCoverage")
    private String csrCoverage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "csrCourses")
    private String csrCourses;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "csrPlacementRecord")
    private String csrPlacementRecord;
    @Size(max = 40)
    @Column(name = "csrAnnualBudget")
    private String csrAnnualBudget;
    @Size(max = 40)
    @Column(name = "csrBudgetCenter")
    private String csrBudgetCenter;
    @Size(max = 40)
    @Column(name = "csrBudgetApproval")
    private String csrBudgetApproval;
    @Size(max = 40)
    @Column(name = "csrBoardName")
    private String csrBoardName;
    @Size(max = 40)
    @Column(name = "hallAvailability")
    private String hallAvailability;
    @Size(max = 40)
    @Column(name = "numberOfLocation")
    private String numberOfLocation;
    @Size(max = 1)
    @Column(name = "hostelsAvailable")
    private String hostelsAvailable;
    @Size(max = 60)
    @Column(name = "ITinfrsDeails")
    private String iTinfrsDeails;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "assistInMobilization")
    private String assistInMobilization;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "otherNGODetails")
    private String otherNGODetails;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "assistCanvassing")
    private String assistCanvassing;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "captiveFoundation")
    private String captiveFoundation;
    @Size(max = 1)
    @Column(name = "trainerAccomodation")
    private String trainerAccomodation;
    @Size(max = 1)
    @Column(name = "powerBackup")
    private String powerBackup;
    @Size(max = 1)
    @Column(name = "specificTemplate")
    private String specificTemplate;
    @Size(max = 1)
    @Column(name = "integrateCourses")
    private String integrateCourses;
    @Size(max = 40)
    @Column(name = "integrateDetail")
    private String integrateDetail;
    @Size(max = 1)
    @Column(name = "criteriaAccepted")
    private String criteriaAccepted;
    @Size(max = 40)
    @Column(name = "relateAreas")
    private String relateAreas;
    @Size(max = 40)
    @Column(name = "transportPotential")
    private String transportPotential;
    @Size(max = 40)
    @Column(name = "legalPersion")
    private String legalPersion;
    @Size(max = 40)
    @Column(name = "erpTimeline")
    private String erpTimeline;
    @Size(max = 40)
    @Column(name = "paymentPersonName")
    private String paymentPersonName;
    @Size(max = 300)
    @Column(name = "partyAddress")
    private String partyAddress;
    @Size(max = 40)
    @Column(name = "city")
    private String city;
    @Size(max = 6)
    @Column(name = "pincode")
    private String pincode;
    @Size(max = 40)
    @Column(name = "trusteename")
    private String trusteename;
    @Size(max = 12)
    @Column(name = "contactPhone")
    private String contactPhone;
    @Size(max = 64)
    @Column(name = "mailID")
    private String mailID;
    @Size(max = 1)
    @Column(name = "nsdc")
    private String nsdc;
    @Size(max = 1)
    @Column(name = "ddu_gky")
    private String dduGky;
    @Size(max = 1)
    @Column(name = "pmky")
    private String pmky;
    @Size(max = 1)
    @Column(name = "partnerStatus")
    private String partnerStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lastUpdatedAt")
    @Temporal(TemporalType.DATE)
    private Date lastUpdatedAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updatedBy")
    private int updatedBy;

    public Partnermaster() {
    }

    public Partnermaster(Integer partnerID) {
        this.partnerID = partnerID;
    }

    public Partnermaster(Integer partnerID, String partnerType, String partnerLegalname, String projectname, String companyType, String regdHOPlace, String csrProgramName, String csrFocusArea, String csrInceptionYear, String csrCoverage, String csrCourses, String csrPlacementRecord, String assistInMobilization, String otherNGODetails, String assistCanvassing, String captiveFoundation, Date lastUpdatedAt, int updatedBy) {
        this.partnerID = partnerID;
        this.partnerType = partnerType;
        this.partnerLegalname = partnerLegalname;
        this.projectname = projectname;
        this.companyType = companyType;
        this.regdHOPlace = regdHOPlace;
        this.csrProgramName = csrProgramName;
        this.csrFocusArea = csrFocusArea;
        this.csrInceptionYear = csrInceptionYear;
        this.csrCoverage = csrCoverage;
        this.csrCourses = csrCourses;
        this.csrPlacementRecord = csrPlacementRecord;
        this.assistInMobilization = assistInMobilization;
        this.otherNGODetails = otherNGODetails;
        this.assistCanvassing = assistCanvassing;
        this.captiveFoundation = captiveFoundation;
        this.lastUpdatedAt = lastUpdatedAt;
        this.updatedBy = updatedBy;
    }

    public Integer getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(Integer partnerID) {
        this.partnerID = partnerID;
    }

    public String getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(String partnerType) {
        this.partnerType = partnerType;
    }

    public String getPartnerLegalname() {
        return partnerLegalname;
    }

    public void setPartnerLegalname(String partnerLegalname) {
        this.partnerLegalname = partnerLegalname;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getRegdHOPlace() {
        return regdHOPlace;
    }

    public void setRegdHOPlace(String regdHOPlace) {
        this.regdHOPlace = regdHOPlace;
    }

    public String getCsrHead() {
        return csrHead;
    }

    public void setCsrHead(String csrHead) {
        this.csrHead = csrHead;
    }

    public String getCsrName() {
        return csrName;
    }

    public void setCsrName(String csrName) {
        this.csrName = csrName;
    }

    public String getCsrMailID() {
        return csrMailID;
    }

    public void setCsrMailID(String csrMailID) {
        this.csrMailID = csrMailID;
    }

    public String getCsrPhone() {
        return csrPhone;
    }

    public void setCsrPhone(String csrPhone) {
        this.csrPhone = csrPhone;
    }

    public String getFactoryLocation() {
        return factoryLocation;
    }

    public void setFactoryLocation(String factoryLocation) {
        this.factoryLocation = factoryLocation;
    }

    public String getFactoryVillage() {
        return factoryVillage;
    }

    public void setFactoryVillage(String factoryVillage) {
        this.factoryVillage = factoryVillage;
    }

    public String getFactoryNearestTown() {
        return factoryNearestTown;
    }

    public void setFactoryNearestTown(String factoryNearestTown) {
        this.factoryNearestTown = factoryNearestTown;
    }

    public String getFactoryState() {
        return factoryState;
    }

    public void setFactoryState(String factoryState) {
        this.factoryState = factoryState;
    }

    public String getFactoryLocalCSRRep() {
        return factoryLocalCSRRep;
    }

    public void setFactoryLocalCSRRep(String factoryLocalCSRRep) {
        this.factoryLocalCSRRep = factoryLocalCSRRep;
    }

    public String getCsrProgramName() {
        return csrProgramName;
    }

    public void setCsrProgramName(String csrProgramName) {
        this.csrProgramName = csrProgramName;
    }

    public String getCsrFocusArea() {
        return csrFocusArea;
    }

    public void setCsrFocusArea(String csrFocusArea) {
        this.csrFocusArea = csrFocusArea;
    }

    public String getCsrInceptionYear() {
        return csrInceptionYear;
    }

    public void setCsrInceptionYear(String csrInceptionYear) {
        this.csrInceptionYear = csrInceptionYear;
    }

    public String getCsrCoverage() {
        return csrCoverage;
    }

    public void setCsrCoverage(String csrCoverage) {
        this.csrCoverage = csrCoverage;
    }

    public String getCsrCourses() {
        return csrCourses;
    }

    public void setCsrCourses(String csrCourses) {
        this.csrCourses = csrCourses;
    }

    public String getCsrPlacementRecord() {
        return csrPlacementRecord;
    }

    public void setCsrPlacementRecord(String csrPlacementRecord) {
        this.csrPlacementRecord = csrPlacementRecord;
    }

    public String getCsrAnnualBudget() {
        return csrAnnualBudget;
    }

    public void setCsrAnnualBudget(String csrAnnualBudget) {
        this.csrAnnualBudget = csrAnnualBudget;
    }

    public String getCsrBudgetCenter() {
        return csrBudgetCenter;
    }

    public void setCsrBudgetCenter(String csrBudgetCenter) {
        this.csrBudgetCenter = csrBudgetCenter;
    }

    public String getCsrBudgetApproval() {
        return csrBudgetApproval;
    }

    public void setCsrBudgetApproval(String csrBudgetApproval) {
        this.csrBudgetApproval = csrBudgetApproval;
    }

    public String getCsrBoardName() {
        return csrBoardName;
    }

    public void setCsrBoardName(String csrBoardName) {
        this.csrBoardName = csrBoardName;
    }

    public String getHallAvailability() {
        return hallAvailability;
    }

    public void setHallAvailability(String hallAvailability) {
        this.hallAvailability = hallAvailability;
    }

    public String getNumberOfLocation() {
        return numberOfLocation;
    }

    public void setNumberOfLocation(String numberOfLocation) {
        this.numberOfLocation = numberOfLocation;
    }

    public String getHostelsAvailable() {
        return hostelsAvailable;
    }

    public void setHostelsAvailable(String hostelsAvailable) {
        this.hostelsAvailable = hostelsAvailable;
    }

    public String getITinfrsDeails() {
        return iTinfrsDeails;
    }

    public void setITinfrsDeails(String iTinfrsDeails) {
        this.iTinfrsDeails = iTinfrsDeails;
    }

    public String getAssistInMobilization() {
        return assistInMobilization;
    }

    public void setAssistInMobilization(String assistInMobilization) {
        this.assistInMobilization = assistInMobilization;
    }

    public String getOtherNGODetails() {
        return otherNGODetails;
    }

    public void setOtherNGODetails(String otherNGODetails) {
        this.otherNGODetails = otherNGODetails;
    }

    public String getAssistCanvassing() {
        return assistCanvassing;
    }

    public void setAssistCanvassing(String assistCanvassing) {
        this.assistCanvassing = assistCanvassing;
    }

    public String getCaptiveFoundation() {
        return captiveFoundation;
    }

    public void setCaptiveFoundation(String captiveFoundation) {
        this.captiveFoundation = captiveFoundation;
    }

    public String getTrainerAccomodation() {
        return trainerAccomodation;
    }

    public void setTrainerAccomodation(String trainerAccomodation) {
        this.trainerAccomodation = trainerAccomodation;
    }

    public String getPowerBackup() {
        return powerBackup;
    }

    public void setPowerBackup(String powerBackup) {
        this.powerBackup = powerBackup;
    }

    public String getSpecificTemplate() {
        return specificTemplate;
    }

    public void setSpecificTemplate(String specificTemplate) {
        this.specificTemplate = specificTemplate;
    }

    public String getIntegrateCourses() {
        return integrateCourses;
    }

    public void setIntegrateCourses(String integrateCourses) {
        this.integrateCourses = integrateCourses;
    }

    public String getIntegrateDetail() {
        return integrateDetail;
    }

    public void setIntegrateDetail(String integrateDetail) {
        this.integrateDetail = integrateDetail;
    }

    public String getCriteriaAccepted() {
        return criteriaAccepted;
    }

    public void setCriteriaAccepted(String criteriaAccepted) {
        this.criteriaAccepted = criteriaAccepted;
    }

    public String getRelateAreas() {
        return relateAreas;
    }

    public void setRelateAreas(String relateAreas) {
        this.relateAreas = relateAreas;
    }

    public String getTransportPotential() {
        return transportPotential;
    }

    public void setTransportPotential(String transportPotential) {
        this.transportPotential = transportPotential;
    }

    public String getLegalPersion() {
        return legalPersion;
    }

    public void setLegalPersion(String legalPersion) {
        this.legalPersion = legalPersion;
    }

    public String getErpTimeline() {
        return erpTimeline;
    }

    public void setErpTimeline(String erpTimeline) {
        this.erpTimeline = erpTimeline;
    }

    public String getPaymentPersonName() {
        return paymentPersonName;
    }

    public void setPaymentPersonName(String paymentPersonName) {
        this.paymentPersonName = paymentPersonName;
    }

    public String getPartyAddress() {
        return partyAddress;
    }

    public void setPartyAddress(String partyAddress) {
        this.partyAddress = partyAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getTrusteename() {
        return trusteename;
    }

    public void setTrusteename(String trusteename) {
        this.trusteename = trusteename;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getMailID() {
        return mailID;
    }

    public void setMailID(String mailID) {
        this.mailID = mailID;
    }

    public String getNsdc() {
        return nsdc;
    }

    public void setNsdc(String nsdc) {
        this.nsdc = nsdc;
    }

    public String getDduGky() {
        return dduGky;
    }

    public void setDduGky(String dduGky) {
        this.dduGky = dduGky;
    }

    public String getPmky() {
        return pmky;
    }

    public void setPmky(String pmky) {
        this.pmky = pmky;
    }

    public String getPartnerStatus() {
        return partnerStatus;
    }

    public void setPartnerStatus(String partnerStatus) {
        this.partnerStatus = partnerStatus;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (partnerID != null ? partnerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partnermaster)) {
            return false;
        }
        Partnermaster other = (Partnermaster) object;
        if ((this.partnerID == null && other.partnerID != null) || (this.partnerID != null && !this.partnerID.equals(other.partnerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Partnermaster[ partnerID=" + partnerID + " ]";
    }
    
}
