package com.fasterxml.jackson.manualtest;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ObjectWriter;

import tools.jackson.dataformat.protobuf.ProtobufFactory;
import tools.jackson.dataformat.protobuf.ProtobufMapper;
import tools.jackson.dataformat.protobuf.schema.ProtobufSchema;

import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;
import com.fasterxml.jackson.perf.protob.ProtobufHelper;

public class MediaItemWriteProtobuf
    extends ObjectWriterTestBase<MediaItem, MediaItem>
{
    private final static ProtobufSchema SCHEMA = ProtobufHelper.mediaItemSchema();
    
    @Override
    protected int targetSizeMegs() { return 20; }

    public static void main(String[] args) throws Exception
    {
//        final boolean USE_AFTERBURNER = true;
        final boolean USE_AFTERBURNER = false;

        if (args.length != 0) {
            System.err.println("Usage: java ...");
            System.exit(1);
        }
        String desc = "Protobuf";
        MediaItem input = MediaItems.stdMediaItem();
        ObjectMapper m = _mapper(ProtobufMapper.builder(new ProtobufFactory()), USE_AFTERBURNER);
        if (USE_AFTERBURNER) {
            desc += "+Afterburner";
        }
        try {
            new MediaItemWriteProtobuf().test(m,
            		desc+"#1", input, MediaItem.class,
            		desc+"#2", input, MediaItem.class);
        } catch (Exception e) {
            System.err.println("Fail!");
            Throwable t = e;
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    protected ObjectWriter withSchema(ObjectWriter w) {
        return w.with(SCHEMA);
    }
}
