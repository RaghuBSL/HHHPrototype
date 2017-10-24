/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author S Ragothaman
 */

@Named("navigationBean")
@SessionScoped
public class NavigationBean implements Serializable {
    
    static Logger logger = LoggerFactory.getLogger(NavigationBean.class);
    private String page="";
    private Boolean good = false;
    public String getPage(){
        return page;
    }
    public void setPage(String currentPage) {
        
        good = true;
        this.page=currentPage;
        System.out.println(" Navi page is " + page );
        logger.info(" Navi page is {}", page);
    }

    /**
     * @return the good
     */
    public Boolean getGood() {
        return good;
    }

    /**
     * @param good the good to set
     */
    public void setGood(Boolean good) {
        this.good = good;
    }
}
