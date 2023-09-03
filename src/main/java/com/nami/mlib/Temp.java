package com.nami.mlib;

import java.io.File;

public class Temp {

    public static final Folder TEMP_FOLDER = new Folder(System.getProperty("java.io.tmpdir"));

    public static File tempFile() {
        File file = TEMP_FOLDER.file(String.format("%s.tmp", (long) (Math.random() * System.currentTimeMillis())));
        file.deleteOnExit();

        return file;
    }

}
