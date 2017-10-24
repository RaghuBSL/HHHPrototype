/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import common.NavigationBean;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
//import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import session.util.JsfUtil;
// import survey.AudittrailController;

//import javax.ejb.Stateless;
//import javax.faces.bean.ViewScoped;
/**
 * All bugs are noted here 1.
 *
 *
 *
 *
 */
@Named("appMapBean")
@SessionScoped
public class AppMapBean implements Serializable {

    // @Inject
    // UsersecurityController userEJB;
    private Date lastActivity;

    public void idleListener() {
        java.util.Calendar calJava = GregorianCalendar.getInstance(TimeZone.getTimeZone("Asia/Kolkata"));
        calJava.add(Calendar.MINUTE, -2);
        lastActivity = calJava.getTime();

    }

    public void timeout() throws IOException {
        logger.info(" Session timed out");
        // before close remove user from the session map
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = ec.getSessionMap();
        if (sessionMap.containsKey("User")) {
            sessionMap.remove("User");
        }
        //  ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/faces/index.xhtml");
        FacesContext.getCurrentInstance().responseComplete();
        //FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "index.xhtml?faces-redirect=true");  
    }

    @PostConstruct
    public void setup() {
        // setupRightsLevel();
        setupBankyesno();
        setupStates();
        setupActive();
        setupYears();
        setupMonths();
        setupAttendance();
        setSurvey();
        setAuditFiles();
        setSurveyType();
        setScheduleStatus();
        setScheduleChecked();
        setTraineeStatus();
        setTrainingReason();
        setBuzzTrainingTrace();
        setRationCard();
        setMaritalStatus();
        setEducation();
        setSocialCategory();
        setTrailStatus();
        setupFamilyBusiness();
        setupStartedBusiness();
        setNgoType();
        yearSelected = 0;
        monthSelected = 0;
    }
    //  @Inject
    //  AudittrailController auditEJB;
    // ver 33-02 all Autocomplete controls have been replaced with selectoneMenu
    // ver 36-04 is corrected for time zone
    // ver 36-06 is for closing all reopened items gelathi edit, trainer update
    // ver 36-07 is updared abstartc facade pf EDIt for constraint violation
    // ver 38-01 is with report writer
    // ver 40-03 with report and cache clear for db back end updates

    // ver 40 -04 excel upload for old data has no Father name, now accepted
    // ver 41-01  login redirect with filter mapping  
    // ver 42-01   f;view Action is not working in server though works locally
    //             filter is written without use f:action meta tag   
    // 42: 02 Excel upkoad now demands Aadhar and phone be text and not numbers
    // 42:03  wrong XLSX format error is now displayed
    // 43:01  excel upload for training is now a separate object and init for every upload
    // 43:02  aadhar and phone can have blanks, odometer os for day1 and 2 separate 
    // 44:01  download error file, errMessage clean up 
    // 45:01  day1 update failed, revised web.xml for JSF framework config
    // 45:02  Report error, Null check for day1, project etc 
    // 45:03  Report bugs fixed 
    // 46 : 01 Report bugs for trg days 
    // 47 : 01 merging village master with schedule creation
    // 47 : 02 rights menu corrected for 1 report
    // 47 : 03 batch validate phone error corrected. Creae rights object error reversed
    // 48 : 01 reporting errors, and aadhaar validation error corrected
    // 48 : 02 Integer obj compare failed in aadhar corrected, Submit on confirm now being messaged
    // 48 : 03   error display is now with 6 hours added 
    // 49, 50 51 has issues and so NO production release
    // 51 : 01 report scroll width adjusted
    // 51 : 02 report on Day2 has no impact on day1
    // 51 : 03 explicit GC call, reset PWD has confirm dialog , Timeout in 30 mtes
    //         buzzReport.xhtml has now a f:view tag to show only text and html for correct mimetype
    //         introduced to check missing date buttons UNTESTED in PROD 
    // 52 01 pre Destroy in all controllers  "[1-9][0-9 }{19}"
    // 52 02 Store village name or sub village name
    private TimeZone tz = TimeZone.getTimeZone("Asia/Kolkata");
    java.util.Calendar calJava = GregorianCalendar.getInstance(TimeZone.getTimeZone("Asia/Kolkata"));
    private final Date date = calJava.getTime();
    static Logger logger = LoggerFactory.getLogger(AppMapBean.class);
    private String hhhfVersion = "HHHF MIC Platform System Demo version 01-06";
    // schedule master all dates are timezoned
    private String loginName;
    // All validator messages are put here for ease of 
    private String workPhoneValidatorMessage = "Phone number should be like 41101234 / 25501234.";
    private String workPhonePattern = "^[1-9]([0-9]+){7}$";
    //
    private String genPhoneValidatorMessage = "Phone number should be like 41101234 / 25501234 / 9845012345 / 919820212345";
    private String genPhonePattern = "(^[1-9](\\d){7}$)|((91)?(\\d){10}$)";
    // validation string effective from version 36
    private String namePattern = "(^[a-zA-Z])([.',/ a-zA-Z0-9]*)([a-zA-Z0-9]$)";
    private String nameValidatorMessage = "Name can only have letters, digits, dot comma, quotes and no trailing spaces";
    private String educationValidatorMessage = "Education can only have letters hyphen and digits.";

    //private String namePattern = "(^[a-zA-Z])([.', a-zA-Z]*)([a-zA-Z]$)";
    //private String nameValidatorMessage = "Name can only have letters, dot comma and quotes.";
    private String orgValidatorMessage = "Organization can only have letters, dot comma and quotes.";
    private String desgValidatorMessage = "Designation can only have letters hyphen and digits.";
    private String occpValidatorMessage = "Occupation can only have letters hyphen and digits.";
    private String desgPattern = "[a-zA-Z1-9- ]+";
    //
    private String cityPattern = "[a-zA-Z ]+";
    private String cityValidatorMessage = "City can only have letters & spaces between words.";
    private String stateValidatorMessage = "State can only have letters & spaces between words.";
    private String pincodePattern = "^[1-9]([0-9]+){5}$";
    private String pincodeValidatorMessage = "Pincode can only have 6 digits and no spaces.";
    private String mobilePattern = "^( 0|(91) )?[1-9](\\d){9}$";  ///"[1-9][0-9]+{9,11}";
    private String aadhaarValidatorMessage = "Aadhaar can only have 12 digits and no spaces.";
    private String aadhaarPattern = "^[1-9](\\d){11}$";
    private String amountPattern = "^[1-9][0-9]+$";       // allow only 7 digits in front end
    private String amountValidatorMessage = "Amount can only have 7 digits no decimals, leading zeros";
    private String ifscValidatorMessage = "IFSC can only have 11 digits A-Z(4) and 7 digits and no spaces.";
    // private String ifscPattern = "^[A-Z][A-Z0-9]{10}$";
    private String ifscPattern = "^[A-Z]{4}[0-9]{7}$";
    private String participantValidatorMessage = "Participant can only have numbers and no spaces.";
    private String participantPattern = "^[1-9](\\d){2}$";
    private String incomePattern = "[0-9]+";
    private String incomeValidatorMessage = "Amount can have only digits and no spaces.";

    private String bankaccountValidatorMessage = "Bank Account can only have 9-18 digits and no spaces.";
    private String bankaccountPattern = "^[1-9](\\d){9,18}$";
    private String employeeValidatorMessage = "Employee ID can have only digits and no spaces ";
    private String employeePattern = "[0-9]+";
    private String mobileValidatorMessage = "Mobile number like 8041101234 / 9845012345 / 09820212345 / 918888812345";
    private String mailPattern = "^[a-zA-Z0-9_!#$%*+/=?{}~^.-]+@[a-zA-Z0-9.-]+$";
    private String mailPatternFull
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private String mailValidatorMessage = "Mail id is like firstname.othername@domain.net.au";
    private String occupationValidatorMessage = "";

    //
    private String eidValidatorMessage = "Employee ID can be numbers only";
    private String slotChar = "#";

    /////////*  vars only for prototype to be removed during DEV */
    private String partnerType;
    private Date entryDate;

    public String partnerEdit() {
        //  naviPageEJB.setPage("");
        System.out.println("In partner Edit with  " + partnerType);
        //if( partnerType.equals("N")) {
        return ("/Partner/PartnerNGOEdit.xhtml");
        //} else {
        //    return("/Partner/PartnerCorpEdit.xhtml");
        //}
    }

    public String cancelForm() {
        // naviPageEJB.setPage("");
        return "master.xhtml";
    }

    /**
     * ***************
     */
    private Map<String, Character> rightsLevel = new LinkedHashMap<>();
    // private List<String> indianStates = new ArrayList<String>();
    private List<String> indianStatesList = new ArrayList<>();
    private Map<String, Integer> indianStates = new LinkedHashMap();
    private Map<String, Integer> activeState = new LinkedHashMap();
    private Map<String, Integer> attdState = new LinkedHashMap();
    private Map<String, Integer> traineeStatus = new LinkedHashMap();
    private Map<String, String> fileAudit = new LinkedHashMap();
    private Map<String, String> bankYesno = new LinkedHashMap();
    private Map<String, String> scheduleStatus = new LinkedHashMap();
    private Map<String, String> trailStatus = new LinkedHashMap();
    private Map<String, String> reasonForTraining = new LinkedHashMap();
    private Map<String, String> familyBusiness = new LinkedHashMap();
    private Map<String, String> startedBusiness = new LinkedHashMap();
    private Map<String, Character> buzzTrace = new LinkedHashMap();
    private Map<String, Character> rationCard = new LinkedHashMap();
    private Map<String, Character> maritalStatus = new LinkedHashMap();
    private Map<String, Character> socialCategory = new LinkedHashMap();
    private Map<String, Character> education = new LinkedHashMap();
    private Map<Integer, Integer> yearList = new LinkedHashMap<>();
    private Map<String, Integer> monthList = new LinkedHashMap<>();
    private Map<String, String> monthReportList = new LinkedHashMap<>();
    private Map<String, Boolean> checked = new LinkedHashMap<>();
    private Map<String, String> surveyResult = new LinkedHashMap<>();
    private Map<String, String> surveyType = new LinkedHashMap<>();
    private Map<String, String> ngoType = new LinkedHashMap<>();
    private Map<String, String> companyType = new LinkedHashMap<>();
    private Map<String, String> yesNo = new LinkedHashMap<>();
    private Map<String, String> partnerStatus = new LinkedHashMap<>();
    private Map<String, Integer> infraCheckList = new LinkedHashMap<>();
    private Map<String, Integer> siteEvaluationCheckList = new LinkedHashMap<>();
    private Map<String, String> trainerPosition = new LinkedHashMap<>();
    private Map<String, String> gender = new LinkedHashMap<>();
    private Map<String, String> levelTrainer = new LinkedHashMap<>();
    private Map<String, String> sourceType = new LinkedHashMap<>();
    private Map<String, String> trainerStatus = new LinkedHashMap<>();
    private Map<String, String> ivwStatus = new LinkedHashMap<>();
    private Map<String, String> fellowType = new LinkedHashMap<>();
    private Map<String, String> ivwMode = new LinkedHashMap<>();
    private Map<String, String> bloodGroup = new LinkedHashMap<>();
    private Map<String, String> uniqueID = new LinkedHashMap<>();
    private Map<String, String> eduStatus = new LinkedHashMap<>();
    private Map<String, String> approvalStatus = new LinkedHashMap<>();
    //
    private Integer yearSelected = 0;
    private Integer monthSelected = 0;

    /*
    MyBean newInstance = new MyBean();
    FacesContext.getCurrentInstance().getExternalContext()
   .getSessionMap().put(beanName, newInstance);
     */
    private void setSurvey() {
        getSurveyResult().put("Not Uploaded", null);
        getSurveyResult().put("Yes", "Y");
        getSurveyResult().put("No", "N");
    }

    private void setSurveyType() {
        getSurveyType().put("Telephone", "T");
        getSurveyType().put("Direct", "D");
    }

    /* The workflow steps are 
- Registered
- Screening in Progress
- Screening Failed
- Screening Passed
- Agreement in Progress
- Agreement In Place
- Onboarding in Progress
- Onboarded
- Exit Requested
- Seperated
     */
    private void setNgoType() {
        getNgoType().put("Proprietorship", "P");
        getNgoType().put("Society", "S");
        getNgoType().put("Trust", "T");
        getNgoType().put("Unregistered", "U");
        //
        getYesNo().put("Yes", "Yes");
        getYesNo().put("No", "No");
        getPartnerStatus().put("Registered", "RG");
        getPartnerStatus().put("Screening in Progress", "SG");
        getPartnerStatus().put("Screening Failed", "SF");
        getPartnerStatus().put("Screening Passed", "SP");
        getPartnerStatus().put("Agreement in Progress", "AG");
        getPartnerStatus().put("Agreement in Place", "AP");
        getPartnerStatus().put("Onboarding in Progress", "OG");
        getPartnerStatus().put("Onboarded", "OB");
        getPartnerStatus().put("Exit Requested", "ER");
        getPartnerStatus().put("Separated", "SP");
        //
        getInfraCheckList().put("Classroom", 10);
        getInfraCheckList().put("Computer Lab", 20);
        getInfraCheckList().put("Computers For training", 30);
        getInfraCheckList().put("Fan", 40);
        getInfraCheckList().put("Light", 50);
        getInfraCheckList().put("Chairs", 60);
        getInfraCheckList().put("Trainer Table", 70);
        getInfraCheckList().put("Laptop", 80);
        getInfraCheckList().put("Projector", 90);
        getInfraCheckList().put("Speakers", 100);
        getInfraCheckList().put("White board", 110);
        getInfraCheckList().put("Blackboard", 120);
        getInfraCheckList().put("Markers ", 130);
        getInfraCheckList().put("Chalk Pieces", 140);
        getInfraCheckList().put("Duster", 150);
        getInfraCheckList().put("Mirrors", 160);
        getInfraCheckList().put("Sketch Pens", 170);
        getInfraCheckList().put("Chart", 180);
        getInfraCheckList().put("Trainee Manual", 190);
        getInfraCheckList().put("Trainer Manual", 200);
        getInfraCheckList().put("Printer", 210);
        getInfraCheckList().put("Internet Connection", 220);
        getInfraCheckList().put("Ladies Toilet Facility", 230);
        getInfraCheckList().put("Gents Toilet Facility", 240);
        getInfraCheckList().put("How many Windows? Is the class Room Airy & enough natural light", 250);
        getInfraCheckList().put("How far is it from main Busstop / Railway Station", 260);
        getInfraCheckList().put("Does the trainers have any space for conducting outdoor activity?", 270);
        //
        getGender().put("Male", "M");
        getGender().put("Female", "F");
        getGender().put("Others", "O");
        //
        getTrainerPosition().put("Fellow", "F");
        getTrainerPosition().put("Trainer", "T");
        getTrainerPosition().put("All", "A");
        //
        getLevelTrainer().put("1", "1");
        getLevelTrainer().put("2", "2");
        getLevelTrainer().put("3", "3");
        getLevelTrainer().put("4", "4");
        getLevelTrainer().put("All", "A");
        //
        getCompanyType().put("Trust", "T");
        getCompanyType().put("Society", "S");
        getCompanyType().put("Company", "C");
        getCompanyType().put("Partnership", "P");
        getCompanyType().put("PSU/Govt Undertaking", "G");
        //
        getSiteEvaluationCheckList().put("Training Centers *", 10);
        getSiteEvaluationCheckList().put("Core areas that they work on *", 10);
        getSiteEvaluationCheckList().put("Source of funds", 20);
        getSiteEvaluationCheckList().put("Training halls with chairs *", 30);
        getSiteEvaluationCheckList().put("Full time staff", 40);
        getSiteEvaluationCheckList().put("PC Labs with internet", 50);
        getSiteEvaluationCheckList().put("LCD Projectors, boards, mirroes, sound system", 60);
        getSiteEvaluationCheckList().put("Student mobilization capabilities", 70);
        getSiteEvaluationCheckList().put("Acceptance to HHHF 1000 hour format *", 80);
        getSiteEvaluationCheckList().put("Hostel", 90);
        getSiteEvaluationCheckList().put("Other Key Items: gensets, motors", 100);
        getSiteEvaluationCheckList().put("What fee they want to charge the trainess for such a program", 110);
        getSiteEvaluationCheckList().put("Do they understand the whole project?", 120);
        getSiteEvaluationCheckList().put("Parallel Vocational courses needed", 130);
        getSiteEvaluationCheckList().put("Local branding / image os this institution", 140);
        getSiteEvaluationCheckList().put("Key Contact person name & contacts *", 150);
        getSiteEvaluationCheckList().put("is this the primary business for them?", 160);
        getSiteEvaluationCheckList().put("Transportation", 170);
        //
        getSourceType().put("Consultancy", "C");
        getSourceType().put("Employee referral", "E");
        getSourceType().put("Online Application", "W");
        getSourceType().put("Activity", "A");
        getSourceType().put("Others", "O");
        //
        getTrainerStatus().put("Selected", "S");
        getTrainerStatus().put("Rejected", "R");
        getTrainerStatus().put("Follow-Up", "S");
        //
        getApprovalStatus().put("Approved", "A");
        getApprovalStatus().put("Not Approved", "N");
        //
        getFellowType().put("Rural", "R");
        getFellowType().put("Urban", "U");
        //
        getIvwMode().put("Telephone", "T");
        getIvwMode().put("Personal", "P");
        getIvwMode().put("Written", "W");
        getIvwMode().put("Online", "O");
        //
        getBloodGroup().put("O", "O");
        getBloodGroup().put("A", "A");
        getBloodGroup().put("B", "B");
        getBloodGroup().put("AB", "AB");
        getBloodGroup().put("B Negative", "B-");
        getBloodGroup().put("B Positive", "B+");   
        //
        getUniqueID().put("Aadhaar", "A");
        getUniqueID().put("Voters ID", "V");
        getUniqueID().put("Bank Account", "B");
        getUniqueID().put("Pan card", "P");
        getUniqueID().put("Driving License", "D");
        getUniqueID().put("Ration Card ", "R");
        getUniqueID().put("BPL Card", "L");
        getUniqueID().put("Passport", "S");
        getUniqueID().put("Others", "O");
        //
        getEduStatus().put("Illiterate", "1");
        getEduStatus().put("5th Pass", "2");
        getEduStatus().put("8th Pass", "3");
        getEduStatus().put("10th Pass", "4");
        getEduStatus().put("ITI/Diploma couse", "5");
        getEduStatus().put("12th Pass", "6");
        getEduStatus().put("Graduate", "7");
        getEduStatus().put("Post Graduate", "8");
        //
        
        
        
        
        
    }

    private void setScheduleStatus() {
        getScheduleStatus().put("No Status", null);
        getScheduleStatus().put("Creation", "A");
        getScheduleStatus().put("Error", "E");
        getScheduleStatus().put("Submitted", "S");
        getScheduleStatus().put("Confirmed", "C");
    }

    private void setTrainingReason() {
        getReasonForTraining().put("Not Selected", null);
        getReasonForTraining().put("Further Education", "Further Education");
        getReasonForTraining().put("Find Suitable Job", "Find Suitable Job");
        getReasonForTraining().put("Setup an Enterprise", "Setup an Enterprise");
        getReasonForTraining().put("Learn Financial Management", "Learn Financial Management");

    }

    private void setBuzzTrainingTrace() {
        getBuzzTrace().put("Not Selected", null);
        getBuzzTrace().put("Family", 'a');
        getBuzzTrace().put("Friends/Relatives", 'b');
        getBuzzTrace().put("SHG", 'c');
        getBuzzTrace().put("SMS/Whatsapp", 'd');
        getBuzzTrace().put("NGOs", 'e');
        // Govt. CDPO/ Gelathi /Media
        getBuzzTrace().put("Govt. CDPO", 'f');
        getBuzzTrace().put("Gelathi", 'g');
        getBuzzTrace().put("Media", 'h');

    }

    private void setTrailStatus() {
        getTrailStatus().put("Data Changed", "E");
        getTrailStatus().put("Record Deletion", "D");

    }

    private void setupMonths() {
        getMonthList().put("Select Month", 0);
        getMonthList().put("January", 1);
        getMonthList().put("February", 2);
        getMonthList().put("March", 3);
        getMonthList().put("April", 4);
        getMonthList().put("May", 5);
        getMonthList().put("June", 6);
        getMonthList().put("July", 7);
        getMonthList().put("August", 8);
        getMonthList().put("September", 9);
        getMonthList().put("October", 10);
        getMonthList().put("November", 11);
        getMonthList().put("December", 12);
        getMonthList().put("        ", 99);  // for all totals in reports to display blank
        //
        //getMonthReportList().put("       ", 0);
        getMonthReportList().put("January", "01");
        getMonthReportList().put("February", "02");
        getMonthReportList().put("March", "03");
        getMonthReportList().put("April", "04");
        getMonthReportList().put("May", "05");
        getMonthReportList().put("June", "06");
        getMonthReportList().put("July", "07");
        getMonthReportList().put("August", "08");
        getMonthReportList().put("September", "09");
        getMonthReportList().put("October", "10");
        getMonthReportList().put("November", "11");
        getMonthReportList().put("December", "12");
        getMonthReportList().put("        ", "  ");  // for all totals in reports to display blank        
    }

    private void setAuditFiles() {
        fileAudit.put("All", "All");
        fileAudit.put("Fundermaster", "Fundermaster");
        fileAudit.put("Trainermaster", "Trainermaster");
        fileAudit.put("Batchmaster", "Batchmaster");
        fileAudit.put("Projectmaster", "Projectmaster");
        fileAudit.put("Gelathimaster", "Gelathimaster");
        fileAudit.put("Partnermaster", "Partnermaster");
        fileAudit.put("Training", "Training");
    }

    private void setupYears() {

        int year = Calendar.getInstance().get(Calendar.YEAR);
        //getYearList().put("", 0);
        // set for 1 more year to survey
        year += 1;
        // survey start year is 2012
        for (int n = 2012; n <= year; n++) {
            getYearList().put((n), (n));
        }

    }

    public int getStateCode(String name) {
        if (indianStates.containsKey(name)) {
            return indianStates.get(name);
        }
        return 0;
    }

    public void setupStates() {
        // each state code is not known for state Karanataka is 29
        getIndianStates().put("Andhra Pradesh", 0);
        getIndianStates().put("Arunachal Pradesh", 0);
        getIndianStates().put("Assam", 0);
        getIndianStates().put("Bihar", 0);
        getIndianStates().put("Chhattisgarh", 0);
        getIndianStates().put("Goa", 0);
        getIndianStates().put("Gujarat", 0);
        getIndianStates().put("Haryana", 0);
        getIndianStates().put("Himachal Pradesh", 0);
        getIndianStates().put("Jammu and Kashmir", 0);
        getIndianStates().put("Jharkhand", 0);
        getIndianStates().put("Karnataka", 29);
        getIndianStates().put("Kerala", 0);
        getIndianStates().put("Madhya Pradesh", 0);
        getIndianStates().put("Maharashtra", 0);
        getIndianStates().put("Manipur", 0);
        getIndianStates().put("Meghalaya", 0);
        getIndianStates().put("Mizoram", 0);
        getIndianStates().put("Nagaland", 0);
        getIndianStates().put("Odisha", 0);
        getIndianStates().put("Punjab", 0);
        getIndianStates().put("Rajasthan", 0);
        getIndianStates().put("Sikkim", 0);
        getIndianStates().put("Tamil Nadu", 0);
        getIndianStates().put("Telangana", 0);
        getIndianStates().put("Tripura", 0);
        getIndianStates().put("Uttar Pradesh", 0);
        getIndianStates().put("Uttarakhand", 0);
        getIndianStates().put("West Bengal", 0);
        getIndianStates().put("Andaman and Nicobar Islands", 0);
        getIndianStates().put("Chandigarh", 0);
        getIndianStates().put("Dadar and Nagar Haveli", 0);
        getIndianStates().put("Daman and Diu", 0);
        getIndianStates().put("Delhi", 0);
        getIndianStates().put("Lakshadweep", 0);
        getIndianStates().put("Puducherry", 0);
        for (String sName : indianStates.keySet()) {
            indianStatesList.add(sName);
        }
    }

    private void setupActive() {
        getActiveState().put("Active Trainer", 1);
        getActiveState().put("Not Active", 2);
    }

    private void setupBankyesno() {
        getBankYesno().put("Unmarked", null);
        getBankYesno().put("Yes", "Y");
        getBankYesno().put("No", "N");
        getBankYesno().put("Yes", "y");
        getBankYesno().put("No", "n");
    }

    private void setupStartedBusiness() {
        getStartedBusiness().put("Unmarked", null);
        getStartedBusiness().put("Started Business Before Buzz Training", "B");
        getStartedBusiness().put("Started Business After  Buzz Training", "A");
    }

    private void setupFamilyBusiness() {
        getFamilyBusiness().put("Unmarked", null);
        getFamilyBusiness().put("Family Business", "F");
        getFamilyBusiness().put("My Own Business", "M");
    }

    private void setupAttendance() {
        getAttdState().put("Unmarked", 0);
        getAttdState().put("Present", 1);
        getAttdState().put("Absent", 2);
    }

    private void setTraineeStatus() {
        getTraineeStatus().put("Created", 1);
        getTraineeStatus().put("Error ", 2);
        getTraineeStatus().put("Validated", 3);
    }

    private void setMaritalStatus() {
        getMaritalStatus().put("Not Selected", null);
        getMaritalStatus().put("Single", 'a');
        getMaritalStatus().put("Married", 'b');
        getMaritalStatus().put("Widowed", 'c');
        getMaritalStatus().put("Divorced", 'd');
        getMaritalStatus().put("Separated", 's');
    }

    private void setEducation() {
        getEducation().put("Not Selected", null);
        getEducation().put("Below Primary", 'a');
        getEducation().put("Primary", 'b');
        getEducation().put("Secondary", 'c');
        getEducation().put("Higher Secondary", 'd');
        getEducation().put("Graduate & Above", 'e');
    }

    private void setSocialCategory() {
        getSocialCategory().put("Not Selected", null);
        getSocialCategory().put("Scheduled Tribe", 'a');
        getSocialCategory().put("Scheduled Caste", 'b');
        getSocialCategory().put("Other Backward Caste", 'c');
        getSocialCategory().put("General", 'd');
    }

    private void setScheduleChecked() {
        getChecked().put("Not Done", false);
        getChecked().put("Conducted", true);
        getChecked().put("No Data", null);

    }

    private void setRationCard() {
        getRationCard().put("Not Selected", null);
        getRationCard().put("APL", 'a');
        getRationCard().put("BPL", 'b');
        getRationCard().put("Antodaya", 'c');
        getRationCard().put("None", 'd');

    }

    public void showConstraintViolation(ConstraintViolationException e) {
        // build constraint error
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            sb.append("Error: " + violation.getPropertyPath() + " : " + violation.getMessage() + "\n");

        }
        logger.info(sb.toString());
        // JsfUtil.addErrorMessage(sb.toString());
        //Notification.show(sb.toString(), Type.ERROR_MESSAGE);
    }

    //
    public void showRollbackViolation(RollbackException r) {
        /*
        // build constraint error
        StringBuilder sb = new StringBuilder();
        for (RollbackException<?> violation : e.  .getConstraintViolations()) {
            sb.append("Error: " + violation.getPropertyPath() + " : " + violation.getMessage() + "\n");

        }
        logger.info(sb.toString());
        JsfUtil.addErrorMessage( sb.toString());
        //Notification.show(sb.toString(), Type.ERROR_MESSAGE);
         */
    }

    public Map<String, Character> getRightsLevel() {
        return rightsLevel;
    }

    /**
     * @param purposeOfMoneyTransfer the purposeOfMoneyTransfer to set
     */
    public void setPurposeOfMoneyTransfer(Map<String, Character> rightsLevel) {
        this.rightsLevel = rightsLevel;
    }

    /**
     * @return the workPhoneValidatorMessage
     */
    public String getWorkPhoneValidatorMessage() {
        return workPhoneValidatorMessage;
    }

    /**
     * @param workPhoneValidatorMessage the workPhoneValidatorMessage to set
     */
    public void setWorkPhoneValidatorMessage(String workPhoneValidatorMessage) {
        this.workPhoneValidatorMessage = workPhoneValidatorMessage;
    }

    /**
     * @return the workPhonePattern
     */
    public String getWorkPhonePattern() {
        return workPhonePattern;
    }

    /**
     * @param workPhonePattern the workPhonePattern to set
     */
    public void setWorkPhonePattern(String workPhonePattern) {
        this.workPhonePattern = workPhonePattern;
    }

    /**
     * @return the namePattern
     */
    public String getNamePattern() {
        return namePattern;
    }

    /**
     * @param namePattern the namePattern to set
     */
    public void setNamePattern(String namePattern) {
        this.namePattern = namePattern;
    }

    /**
     * @return the nameValidatorMessage
     */
    public String getNameValidatorMessage() {
        return nameValidatorMessage;
    }

    /**
     * @param nameValidatorMessage the nameValidatorMessage to set
     */
    public void setNameValidatorMessage(String nameValidatorMessage) {
        this.nameValidatorMessage = nameValidatorMessage;
    }

    /**
     * @return the orgValidatorMessage
     */
    public String getOrgValidatorMessage() {
        return orgValidatorMessage;
    }

    /**
     * @return the desgValidatorMessage
     */
    public String getDesgValidatorMessage() {
        return desgValidatorMessage;
    }

    /**
     * @return the desgPattern
     */
    public String getDesgPattern() {
        return desgPattern;
    }

    /**
     * @return the cityPattern
     */
    public String getCityPattern() {
        return cityPattern;
    }

    /**
     * @return the cityValidatorMessage
     */
    public String getCityValidatorMessage() {
        return cityValidatorMessage;
    }

    /**
     * @return the stateValidatorMessage
     */
    public String getStateValidatorMessage() {
        return stateValidatorMessage;
    }

    /**
     * @return the pincodePattern
     */
    public String getPincodePattern() {
        return pincodePattern;
    }

    /**
     * @return the pincodeValidatorMessage
     */
    public String getPincodeValidatorMessage() {
        return pincodeValidatorMessage;
    }

    /**
     * @return the mobilePattern
     */
    public String getMobilePattern() {
        return mobilePattern;
    }

    /**
     * @return the mobileValidatorMessage
     */
    public String getMobileValidatorMessage() {
        return mobileValidatorMessage;
    }

    /**
     * @return the mailPattern
     */
    public String getMailPattern() {
        return mailPattern;
    }

    /**
     * @return the mailValidatorMessage
     */
    public String getMailValidatorMessage() {
        return mailValidatorMessage;
    }

    /**
     * @return the indianStates
     */
    public Map<String, Integer> getIndianStates() {
        return indianStates;
    }

    /**
     * @return the activeState
     */
    public Map<String, Integer> getActiveState() {
        return activeState;
    }

    /**
     * @return the genPhoneValidatorMessage
     */
    public String getGenPhoneValidatorMessage() {
        return genPhoneValidatorMessage;
    }

    /**
     * @return the genPhonePattern
     */
    public String getGenPhonePattern() {
        return genPhonePattern;
    }

    /**
     * @return the occpValidatorMessage
     */
    public String getOccpValidatorMessage() {
        return occpValidatorMessage;
    }

    /**
     * @return the aadhaarValidatorMessage
     */
    public String getAadhaarValidatorMessage() {
        return aadhaarValidatorMessage;
    }

    /**
     * @return the aadhaarPattern
     */
    public String getAadhaarPattern() {
        return aadhaarPattern;
    }

    /**
     * @return the ifscValidatorMessage
     */
    public String getIfscValidatorMessage() {
        return ifscValidatorMessage;
    }

    /**
     * @return the ifscPattern
     */
    public String getIfscPattern() {
        return ifscPattern;
    }

    /**
     * @return the participantValidatorMessage
     */
    public String getParticipantValidatorMessage() {
        return participantValidatorMessage;
    }

    /**
     * @return the participantPattern
     */
    public String getParticipantPattern() {
        return participantPattern;
    }

    /**
     * @return the bankaccountValidatorMessage
     */
    public String getBankaccountValidatorMessage() {
        return bankaccountValidatorMessage;
    }

    /**
     * @return the bankaccountPattern
     */
    public String getBankaccountPattern() {
        return bankaccountPattern;
    }

    /**
     * @return the mailPatternFull
     */
    public String getMailPatternFull() {
        return mailPatternFull;
    }

    public void menuTrainer() {
        logger.info("Master-Menu Trainer invoked ");
    }

    public void menuManageBatches() {
        logger.info("Transaction-Menu Manage Batches invoked ");
    }

    public void menuConfirmBatches() {
        logger.info("Menu Confirm Batches invoked ");
    }

    public void menuFollowup() {
        logger.info("Transaction-Menu Followup Creation invoked ");
    }

    public void menuGelathiSurvey() {
        logger.info("Transaction-Menu Gelathi Survey Creation invoked ");
    }

    public void menuAudit() {
        logger.info("Transaction-Menu Audit Listing invoked ");
    }

    /**
     * @return the eidValidatorMessage
     */
    public String getEidValidatorMessage() {
        return eidValidatorMessage;
    }

    /**
     * @return the slotChar
     */
    public String getSlotChar() {
        return slotChar;
    }

    public void validateZipMask(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        //logger.info("end reading {}", value.toString() );
        if (value != null) {
            String property = value.toString();
            if (property.contains("#")) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                // message.setSummary("Pin Code invalid");
                message.setDetail("Pincode must have all 6 digits");
                // FacesContext context = FacesContext.getCurrentInstance();
                // context.addMessage("Pincode Error", message);
                throw new ValidatorException(message);
                // 
            }
        }
    }

    public void validatePhoneMask(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        //logger.info("end reading {}", value.toString() );
        if (value != null) {
            String property = value.toString();
            if (property.contains("#")) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                //  message.setSummary("Phone invalid");
                message.setDetail("Phone number must have all 10 digits with STD code without leading zero");
                // FacesContext context = FacesContext.getCurrentInstance();
                //  context.addMessage("Phone Error", message);
                throw new ValidatorException(message);
                //
            }
        }
    }

    // validateParticipantsMask
    public void validateParticipantsMask(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        //logger.info("end reading {}", value.toString() );
        if (value != null) {
            String property = value.toString();
            if (property.contains("#")) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                //  message.setSummary("Participant invalid");
                message.setDetail("Participant must be like 01 to 99 ");
                // FacesContext context = FacesContext.getCurrentInstance();
                //    context.addMessage("Participant Error", message);
                throw new ValidatorException(message);
                //
            }
        }
    }

    public void validateMobileMask(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        //logger.info("end reading {}", value.toString() );
        if (value != null) {
            String property = value.toString();
            if (property.contains("#")) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                //   message.setSummary("Phone invalid");
                message.setDetail("Phone number must have all 10 digits with STD code without leading zero");
                // FacesContext context = FacesContext.getCurrentInstance();
                //  context.addMessage("Phone Error", message);
                throw new ValidatorException(message);
                //
            }
        }
    }

    public void validateAgeMask(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        //logger.info("end reading {}", value.toString() );
        if (value != null) {
            String property = value.toString();
            if (property.contains("#")) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                //   message.setSummary("Age invalid");
                message.setDetail("Age is 2 digits numbers only");
                // FacesContext context = FacesContext.getCurrentInstance();
                //  context.addMessage("Age Error", message);
                throw new ValidatorException(message);
                // 
            }
        }
    }
    // validateAadhaar

    public void validateAadhaar(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        //logger.info("end reading {}", value.toString() );
        if (value != null) {
            String property = value.toString();
            if (property.contains("#")) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                // message.setSummary("Aaadhar invalid");
                message.setDetail("Aaadhar number must have all 12 digits ");
                // FacesContext context = FacesContext.getCurrentInstance();
                //   context.addMessage("Aaadhar Number Error", message);
                throw new ValidatorException(message);
                // 
            }
        }
    }

    public void validateAge(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        //logger.info("end reading {}", value.toString() );
        if (value != null) {
            String property = value.toString();
            if (property.contains("#")) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                //   message.setSummary("Age invalid");
                message.setDetail("Age number must have 2 digits ");
                // FacesContext context = FacesContext.getCurrentInstance();
                //   context.addMessage("Age Entry Error", message);
                throw new ValidatorException(message);
                // 
            }
        }
    }

    /**
     * @return the yearList
     */
    public Map<Integer, Integer> getYearList() {
        return yearList;
    }

    /**
     * @param yearList the yearList to set
     */
    public void setYearList(Map<Integer, Integer> yearList) {
        this.yearList = yearList;
    }

    /**
     * @return the monthList
     */
    public Map<String, Integer> getMonthList() {
        return monthList;
    }

    /**
     * @param monthList the monthList to set
     */
    public void setMonthList(Map<String, Integer> monthList) {
        this.monthList = monthList;
    }

    /**
     * @return the yearSelected
     */
    public Integer getYearSelected() {
        return yearSelected;
    }

    /**
     * @param yearSelected the yearSelected to set
     */
    public void setYearSelected(Integer yearSelected) {
        this.yearSelected = yearSelected;
    }

    /**
     * @return the monthSelected
     */
    public Integer getMonthSelected() {
        return monthSelected;
    }

    /**
     * @param monthSelected the monthSelected to set
     */
    public void setMonthSelected(Integer monthSelected) {
        this.monthSelected = monthSelected;
    }

    /**
     * @return the employeeValidatorMessage
     */
    public String getEmployeeValidatorMessage() {
        return employeeValidatorMessage;
    }

    /**
     * @param employeeValidatorMessage the employeeValidatorMessage to set
     */
    public void setEmployeeValidatorMessage(String employeeValidatorMessage) {
        this.employeeValidatorMessage = employeeValidatorMessage;
    }

    /**
     * @return the employeePattern
     */
    public String getEmployeePattern() {
        return employeePattern;
    }

    /**
     * @param employeePattern the employeePattern to set
     */
    public void setEmployeePattern(String employeePattern) {
        this.employeePattern = employeePattern;
    }

    /**
     * @return the attdState
     */
    public Map<String, Integer> getAttdState() {
        return attdState;
    }

    /**
     * @param attdState the attdState to set
     */
    public void setAttdState(Map<String, Integer> attdState) {
        this.attdState = attdState;
    }

    /**
     * @return the incomePattern
     */
    public String getIncomePattern() {
        return incomePattern;
    }

    /**
     * @param incomePattern the incomePattern to set
     */
    public void setIncomePattern(String incomePattern) {
        this.incomePattern = incomePattern;
    }

    /**
     * @return the incomeValidatorMessage
     */
    public String getIncomeValidatorMessage() {
        return incomeValidatorMessage;
    }

    /**
     * @return the surveyResult
     */
    public Map<String, String> getSurveyResult() {
        return surveyResult;
    }

    /**
     * @param surveyResult the surveyResult to set
     */
    public void setSurveyResult(Map<String, String> surveyResult) {
        this.surveyResult = surveyResult;
    }

    /**
     * @return the tz
     */
    public TimeZone getTz() {
        return tz;
    }

    /*
    public void auditWrite(ValueChangeEvent evt, String tableName, int uid) {
        auditEJB.getSelected().setTablename(tableName);
        if (null != evt.getOldValue()) {
            if (evt.getOldValue().toString().length() > 250) {
                auditEJB.getSelected().setPreviousValue(evt.getOldValue().toString().substring(0, 250));
            } else {
                auditEJB.getSelected().setPreviousValue(evt.getOldValue().toString());
            }
        } else {
            auditEJB.getSelected().setPreviousValue(" ");    // can't have null so make it empty string
        }
        if (null != evt.getNewValue() && evt.getNewValue().toString().length() > 0) {
            if (evt.getNewValue().toString().length() > 250) {
                auditEJB.getSelected().setUpdatedCurrent(evt.getNewValue().toString().substring(0, 250));
            } else {
                auditEJB.getSelected().setUpdatedCurrent(evt.getNewValue().toString());
            }
        } else {
            auditEJB.getSelected().setUpdatedCurrent(" ");
        }
        auditEJB.getSelected().setUpdateTimeStamp(date);
        auditEJB.getSelected().setUserID(uid);
        auditEJB.getSelected().setTrailType("E");
        auditEJB.create();
        //selected = null;
    }

    public void auditDeleteWrite(String tableName, int uid, String oldValue) {
        //logger.info(" In Audit delete write ");
        auditEJB.prepareCreate();
        auditEJB.getSelected().setFieldName("Row-Delete");
        auditEJB.getSelected().setUpdatedCurrent(" ");
        auditEJB.getSelected().setTablename(tableName);
        auditEJB.getSelected().setPreviousValue(oldValue);
        auditEJB.getSelected().setUpdateTimeStamp(date);
        auditEJB.getSelected().setUserID(uid);
        auditEJB.getSelected().setTrailType("D");
        auditEJB.create();
        //selected = null;
    }
     */
    /**
     * @return the buzzVersion
     */
    public String getHhhfVersion() {
        return hhhfVersion;
    }

    /**
     * @return the fileAudit
     */
    public Map<String, String> getFileAudit() {
        return fileAudit;
    }

    /**
     * @param fileAudit the fileAudit to set
     */
    public void setFileAudit(Map<String, String> fileAudit) {
        this.fileAudit = fileAudit;
    }

    /**
     * @return the surveyType
     */
    public Map<String, String> getSurveyType() {
        return surveyType;
    }

    /**
     * @param surveyType the surveyType to set
     */
    public void setSurveyType(Map<String, String> surveyType) {
        this.surveyType = surveyType;
    }

    /**
     * @return the scheduleStatus
     */
    public Map<String, String> getScheduleStatus() {
        return scheduleStatus;
    }

    /**
     * @param scheduleStatus the scheduleStatus to set
     */
    public void setScheduleStatus(Map<String, String> scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    /**
     * @return the checked
     */
    public Map<String, Boolean> getChecked() {
        return checked;
    }

    /**
     * @param checked the checked to set
     */
    public void setChecked(Map<String, Boolean> checked) {
        this.checked = checked;
    }

    /**
     * @return the traineeStatus
     */
    public Map<String, Integer> getTraineeStatus() {
        return traineeStatus;
    }

    /**
     * @param traineeStatus the traineeStatus to set
     */
    public void setTraineeStatus(Map<String, Integer> traineeStatus) {
        this.traineeStatus = traineeStatus;
    }

    /**
     * @return the ReasonForTraining
     */
    public Map<String, String> getReasonForTraining() {
        return reasonForTraining;
    }

    /**
     * @param ReasonForTraining the ReasonForTraining to set
     */
    public void setReasonForTraining(Map<String, String> reasonForTraining) {
        this.reasonForTraining = reasonForTraining;
    }

    /**
     * @return the rationCard
     */
    public Map<String, Character> getRationCard() {
        return rationCard;
    }

    /**
     * @param rationCard the rationCard to set
     */
    public void setRationCard(Map<String, Character> rationCard) {
        this.rationCard = rationCard;
    }

    /**
     * @return the maritalStatus
     */
    public Map<String, Character> getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * @param maritalStatus the maritalStatus to set
     */
    public void setMaritalStatus(Map<String, Character> maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * @return the socialCategory
     */
    public Map<String, Character> getSocialCategory() {
        return socialCategory;
    }

    /**
     * @param socialCategory the socialCategory to set
     */
    public void setSocialCategory(Map<String, Character> socialCategory) {
        this.socialCategory = socialCategory;
    }

    /**
     * @return the education
     */
    public Map<String, Character> getEducation() {
        return education;
    }

    /**
     * @param education the education to set
     */
    public void setEducation(Map<String, Character> education) {
        this.education = education;
    }

    /**
     * @return the buzzTrace
     */
    public Map<String, Character> getBuzzTrace() {
        return buzzTrace;
    }

    /**
     * @param buzzTrace the buzzTrace to set
     */
    public void setBuzzTrace(Map<String, Character> buzzTrace) {
        this.buzzTrace = buzzTrace;
    }

    /**
     * @return the trailStatus
     */
    public Map<String, String> getTrailStatus() {
        return trailStatus;
    }

    /**
     * @param trailStatus the trailStatus to set
     */
    public void setTrailStatus(Map<String, String> trailStatus) {
        this.trailStatus = trailStatus;
    }

    /**
     * @return the bankYesno
     */
    public Map<String, String> getBankYesno() {
        return bankYesno;
    }

    /**
     * @param bankYesno the bankYesno to set
     */
    public void setBankYesno(Map<String, String> bankYesno) {
        this.bankYesno = bankYesno;
    }

    /**
     * @return the familyBusiness
     */
    public Map<String, String> getFamilyBusiness() {
        return familyBusiness;
    }

    /**
     * @param familyBusiness the familyBusiness to set
     */
    public void setFamilyBusiness(Map<String, String> familyBusiness) {
        this.familyBusiness = familyBusiness;
    }

    /**
     * @return the startedBusiness
     */
    public Map<String, String> getStartedBusiness() {
        return startedBusiness;
    }

    /**
     * @param startedBusiness the startedBusiness to set
     */
    public void setStartedBusiness(Map<String, String> startedBusiness) {
        this.startedBusiness = startedBusiness;
    }

    /**
     * @return the amountPattern
     */
    public String getAmountPattern() {
        return amountPattern;
    }

    /**
     * @param amountPattern the amountPattern to set
     */
    public void setAmountPattern(String amountPattern) {
        this.amountPattern = amountPattern;
    }

    /**
     * @return the amountValidatorMessage
     */
    public String getAmountValidatorMessage() {
        return amountValidatorMessage;
    }

    /**
     * @param amountValidatorMessage the amountValidatorMessage to set
     */
    public void setAmountValidatorMessage(String amountValidatorMessage) {
        this.amountValidatorMessage = amountValidatorMessage;
    }

    /**
     * @return the indianStatesList
     */
    public List<String> getIndianStatesList() {
        return indianStatesList;
    }

    /**
     * @param indianStatesList the indianStatesList to set
     */
    public void setIndianStatesList(List<String> indianStatesList) {
        this.indianStatesList = indianStatesList;
    }

    /**
     * @return the loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName the loginName to set
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return the educationValidatorMessage
     */
    public String getEducationValidatorMessage() {
        return educationValidatorMessage;
    }

    /**
     * @param educationValidatorMessage the educationValidatorMessage to set
     */
    public void setEducationValidatorMessage(String educationValidatorMessage) {
        this.educationValidatorMessage = educationValidatorMessage;
    }

    /**
     * @return the monthReportList
     */
    public Map<String, String> getMonthReportList() {
        return monthReportList;
    }

    /**
     * @param monthReportList the monthReportList to set
     */
    public void setMonthReportList(Map<String, String> monthReportList) {
        this.monthReportList = monthReportList;
    }

    public void idleDialog() throws IOException {
        throwMessage("The screen is idle and timed out. Please login to continue ");
        // FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
        FacesContext.getCurrentInstance().responseComplete();
        // externalContext("foo.xhtml");
    }

    private void throwMessage(String msg) {
        // logger.info(" {}" + msg, userEJB.getLoginName());
        FacesMessage message = new FacesMessage(msg);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, message);  // show global message
        context.validationFailed();
    }

    /**
     * @return the lastActivity
     */
    public Date getLastActivity() {
        return lastActivity;
    }

    /**
     * @param lastActivity the lastActivity to set
     */
    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }

    public String getBrowserName() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String userAgent = externalContext.getRequestHeaderMap().get("User-Agent");
        /*
        if (userAgent.contains("MSIE")) {
            return "Internet Explorer";
        }
        if (userAgent.contains("Firefox")) {
            return "Firefox";
        }
        if (userAgent.contains("Chrome")) {
            return "Chrome";
        }
         if (userAgent.contains("Chromium")) {
            return "Chromium";
        }
        if (userAgent.contains("Edge")) {
            return "Microsoft Edge";
        } 
        if (userAgent.contains("Opera")) {
            return "Opera";
        }
        if (userAgent.contains("Safari")) {
            return "Safari";
        }
         */
        return "Unknown " + userAgent;
    }

    /**
     * Returns the clientId for a component with id="foo"
     */
    public String getClientId(String name) {
        FacesContext context = FacesContext.getCurrentInstance();
        UIViewRoot root = context.getViewRoot();

        final String componentId = name;
        UIComponent c = findComponent(root, componentId);
        if (null != c) {
            return c.getClientId(context);
        } else {
            return null;
        }
    }

    /**
     * Finds component with the given id
     */
    private UIComponent findComponent(UIComponent c, String id) {
        if (id.equals(c.getId())) {
            return c;
        }
        Iterator<UIComponent> kids = c.getFacetsAndChildren();
        while (kids.hasNext()) {
            UIComponent found = findComponent(kids.next(), id);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    /**
     * @return the ngoType
     */
    public Map<String, String> getNgoType() {
        return ngoType;
    }

    /**
     * @param ngoType the ngoType to set
     */
    public void setNgoType(Map<String, String> ngoType) {
        this.ngoType = ngoType;
    }

    /**
     * @return the yesNo
     */
    public Map<String, String> getYesNo() {
        return yesNo;
    }

    /**
     * @param yesNo the yesNo to set
     */
    public void setYesNo(Map<String, String> yesNo) {
        this.yesNo = yesNo;
    }

    /**
     * @return the partnerStatus
     */
    public Map<String, String> getPartnerStatus() {
        return partnerStatus;
    }

    /**
     * @param partnerStatus the partnerStatus to set
     */
    public void setPartnerStatus(Map<String, String> partnerStatus) {
        this.partnerStatus = partnerStatus;
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
     * @return the entryDate
     */
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * @param entryDate the entryDate to set
     */
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * @return the infraCheckList
     */
    public Map<String, Integer> getInfraCheckList() {
        return infraCheckList;
    }

    /**
     * @return the trainerPosition
     */
    public Map<String, String> getTrainerPosition() {
        return trainerPosition;
    }

    /**
     * @param trainerPosition the trainerPosition to set
     */
    public void setTrainerPosition(Map<String, String> trainerPosition) {
        this.trainerPosition = trainerPosition;
    }

    /**
     * @return the gender
     */
    public Map<String, String> getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Map<String, String> gender) {
        this.gender = gender;
    }

    /**
     * @return the levelTrainer
     */
    public Map<String, String> getLevelTrainer() {
        return levelTrainer;
    }

    /**
     * @param levelTrainer the levelTrainer to set
     */
    public void setLevelTrainer(Map<String, String> levelTrainer) {
        this.levelTrainer = levelTrainer;
    }

    /**
     * @return the companyType
     */
    public Map<String, String> getCompanyType() {
        return companyType;
    }

    /**
     * @param companyType the companyType to set
     */
    public void setCompanyType(Map<String, String> companyType) {
        this.companyType = companyType;
    }

    /**
     * @return the siteEvaluationCheckList
     */
    public Map<String, Integer> getSiteEvaluationCheckList() {
        return siteEvaluationCheckList;
    }

    /**
     * @param siteEvaluationCheckList the siteEvaluationCheckList to set
     */
    public void setSiteEvaluationCheckList(Map<String, Integer> siteEvaluationCheckList) {
        this.siteEvaluationCheckList = siteEvaluationCheckList;
    }

    /**
     * @return the sourceType
     */
    public Map<String, String> getSourceType() {
        return sourceType;
    }

    /**
     * @param sourceType the sourceType to set
     */
    public void setSourceType(Map<String, String> sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * @return the trainerStatus
     */
    public Map<String, String> getTrainerStatus() {
        return trainerStatus;
    }

    /**
     * @param trainerStatus the trainerStatus to set
     */
    public void setTrainerStatus(Map<String, String> trainerStatus) {
        this.trainerStatus = trainerStatus;
    }

    /**
     * @return the ivwStatus
     */
    public Map<String, String> getIvwStatus() {
        return ivwStatus;
    }

    /**
     * @param ivwStatus the ivwStatus to set
     */
    public void setIvwStatus(Map<String, String> ivwStatus) {
        this.ivwStatus = ivwStatus;
    }

    /**
     * @return the fellowType
     */
    public Map<String, String> getFellowType() {
        return fellowType;
    }

    /**
     * @return the ivwMode
     */
    public Map<String, String> getIvwMode() {
        return ivwMode;
    }

    /**
     * @param ivwMode the ivwMode to set
     */
    public void setIvwMode(Map<String, String> ivwMode) {
        this.ivwMode = ivwMode;
    }

    /**
     * @return the bloodGroup
     */
    public Map<String, String> getBloodGroup() {
        return bloodGroup;
    }

    /**
     * @param bloodGroup the bloodGroup to set
     */
    public void setBloodGroup(Map<String, String> bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    /**
     * @return the uniqueID
     */
    public Map<String, String> getUniqueID() {
        return uniqueID;
    }

    /**
     * @param uniqueID the uniqueID to set
     */
    public void setUniqueID(Map<String, String> uniqueID) {
        this.uniqueID = uniqueID;
    }

    /**
     * @return the eduStatus
     */
    public Map<String, String> getEduStatus() {
        return eduStatus;
    }

    /**
     * @param eduStatus the eduStatus to set
     */
    public void setEduStatus(Map<String, String> eduStatus) {
        this.eduStatus = eduStatus;
    }

    /**
     * @return the approvalStatus
     */
    public Map<String, String> getApprovalStatus() {
        return approvalStatus;
    }

    /**
     * @param approvalStatus the approvalStatus to set
     */
    public void setApprovalStatus(Map<String, String> approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

}
