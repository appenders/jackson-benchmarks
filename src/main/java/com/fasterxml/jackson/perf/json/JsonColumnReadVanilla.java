package com.fasterxml.jackson.perf.json;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.*;
import tools.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.util.AsArrayIntrospector;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class JsonColumnReadVanilla
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = JsonMapper.builder()
            .annotationIntrospector(new AsArrayIntrospector())
            .build();

    // pass non-null ObjectMapper: will remove whitespace, if any
    private final static InputConverter NO_OP = InputConverter.stdConverter(MAPPER);

    public JsonColumnReadVanilla() {
        super(MediaItem.class, NO_OP, MAPPER);
    }
}
