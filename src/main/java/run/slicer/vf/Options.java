package run.slicer.vf;

import org.jetbrains.annotations.Nullable;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.core.JSPromise;
import org.teavm.jso.typedarrays.Uint8Array;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Manifest;

public interface Options extends JSObject {
    @JSBody(script = "return this.options ? Object.entries(this.options) : [];")
    Option[] options();

    default Map<String, String> rawOptions() {
        final Map<String, String> options = new HashMap<>();
        for (final Options.Option option : this.options()) {
            options.put(option.name(), option.value());
        }

        return options;
    }

    @JSBody(params = {"name"}, script = "return this.source ? this.source(name) : Promise.resolve(null);")
    JSPromise<Uint8Array> source(String name);

    @JSBody(script = "return this.resources || [];")
    String[] resources();

    @JSBody(script = "return this.tokenCollector || null;")
    @Nullable
    TokenCollector tokenCollector();

    @JSBody(script = "return this.outputSink || null;")
    @Nullable
    OutputSink outputSink();

    @JSBody(script = "return this.resultSaver || null;")
    @Nullable
    ResultSaver resultSaver();

    interface Option extends JSObject {
        @JSBody(script = "return this[0];")
        String name();

        @JSBody(script = "return this[1];")
        String value();
    }

    /**
     * {@link org.jetbrains.java.decompiler.main.extern.TextTokenVisitor}
     */
    interface TokenCollector extends JSObject {
        @JSBody(params = {"content"}, script = "this.start(content);")
        void start(String content);

        @JSBody(params = {"start", "length", "declaration", "name"}, script = "this.visitClass(start, length, declaration, name);")
        void visitClass(int start, int length, boolean declaration, String name);

        @JSBody(params = {"start", "length", "declaration", "className", "name", "descriptor"}, script = "this.visitField(start, length, declaration, className, name, descriptor);")
        void visitField(int start, int length, boolean declaration, String className, String name, String descriptor);

        @JSBody(params = {"start", "length", "declaration", "className", "name", "descriptor"}, script = "this.visitMethod(start, length, declaration, className, name, descriptor);")
        void visitMethod(int start, int length, boolean declaration, String className, String name, String descriptor);

        @JSBody(params = {"start", "length", "declaration", "className", "methodName", "methodDescriptor", "index", "name"}, script = "this.visitParameter(start, length, declaration, className, methodName, methodDescriptor, index, name);")
        void visitParameter(int start, int length, boolean declaration, String className, String methodName, String methodDescriptor, int index, String name);

        @JSBody(params = {"start", "length", "declaration", "className", "methodName", "methodDescriptor", "index", "name"}, script = "this.visitLocal(start, length, declaration, className, methodName, methodDescriptor, index, name);")
        void visitLocal(int start, int length, boolean declaration, String className, String methodName, String methodDescriptor, int index, String name);

        @JSBody(script = "this.end();")
        void end();
    }

    /**
     * {@link org.jetbrains.java.decompiler.main.extern.IContextSource.IOutputSink}
     */
    interface OutputSink extends JSObject {
        @JSBody(script = "return this.begin();")
        JSPromise<Void> begin();

        @JSBody(params = {"qualifiedName", "fileName", "content", "mapping"}, script = "return this.acceptClass(qualifiedName, fileName, content, mapping);")
        JSPromise<Void> acceptClass(final String qualifiedName, final String fileName, final String content, final int[] mapping);

        @JSBody(params = {"directory"}, script = "return this.acceptDirectory(directory);")
        JSPromise<Void> acceptDirectory(final String directory);

        @JSBody(params = {"path"}, script = "return this.acceptOther(path);")
        JSPromise<Void> acceptOther(final String path);

        JSPromise<Void> close();
    }

    /**
     * {@link org.jetbrains.java.decompiler.main.extern.IResultSaver}
     */
    interface ResultSaver extends JSObject {
//        @JSBody(params = {"path"}, script = "return this.saveFolder(path);")
//        JSPromise<Void> saveFolder(final String path);
//
//        @JSBody(params = {"source", "path", "entryName"}, script = "return this.copyFile(source, path, entryName);")
//        JSPromise<Void> copyFile(final String source, final String path, final String entryName);
//
//        @JSBody(params = {"path", "qualifiedName", "entryName", "content", "mapping"}, script = "return this.saveClassFile(path, qualifiedName, entryName, content, mapping);")
//        JSPromise<Void> saveClassFile(final String path, final String qualifiedName, final String entryName, final String content, final int[] mapping);
//
//        @JSBody(params = {"path", "archiveName", "manifest"}, script = "return this.createArchive(path, archiveName, manifest);")
//        JSPromise<Void> createArchive(final String path, final String archiveName, final Manifest manifest);
//
//        @JSBody(params = {"path", "archiveName", "entryName"}, script = "return this.saveDirEntry(path, archiveName, entryName);")
//        JSPromise<Void> saveDirEntry(final String path, final String archiveName, final String entryName);
//
//        @JSBody(params = {"source", "path", "archiveName", "entry"}, script = "return this.copyEntry(source, path, archiveName, entry);")
//        JSPromise<Void> copyEntry(final String source, final String path, final String archiveName, final String entry);

        @JSBody(params = {"path", "archiveName", "qualifiedName", "entryName", "content"}, script = "return this.saveClassEntry(path, archiveName, qualifiedName, entryName, content);")
        JSPromise<Void> saveClassEntry(final String path, final String archiveName, final String qualifiedName, final String entryName, final String content);

//        @JSBody(params = {"path", "archiveName"}, script = "return this.closeArchive(path, archiveName);")
//        JSPromise<Void> closeArchive(final String path, final String archiveName);
    }
}
