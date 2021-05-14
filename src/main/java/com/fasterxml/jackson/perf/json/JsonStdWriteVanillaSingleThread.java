package com.fasterxml.jackson.perf.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.perf.WritePerfBaseFullJackson;
import com.fasterxml.jackson.perf.model.MediaItem;
import org.appenders.st.jackson.SingleThreadJsonFactory;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class JsonStdWriteVanillaSingleThread extends WritePerfBaseFullJackson<MediaItem>
{
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper(new SingleThreadJsonFactory());

    public JsonStdWriteVanillaSingleThread() {
        super(JSON_MAPPER);
    }
}
