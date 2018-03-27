package edu.sdsc.awesome.awesomeWeb.store;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller    // This means that this class is a Controller
@RequestMapping(path = "/demo")


public class AnalyticalJobsController {
    @Autowired
    private AnalyticalJobsRepository analyticalJobsRepository;

    @GetMapping(path = "/addAnalyticalJob")
    public @ResponseBody
    String addanalyticalJobsRepository(@RequestParam Integer id
            , @RequestParam String jobName, @RequestParam String jobType, @RequestParam String jobId, @RequestParam String executionType, @RequestParam String executionPlatform,
                                       @RequestParam String orgQuery, @RequestParam String outputlocation,
                                       @RequestParam String outputcode, @RequestParam boolean ipStatus, @RequestParam boolean outputStatus,
                                       @RequestParam boolean writeStatus, @RequestParam String inputCode, @RequestParam String remoteStatusCode) {


        AnalyticalJobs anj = new AnalyticalJobs();
        anj.setId(id);
        anj.setJobName(jobName);
        anj.setJobId(jobId);
        anj.setJobType(jobType);
        anj.setExecutionType(executionType);
        anj.setExecutionPlatform(executionPlatform);
        anj.setOrgQuery(orgQuery);
        anj.setOutputlocation(outputlocation);
        anj.setOutputcode(outputcode);
        anj.setIpStatus(ipStatus);
        anj.setOutputStatus(outputStatus);
        anj.setWriteStatus(writeStatus);
        anj.setInputCode(inputCode);
        anj.setRemoteStatusCode(remoteStatusCode);


        analyticalJobsRepository.save(anj);
        return "Saved";
    }

}
