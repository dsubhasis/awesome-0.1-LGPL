/*
 * Copyright (c) 2019.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 *
 */

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
