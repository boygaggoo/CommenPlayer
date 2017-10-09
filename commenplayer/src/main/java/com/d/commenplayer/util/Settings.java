package com.d.commenplayer.util;

import android.content.Context;

/**
 * 预设配置
 */
public class Settings {
    public Settings(Context context) {
    }

    public boolean getEnableBackgroundPlay() {
        return false;
    }

    public boolean getEnableNoView() {
        return false;
    }

    public boolean getEnableSurfaceView() {
        return true;
    }

    public boolean getEnableTextureView() {
        return false;
    }
}
