package com.nami.mlib;

import java.io.File;
import java.io.IOException;

public class Directory {

    private final File root;

    public Directory(String rootPath) {
        this.root = new File(rootPath);
        if (!root.exists())
            root.mkdirs();
    }

    public Directory(File file) {
        this.root = file;
        if (!root.exists())
            root.mkdirs();
    }

    public File file(String subPath) {
        File file = rawFile(subPath);
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return file;
    }

    public Directory folder(String subPath) {
        File file = rawFile(subPath);
        if (!file.exists())
            file.mkdirs();

        return new Directory(file);
    }

    private File rawFile(String subPath) {
        File file = new File(String.format("%s/%s", root.getAbsolutePath(), subPath));

        if (!file.getParentFile().exists())
            file.mkdirs();

        return file;
    }

    public File asFile() {
        return root;
    }

}
