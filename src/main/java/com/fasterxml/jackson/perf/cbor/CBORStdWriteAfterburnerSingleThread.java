package com.fasterxml.jackson.perf.cbor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;
import org.appenders.st.jackson.SingleThreadCBORFactory;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class CBORStdWriteAfterburnerSingleThread
    extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER;
    static {
        CBORFactory f = new SingleThreadCBORFactory();
        // configure differently?
        MAPPER = new ObjectMapper(f);
        MAPPER.registerModule(new AfterburnerModule());
    }

    public CBORStdWriteAfterburnerSingleThread() {
        super(MAPPER);
    }
}
