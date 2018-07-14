package edu.sdsc.awesome.neo4j.awsmneo4jclient;

import com.sun.management.OperatingSystemMXBean;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;




public class GetCount {

    private Long totlaM;
    private Integer avlprocessor;
    private Double systeload;
    private Long swapFree;
    private Long phyFreeMem;
    private Double cpuLoadProc;
    private Double cpuLoadSystem;
    private Long totalDisk;
    private Long freeDisk;


    public JsonObject systemPerf(){
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(
                OperatingSystemMXBean.class);

        String prefix = "awsm";
        String suffix = ".tmp";

        // this temporary file remains after the jvm exits
        try {
            File tempFile = File.createTempFile(prefix, suffix);
            freeDisk = tempFile.getUsableSpace();
            totalDisk = tempFile.getTotalSpace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        JsonObjectBuilder js = Json.createObjectBuilder();
         totlaM = osBean.getTotalPhysicalMemorySize();
         js.add("cpu", totlaM);
         avlprocessor = osBean.getAvailableProcessors();
         js.add("proc", avlprocessor);
         systeload = osBean.getSystemLoadAverage();
         js.add("system", systeload);
         swapFree = osBean.getFreeSwapSpaceSize();
        js.add("swap", swapFree);
         phyFreeMem =  osBean.getFreePhysicalMemorySize();
        js.add("freeM", phyFreeMem);
         cpuLoadProc = osBean.getProcessCpuLoad();
        js.add("cpuUser", cpuLoadProc);
         cpuLoadSystem = osBean.getSystemCpuLoad();
        js.add("cpuSys", cpuLoadSystem);
        js.add("freeDisk", freeDisk);
        js.add("totalDisk", totalDisk);

        JsonObject jsb = js.build();
        return jsb;

    }
}
