package io.github.fact;

import io.github.fact.providers.AWSFact;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class AWSFactTest extends TestCase {

    @Test
    public void testReadCgroupIDs(){
        Trace.Builder trace = Trace.newBuilder();
        String path = AWSFactTest.class.getResource("/aws_cgroup.txt").getPath();

        long start = System.nanoTime();
        try {
            AWSFact.getInstances(path).readCgroupIDs(trace);
        } catch (IOException ignore) {}
        long durationInNs = System.nanoTime()-start;
        System.out.printf("processing took %d ns\n",durationInNs);
        Map<String, String> maps = trace.getTagsMap();
        assertEquals(maps.get("service"),"9857cb");
        assertEquals(maps.get("sandbox"),"36b98a");
        assertEquals(trace.getHostID(),"siNKWg");
    }

}