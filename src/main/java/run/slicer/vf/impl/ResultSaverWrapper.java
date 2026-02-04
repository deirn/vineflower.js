package run.slicer.vf.impl;

import org.jetbrains.java.decompiler.main.extern.IResultSaver;
import run.slicer.vf.Options;

import java.util.jar.Manifest;

public class ResultSaverWrapper implements IResultSaver {
    private final Options.ResultSaver resultSaver;

    public ResultSaverWrapper(Options.ResultSaver resultSaver) {
        this.resultSaver = resultSaver;
    }

    @Override
    public void saveFolder(String path) {
//        resultSaver.saveFolder(path).await();
    }

    @Override
    public void copyFile(String source, String path, String entryName) {
//        resultSaver.copyFile(source, path, entryName).await();
    }

    @Override
    public void saveClassFile(String path, String qualifiedName, String entryName, String content, int[] mapping) {
//        resultSaver.saveClassFile(path, qualifiedName, entryName, content, mapping).await();
    }

    @Override
    public void createArchive(String path, String archiveName, Manifest manifest) {
//        resultSaver.createArchive(path, archiveName, manifest).await();
    }

    @Override
    public void saveDirEntry(String path, String archiveName, String entryName) {
//        resultSaver.saveDirEntry(path, archiveName, entryName).await();
    }

    @Override
    public void copyEntry(String source, String path, String archiveName, String entry) {
//        resultSaver.copyEntry(path, source, archiveName, entry).await();
    }

    @Override
    public void saveClassEntry(String path, String archiveName, String qualifiedName, String entryName, String content) {
        resultSaver.saveClassEntry(path, archiveName, qualifiedName, entryName, content).await();
    }

    @Override
    public void closeArchive(String path, String archiveName) {
//        resultSaver.closeArchive(path, archiveName).await();
    }
}
