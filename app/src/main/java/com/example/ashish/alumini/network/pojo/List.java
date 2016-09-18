package com.example.ashish.alumini.network.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashish on 17/9/16.
 */

public class List {

    private String _id;
    private String name;
    private Boolean isNerd;
    private String year;
    private String work;
    private String designation;
    private Integer time;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The _id
     */
    public String get_id() {
        return _id;
    }

    /**
     *
     * @param _id
     *     The _id
     */
    public void set_id(String _id) {
        this._id = _id;
    }

    /**
     *
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The isNerd
     */
    public Boolean getIsNerd() {
        return isNerd;
    }

    /**
     *
     * @param isNerd
     *     The isNerd
     */
    public void setIsNerd(Boolean isNerd) {
        this.isNerd = isNerd;
    }

    /**
     *
     * @return
     *     The year
     */
    public String getYear() {
        return year;
    }

    /**
     *
     * @param year
     *     The year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     *
     * @return
     *     The work
     */
    public String getWork() {
        return work;
    }

    /**
     *
     * @param work
     *     The work
     */
    public void setWork(String work) {
        this.work = work;
    }

    /**
     *
     * @return
     *     The designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     *
     * @param designation
     *     The designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     *
     * @return
     *     The time
     */
    public Integer getTime() {
        return time;
    }

    /**
     *
     * @param time
     *     The time
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}