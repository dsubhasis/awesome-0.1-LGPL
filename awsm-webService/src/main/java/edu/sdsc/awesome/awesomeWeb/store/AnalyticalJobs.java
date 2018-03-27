package edu.sdsc.awesome.awesomeWeb.store;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class AnalyticalJobs {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String jobName;
    private String jobType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getExecutionType() {
        return executionType;
    }

    public void setExecutionType(String executionType) {
        this.executionType = executionType;
    }

    public String getExecutionPlatform() {
        return executionPlatform;
    }

    public void setExecutionPlatform(String executionPlatform) {
        this.executionPlatform = executionPlatform;
    }

    public String getOrgQuery() {
        return orgQuery;
    }

    public void setOrgQuery(String orgQuery) {
        this.orgQuery = orgQuery;
    }

    public String getOutputlocation() {
        return outputlocation;
    }

    public void setOutputlocation(String outputlocation) {
        this.outputlocation = outputlocation;
    }

    public String getOutputcode() {
        return outputcode;
    }

    public void setOutputcode(String outputcode) {
        this.outputcode = outputcode;
    }

    public boolean isIpStatus() {
        return ipStatus;
    }

    public void setIpStatus(boolean ipStatus) {
        this.ipStatus = ipStatus;
    }

    public boolean isOutputStatus() {
        return outputStatus;
    }

    public void setOutputStatus(boolean outputStatus) {
        this.outputStatus = outputStatus;
    }

    public boolean isWriteStatus() {
        return writeStatus;
    }

    public void setWriteStatus(boolean writeStatus) {
        this.writeStatus = writeStatus;
    }

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public String getRemoteStatusCode() {
        return remoteStatusCode;
    }

    public void setRemoteStatusCode(String remoteStatusCode) {
        this.remoteStatusCode = remoteStatusCode;
    }


    private String jobId;
    private String executionType;
    private String executionPlatform;
    private String orgQuery;
    private String outputlocation;
    private String outputcode;
    private boolean ipStatus;
    private boolean outputStatus;
    private boolean writeStatus;
    private String inputCode;
    private String remoteStatusCode;



}
