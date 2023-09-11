package com.nami.mlib.usb;

import com.nami.mlib.Directory;

public interface GlobalUSBListener {

    void onUSBInsertEvent(Directory drive);

    void onUSBRemoveEvent();

}
