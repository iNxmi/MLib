package com.nami.mlib.usb;

import com.nami.mlib.Directory;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GlobalUSBHook {

    private static LinkedList<GlobalUSBListener> listeners = new LinkedList<>();

    public static void init(double pollRatePerSecond) {
        Thread thread = new Thread(() -> {
            List<String> usb = new ArrayList<>();
            while (true) {
                File[] roots = File.listRoots();

                //Check for removed USB drives
                for (int i = 0; i < usb.size(); i++) {
                    String str = usb.get(i);

                    File file = null;
                    for (File f : roots) {
                        String type = FileSystemView.getFileSystemView().getSystemTypeDescription(f);
                        if (!type.equalsIgnoreCase("USB Drive"))
                            continue;

                        String name = FileSystemView.getFileSystemView().getSystemDisplayName(f);
                        if (!str.equals(name))
                            continue;

                        file = f;
                    }

                    if (file != null)
                        continue;

                    usb.remove(str);
                    listeners.forEach((l) -> l.onUSBRemoveEvent());
                }

                //Check for inserted USB drives
                for (File f : roots) {
                    String type = FileSystemView.getFileSystemView().getSystemTypeDescription(f);
                    if (type == null || !type.equalsIgnoreCase("USB Drive"))
                        continue;

                    String name = FileSystemView.getFileSystemView().getSystemDisplayName(f);
                    if (usb.contains(name))
                        continue;

                    usb.add(name);
                    listeners.forEach((l) -> l.onUSBInsertEvent(new Directory(f)));
                }

                try {
                    Thread.sleep((long) ((1.0d / pollRatePerSecond) * 1000.0d));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "usb-thread");
        thread.start();
    }

    public static void addUSBListener(GlobalUSBListener listener) {
        listeners.add(listener);
    }

}
