package edu.sdsc.awesome.awesomeWeb.store;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class

public class DataSet {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String DatasetName;
    private String DataSourceName;
    private Double SchemaId;
    private Integer UpdateFreq;
    private Date StartDate;
    private Date LastDate;
    private Double OwnerId;

    public String getId() {
        return id;
    }

    public String getDatasetName() {
        return DatasetName;
    }

    public String getDataSourceName() {
        return DataSourceName;
    }

    public Double getSchemaId() {
        return SchemaId;
    }

    public Integer getUpdateFreq() {
        return UpdateFreq;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public Date getLastDate() {
        return LastDate;
    }

    public Double getOwnerId() {
        return OwnerId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDatasetName(String datasetName) {
        DatasetName = datasetName;
    }

    public void setDataSourceName(String dataSourceName) {
        DataSourceName = dataSourceName;
    }

    public void setSchemaId(Double schemaId) {
        SchemaId = schemaId;
    }

    public void setUpdateFreq(Integer updateFreq) {
        UpdateFreq = updateFreq;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public void setLastDate(Date lastDate) {
        LastDate = lastDate;
    }

    public void setOwnerId(Double ownerId) {
        OwnerId = ownerId;
    }
}
