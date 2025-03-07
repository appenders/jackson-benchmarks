package com.fasterxml.jackson.perf.props;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.dataformat.javaprop.JavaPropsMapper;
import tools.jackson.dataformat.javaprop.JavaPropsSchema;
import com.fasterxml.jackson.perf.ReadPerfBaseFullJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class PropsStdReadVanilla
    extends ReadPerfBaseFullJackson<MediaItem>
{
    private static final JavaPropsMapper MAPPER = new JavaPropsMapper();
    static final JavaPropsSchema SCHEMA = JavaPropsSchema.emptySchema()
            // for bit of performance boost may disable index marker handling:
//            .withoutIndexMarker();
    ;

    private final static InputConverter PROPS = InputConverter.stdConverter(MAPPER);

    public PropsStdReadVanilla() {
        super(MediaItem.class, PROPS, MAPPER, SCHEMA);
    }
}
