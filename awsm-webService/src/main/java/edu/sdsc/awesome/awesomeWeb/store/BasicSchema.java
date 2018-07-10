package edu.sdsc.awesome.awesomeWeb.store;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BasicSchema {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private String id;
  private String SchemaName;
  private String Store;
  private String type;
  private String owner;
  private String schemaElements;
  private String schemaType;
}
