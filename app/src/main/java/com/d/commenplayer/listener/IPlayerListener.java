package com.d.commenplayer.listener;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * IPlayerListener
 * Created by D on 2017/5/28.
 */
public interface IPlayerListener {
    /**
     * Register a callback to be invoked when the end of a media file
     * has been reached during playback.
     */
    void onCompletion(IMediaPlayer var1);

    /**
     * Register a callback to be invoked when the media file
     * is loaded and ready to go.
     */
    void onPrepared(IMediaPlayer var1);

    /**
     * Register a callback to be invoked when an error occurs
     * during playback or setup.  If no listener is specified,
     * or if the listener returned false, VideoView will inform
     * the user of any errors.
     */
    boolean onError(IMediaPlayer var1, int var2, int var3);

    /**
     * Register a callback to be invoked when an informational event
     * occurs during playback or setup.
     */
    boolean onInfo(IMediaPlayer var1, int var2, int var3);
}
