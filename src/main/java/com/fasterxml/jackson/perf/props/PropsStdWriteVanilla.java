package com.fasterxml.jackson.perf.props;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.perf.WritePerfBaseFullJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class PropsStdWriteVanilla
    extends WritePerfBaseFullJackson<MediaItem>
{
    private static final JavaPropsMapper PROPS_MAPPER = new JavaPropsMapper();

    public PropsStdWriteVanilla() {
        super(PROPS_MAPPER);
    }
}
