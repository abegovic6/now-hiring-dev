package ba.unsa.etf.pnwt.jobsservice.entity;

import ba.unsa.etf.pnwt.jobsservice.constants.DatabaseConstants;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Example entity
 */
@Entity
@Table(schema = DatabaseConstants.JOBS_SERVICE_SCHEMA, name = "Job2Application" )
public class Job2ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "applicationid")
    private Integer applicationId;

    @Column(name = "jobid")
    private Integer jobId;

    public Job2ApplicationEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public static List<Integer> getJobIdsForAppIds(List<Job2ApplicationEntity> j2aList, List<Integer> appIds){
        List<Integer> jobIds = new ArrayList<>();
        for(int i = 0; i < appIds.size(); i++){
            for (int j=0; j<j2aList.size(); j++){
                if(j2aList.get(j).getApplicationId() == appIds.get(i)) jobIds.add(j2aList.get(j).getJobId());
            }
        }
        return jobIds;
    }
}
