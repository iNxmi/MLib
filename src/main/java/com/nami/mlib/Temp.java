package com.nami.mlib;

import java.io.File;

public class Temp {

    public static final Directory DIR_TEMP = new Directory(System.getProperty("java.io.tmpdir"));

    public static File tempFile() {
        File file = DIR_TEMP.file(String.format("%s.tmp", randomLong()));
        file.deleteOnExit();

        return file;
    }

    public static Directory tempDirectory() {
        Directory dir = DIR_TEMP.folder(String.format("%s", randomLong()));
        dir.asFile().deleteOnExit();

        return dir;
    }

    private static long randomLong() {
        return (long) (Math.random() * System.currentTimeMillis());
    }

}
