package edu.sdsc.awesome.awesomeWeb.store;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class DataSource {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;
    private String name;
    private String store;
    private String link;
    private String sourceId;
    private String accessFunction;


}
