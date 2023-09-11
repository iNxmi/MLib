package com.nami.mlib.usb;

import com.nami.mlib.Folder;

public interface GlobalUSBListener {

    void onUSBInsertEvent(Folder drive);

    void onUSBRemoveEvent();

}
