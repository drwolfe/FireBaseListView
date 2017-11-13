package com.wolfedendevs.firebaselistview.m_Model;

/**
 * Created by Douglas on 11/13/2017.
 */

public class Spacecraft {
    String name,propellant,description;
    public Spacecraft() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPropellant() {
        return propellant;
    }
    public void setPropellant(String propellant) {
        this.propellant = propellant;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}