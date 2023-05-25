package com.fasterxml.jackson.perf.cbor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.perf.WritePerfBaseFullJackson;
import com.fasterxml.jackson.perf.model.MediaItem;
import org.appenders.st.jackson.SingleThreadCBORFactory;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class CBORStdWriteVanillaSingleThread
    extends WritePerfBaseFullJackson<MediaItem>
{
    private static final ObjectMapper CBOR_MAPPER;
    static {
        // configure differently?
        CBOR_MAPPER = new ObjectMapper(new SingleThreadCBORFactory());
    }

    public CBORStdWriteVanillaSingleThread() {
        super(CBOR_MAPPER);
    }
}
