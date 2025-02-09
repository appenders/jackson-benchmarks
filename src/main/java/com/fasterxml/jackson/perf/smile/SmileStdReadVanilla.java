package com.fasterxml.jackson.perf.smile;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.*;

import tools.jackson.dataformat.smile.SmileFactory;
import tools.jackson.dataformat.smile.databind.SmileMapper;

import com.fasterxml.jackson.perf.ReadPerfBaseFullJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class SmileStdReadVanilla
    extends ReadPerfBaseFullJackson<MediaItem>
{
    private final static SmileFactory _sf = SmileFactory.builder()
//            .disable(com.fasterxml.jackson.core.TokenStreamFactory.Feature.CANONICALIZE_PROPERTY_NAMES)
            .build();

    private static final ObjectMapper MAPPER = new SmileMapper(_sf);

    private final static InputConverter SMILES = InputConverter.stdConverter(MAPPER);

    public SmileStdReadVanilla() {
        super(MediaItem.class, SMILES, MAPPER);
    }
}
