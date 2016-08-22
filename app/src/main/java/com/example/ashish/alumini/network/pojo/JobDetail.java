package com.example.ashish.alumini.network.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashish on 22/8/16.
 */
public class JobDetail {

    private String kahani;
    private String contactweb;
    private String contactemail;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The kahani
     */
    public String getKahani() {
        return kahani;
    }

    /**
     *
     * @param kahani
     * The kahani
     */
    public void setKahani(String kahani) {
        this.kahani = kahani;
    }

    /**
     *
     * @return
     * The contactweb
     */
    public String getContactweb() {
        return contactweb;
    }

    /**
     *
     * @param contactweb
     * The contactweb
     */
    public void setContactweb(String contactweb) {
        this.contactweb = contactweb;
    }

    /**
     *
     * @return
     * The contactemail
     */
    public String getContactemail() {
        return contactemail;
    }

    /**
     *
     * @param contactemail
     * The contactemail
     */
    public void setContactemail(String contactemail) {
        this.contactemail = contactemail;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
