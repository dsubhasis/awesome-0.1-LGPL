package edu.sdsc.awesome.awesomeWeb.store;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class Dictionary {

    @javax.persistence.Id
    @GeneratedValue
    private String Id;
    private String lName;
    private String lPath;
    private String lFnction;
    private String[] lInputproperyMap;
    private String[] lOutputPropertyMap;




}