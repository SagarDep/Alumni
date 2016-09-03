package com.example.ashish.alumini.network.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashish on 3/9/16.
 */

public class MemberInstance {

    private String _id;
    private String name;
    private String year;
    private String designation;
    private String company;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The _id
     */
    public String get_id() {
        return _id;
    }

    /**
     *
     * @param _id
     * The _id
     */
    public void set_id(String _id) {
        this._id = _id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The year
     */
    public String getYear() {
        return year;
    }

    /**
     *
     * @param year
     * The year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     *
     * @return
     * The designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     *
     * @param designation
     * The designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     *
     * @return
     * The company
     */
    public String getCompany() {
        return company;
    }

    /**
     *
     * @param company
     * The company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}


