package com.fasterxml.jackson.perf;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import tools.jackson.core.FormatSchema;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ObjectReader;
import tools.jackson.databind.cfg.MapperBuilder;
import tools.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.data.MinimalInputConverter;

public abstract class ReadPerfBaseBasicJackson<T>
	implements ReadPerfTestBasic
{
    protected final MinimalInputConverter MINIMAL_CONV;

    protected final ObjectReader MEDIA_ITEM_READER;

    protected ReadPerfBaseBasicJackson(Class<T> type,
            MinimalInputConverter conv, ObjectMapper mapper) {
        this(type, conv, mapper, null);
    }

    protected ReadPerfBaseBasicJackson(Class<T> type,
            MinimalInputConverter conv, ObjectMapper mapper, FormatSchema schema)
    {
        MINIMAL_CONV = conv;
        ObjectReader r = mapper.readerFor(type);
        if (schema != null) {
            r = r.with(schema);
        }
        MEDIA_ITEM_READER = r;
    }

    /*
    /**********************************************************************
    /* Typed reading tests
    /**********************************************************************
     */

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readPojoMediaItem(Blackhole bh/*, AuxStateSize size*/)
        throws Exception
    {
        final byte[] input = MINIMAL_CONV.mediaItemAsBytes();
//        size.set(input.length);
        bh.consume(read(input, MEDIA_ITEM_READER));
    }

    /*
    /**********************************************************************
    /* Helper methods
    /**********************************************************************
     */

    protected Object read(byte[] input, ObjectReader reader) throws IOException {
        return reader.readValue(input);
    }

    protected static MapperBuilder<?,?> _withAfterburner(MapperBuilder<?,?> b) {
        return b.addModule(new AfterburnerModule());
    }
}
