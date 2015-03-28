package com.fasterxml.jackson.perf;

import org.openjdk.jmh.logic.BlackHole;

public interface ReadPerfTestFull extends ReadPerfTestBasic
{
    public void readTreeCitmCatalog(BlackHole bh) throws Exception;
    public void readTreeWebxml(BlackHole bh) throws Exception;
    public void readTreeMenu(BlackHole bh) throws Exception;
    public void readTreeMediaItem(BlackHole bh) throws Exception;

    public void readNodeCitmCatalog(BlackHole bh) throws Exception;
    public void readNodeWebxml(BlackHole bh) throws Exception;
    public void readNodeMenu(BlackHole bh) throws Exception;
    public void readNodeMediaItem(BlackHole bh) throws Exception;
}
