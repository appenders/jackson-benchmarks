package com.fasterxml.jackson.perf.protobuf;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.protobuf.ProtobufFactory;
import com.fasterxml.jackson.dataformat.protobuf.schema.ProtobufSchema;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Group) // Thread, Group or Benchmark
public class ProtobufStdWriteAfterburner
    extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = new ObjectMapper(new ProtobufFactory());
    static {
        MAPPER.registerModule(new AfterburnerModule());
    }

    private final static ProtobufSchema _mediaItemSchema = ProtobufHelper.mediaItemSchema();

    public ProtobufStdWriteAfterburner() {
        super(MAPPER, _mediaItemSchema);
    }
}
