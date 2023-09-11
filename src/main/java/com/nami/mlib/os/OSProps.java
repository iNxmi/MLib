package com.nami.mlib.os;

import com.nami.mlib.Directory;

public class OSProps {

    public static OS OS;
    public static Directory FOLDER_ROOT;

    public static boolean init(String applicationName) {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win"))
            OS = OS.WINDOWS;

        if (os.contains("mac"))
            OS = OS.MAC;

        if (os.contains("nix") || os.contains("nux") || os.contains("aix"))
            OS = OS.UNIX;

        if (os.contains("sunos"))
            OS = OS.SOLARIS;

        if (OS == null)
            return false;

        return OS.init(applicationName);
    }

    public enum OS {
        WINDOWS((applicationName) -> {
            String path = String.format("%s/%s", System.getenv("APPDATA"), applicationName);
            FOLDER_ROOT = new Directory(path);

            return true;
        }),

        MAC((applicationName) -> {
            String path = String.format("%s/%s", System.getProperty("user.home"), applicationName);
            FOLDER_ROOT = new Directory(path);

            return true;
        }),

        UNIX((applicationName) -> {
            String path = String.format("%s/%s", System.getProperty("user.home"), applicationName);
            FOLDER_ROOT = new Directory(path);

            return true;
        }),

        SOLARIS((applicationName) -> false);

        private OSRunnable runnable;

        OS(OSRunnable runnable) {
            this.runnable = runnable;
        }

        public boolean init(String applicationName) {
            return runnable.init(applicationName);
        }

    }



}

interface OSRunnable {
    boolean init(String applicationName);
}
