package edu.sdsc.awesome.awesomeWeb.store;

import javax.json.Json;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Schema {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Double Id;
    private String SchemaName;
    private String SchemaType;
    private String Store;
    private String Structure;

    private Integer type;
    private Double typeId;
    private String index;



}