package edu.sdsc.awesome.awesomeWeb.store;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class GraphProperty {

    @Id
    @GeneratedValue
    private String Id;
    private String graphId;
    private String nodeId;
    private String[] propertiname;
    private String[] propertiType;



}
