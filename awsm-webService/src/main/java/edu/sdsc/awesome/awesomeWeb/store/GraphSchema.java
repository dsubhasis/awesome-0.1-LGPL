package edu.sdsc.awesome.awesomeWeb.store;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Array;

@Entity

public class GraphSchema {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    String Id;
    String schemaId;
    Array[] nodeID;
    Long graphPropertyId;


}
