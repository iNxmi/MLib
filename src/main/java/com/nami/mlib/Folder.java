package com.nami.mlib;

import java.io.File;
import java.io.IOException;

public class Folder {

    private final File root;

    public Folder(String rootPath) {
        this.root = new File(rootPath);
        if (!root.exists())
            root.mkdirs();
    }

    public Folder(File file) {
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

    public Folder folder(String subPath) {
        File file = rawFile(subPath);
        if (!file.exists())
            file.mkdirs();

        return new Folder(file);
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
