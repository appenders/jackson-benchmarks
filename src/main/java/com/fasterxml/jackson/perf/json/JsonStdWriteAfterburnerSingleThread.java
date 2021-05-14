package com.fasterxml.jackson.perf.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;
import org.appenders.st.jackson.SingleThreadJsonFactory;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
@Fork(jvmArgsAppend = {
        "-ea",
        "-Xmx20g",
        "-Xms20g",
        "-XX:+AlwaysPreTouch",
        "-Dio.netty.allocator.maxOrder=7",
        "-Djmh.pinned=true"
//        ,
//        "-XX:+UnlockExperimentalVMOptions",
//        "-XX:+UseZGC"
})
public class JsonStdWriteAfterburnerSingleThread extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = new ObjectMapper(new SingleThreadJsonFactory());
    static {
        MAPPER.registerModule(new AfterburnerModule());
    }

	public JsonStdWriteAfterburnerSingleThread() {
		super(MAPPER);
	}
}
