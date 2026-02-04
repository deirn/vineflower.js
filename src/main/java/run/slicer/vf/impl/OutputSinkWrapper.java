package run.slicer.vf.impl;

import org.jetbrains.java.decompiler.main.extern.IContextSource;
import run.slicer.vf.Options;

public class OutputSinkWrapper implements IContextSource.IOutputSink {
    private final Options.OutputSink outputSink;

    public OutputSinkWrapper(Options.OutputSink outputSink) {
        this.outputSink = outputSink;
    }

    @Override
    public void begin() {
        outputSink.begin().await();
    }

    @Override
    public void acceptClass(String qualifiedName, String fileName, String content, int[] mapping) {
        outputSink.acceptClass(qualifiedName, fileName, content, mapping).await();
    }

    @Override
    public void acceptDirectory(String directory) {
        outputSink.acceptDirectory(directory).await();
    }

    @Override
    public void acceptOther(String path) {
        outputSink.acceptOther(path).await();
    }

    @Override
    public void close() {
        outputSink.close().await();
    }
}
