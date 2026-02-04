declare module "@run-slicer/vf" {
    export type Options = Record<string, string>;

    export interface TokenCollector {
        start: (content: string) => void;
        visitClass: (start: number, length: number, declaration: boolean, name: string) => void;
        visitField: (start: number, length: number, declaration: boolean, className: string, name: string, descriptor: string) => void;
        visitMethod: (start: number, length: number, declaration: boolean, className: string, name: string, descriptor: string) => void;
        visitParameter: (start: number, length: number, declaration: boolean, className: string, methodName: string, methodDescriptor: string, index: number, name: string) => void;
        visitLocal: (start: number, length: number, declaration: boolean, className: string, methodName: string, methodDescriptor: string, index: number, name: string) => void;
        end: () => void;
    }

    export interface OutputSink {
        begin: () => Promise<void>;
        acceptClass: (qualifiedName: string, fileName: string, content: string, mapping: number[]) => Promise<void>;
        acceptDirectory: (directory: string) => Promise<void>;
        acceptOther: (path: string) => Promise<void>;
        close: () => Promise<void>;
    }

    export interface ResultSaver {
        // saveFolder: (path: string) => Promise<void>;
        // copyFile: (source: string, path: string, entryName: string) => Promise<void>;
        // saveClassFile: (path: string, qualifiedName: string, entryName: string, content: string, mapping: number[]) => Promise<void>;
        // createArchive: (path: string, archiveName: string, manifest: Manifest) => Promise<void>;
        // saveDirEntry: (path: string, archiveName: string, entryName: string) => Promise<void>;
        // copyEntry: (source: string, path: string, archiveName: string, entry) => Promise<void>;
        saveClassEntry: (path: string, archiveName: string, qualifiedName: string, entryName: string, content: string) => Promise<void>;
        // closeArchive: (path: string, archiveName: string) => Promise<void>;
    }

    export interface Config {
        source?: (name: string) => Promise<Uint8Array | null>;
        resources?: string[];
        options?: Options;
        tokenCollector?: TokenCollector;
    }

    // export interface ManyConfig extends Config {
    //     outputSink: OutputSink;
    //     // resultSaver: ResultSaver;
    // }

    export function decompile(name: string, config?: Config): Promise<string>;

    export function decompileMany(names: string[], config: Config): Promise<Record<string, string>>;
}
