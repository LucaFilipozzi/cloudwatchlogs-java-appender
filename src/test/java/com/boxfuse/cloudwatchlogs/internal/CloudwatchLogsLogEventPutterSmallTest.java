package com.boxfuse.cloudwatchlogs.internal;

import com.boxfuse.cloudwatchlogs.CloudwatchLogsConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingQueue;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class CloudwatchLogsLogEventPutterSmallTest {
    @Test
    public void toJson() throws JsonProcessingException {
        Map<String, Object> in = new TreeMap<>();
        in.put("abc", "123");
        in.put("def", null);
        in.put("ghi", 456);

        String json = new CloudwatchLogsLogEventPutter(new CloudwatchLogsConfig(), new LinkedBlockingQueue<CloudwatchLogsLogEvent>()).toJson(in);
        assertThat(json, containsString("abc"));
        assertThat(json, containsString("123"));
        assertThat(json, not(containsString("def")));
        assertThat(json, not(containsString("null")));
        assertThat(json, containsString("ghi"));
        assertThat(json, containsString("456"));
    }
}
