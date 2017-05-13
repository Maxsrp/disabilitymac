package com.marktrs.macapp.Model;

/**
 * Created by Mark on 5/12/2017.
 */

public class JobApplication {
    private String id;
    private String jobId;
    private String ownerId;
    private String workerId;
    private String status;
    private String jobName;
    private String jobLocation;
    private String jobSymptom;

    public JobApplication() {
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobSymptom() {
        return jobSymptom;
    }

    public void setJobSymptom(String jobSymptom) {
        this.jobSymptom = jobSymptom;
    }
}
