package com.d.commenplayer.widget.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.d.commenplayer.R;

import java.util.Locale;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;
import tv.danmaku.ijk.media.player.misc.IjkMediaFormat;

/**
 * Util
 * Created by D on 2017/5/27.
 */
public class Util {

    //-------------------------
    // Extend: Background
    //-------------------------
    public static void showMediaInfo(Context context, IMediaPlayer mMediaPlayer) {
        if (mMediaPlayer == null)
            return;

        int mVideoWidth = mMediaPlayer.getVideoWidth();
        int mVideoHeight = mMediaPlayer.getVideoHeight();
        int mVideoSarNum = mMediaPlayer.getVideoSarNum();
        int mVideoSarDen = mMediaPlayer.getVideoSarDen();

        int selectedVideoTrack = MediaPlayerCompat.getSelectedTrack(mMediaPlayer, ITrackInfo.MEDIA_TRACK_TYPE_VIDEO);
        int selectedAudioTrack = MediaPlayerCompat.getSelectedTrack(mMediaPlayer, ITrackInfo.MEDIA_TRACK_TYPE_AUDIO);
        int selectedSubtitleTrack = MediaPlayerCompat.getSelectedTrack(mMediaPlayer, ITrackInfo.MEDIA_TRACK_TYPE_TIMEDTEXT);

        //media_information
        Log.d("Player", MediaPlayerCompat.getName(mMediaPlayer));
        Log.d("Resolution", buildResolution(mVideoWidth, mVideoHeight, mVideoSarNum, mVideoSarDen));
        Log.d("Length", buildTimeMilli(mMediaPlayer.getDuration()));

        ITrackInfo trackInfos[] = mMediaPlayer.getTrackInfo();
        if (trackInfos != null) {
            int index = -1;
            for (ITrackInfo trackInfo : trackInfos) {
                index++;

                int trackType = trackInfo.getTrackType();
                if (index == selectedVideoTrack) {
                    Log.d("Stream #", "" + index + " " + context.getString(R.string.mi__selected_video_track));
                } else if (index == selectedAudioTrack) {
                    Log.d("Stream #", "" + index + " " + context.getString(R.string.mi__selected_audio_track));
                } else if (index == selectedSubtitleTrack) {
                    Log.d("Stream #", "" + index + " " + context.getString(R.string.mi__selected_subtitle_track));
                } else {
                    Log.d("Stream #", "" + index);
                }
                Log.d("Type", buildTrackType(context, trackType));
                Log.d("Language", TextUtils.isEmpty(trackInfo.getLanguage()) ? "und" : trackInfo.getLanguage());

                IMediaFormat mediaFormat = trackInfo.getFormat();
                if (mediaFormat == null) {
                } else if (mediaFormat instanceof IjkMediaFormat) {
                    switch (trackType) {
                        case ITrackInfo.MEDIA_TRACK_TYPE_VIDEO:
                            Log.d("Codec", mediaFormat.getString(IjkMediaFormat.KEY_IJK_CODEC_LONG_NAME_UI));
                            Log.d("Profile level", mediaFormat.getString(IjkMediaFormat.KEY_IJK_CODEC_PROFILE_LEVEL_UI));
                            Log.d("Pixel format", mediaFormat.getString(IjkMediaFormat.KEY_IJK_CODEC_PIXEL_FORMAT_UI));
                            Log.d("Resolution", mediaFormat.getString(IjkMediaFormat.KEY_IJK_RESOLUTION_UI));
                            Log.d("Frame rate", mediaFormat.getString(IjkMediaFormat.KEY_IJK_FRAME_RATE_UI));
                            Log.d("Bit rate", mediaFormat.getString(IjkMediaFormat.KEY_IJK_BIT_RATE_UI));
                            break;
                        case ITrackInfo.MEDIA_TRACK_TYPE_AUDIO:
                            Log.d("Codec", mediaFormat.getString(IjkMediaFormat.KEY_IJK_CODEC_LONG_NAME_UI));
                            Log.d("Profile level", mediaFormat.getString(IjkMediaFormat.KEY_IJK_CODEC_PROFILE_LEVEL_UI));
                            Log.d("Sample rate", mediaFormat.getString(IjkMediaFormat.KEY_IJK_SAMPLE_RATE_UI));
                            Log.d("Channels", mediaFormat.getString(IjkMediaFormat.KEY_IJK_CHANNEL_UI));
                            Log.d("Bit rate", mediaFormat.getString(IjkMediaFormat.KEY_IJK_BIT_RATE_UI));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    private static String buildResolution(int width, int height, int sarNum, int sarDen) {
        StringBuilder sb = new StringBuilder();
        sb.append(width);
        sb.append(" x ");
        sb.append(height);

        if (sarNum > 1 || sarDen > 1) {
            sb.append("[");
            sb.append(sarNum);
            sb.append(":");
            sb.append(sarDen);
            sb.append("]");
        }

        return sb.toString();
    }

    private static String buildTimeMilli(long duration) {
        long total_seconds = duration / 1000;
        long hours = total_seconds / 3600;
        long minutes = (total_seconds % 3600) / 60;
        long seconds = total_seconds % 60;
        if (duration <= 0) {
            return "--:--";
        }
        if (hours >= 100) {
            return String.format(Locale.US, "%d:%02d:%02d", hours, minutes, seconds);
        } else if (hours > 0) {
            return String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format(Locale.US, "%02d:%02d", minutes, seconds);
        }
    }

    private static String buildTrackType(Context context, int type) {
        switch (type) {
            case ITrackInfo.MEDIA_TRACK_TYPE_VIDEO:
                return context.getString(R.string.TrackType_video);
            case ITrackInfo.MEDIA_TRACK_TYPE_AUDIO:
                return context.getString(R.string.TrackType_audio);
            case ITrackInfo.MEDIA_TRACK_TYPE_SUBTITLE:
                return context.getString(R.string.TrackType_subtitle);
            case ITrackInfo.MEDIA_TRACK_TYPE_TIMEDTEXT:
                return context.getString(R.string.TrackType_timedtext);
            case ITrackInfo.MEDIA_TRACK_TYPE_METADATA:
                return context.getString(R.string.TrackType_metadata);
            case ITrackInfo.MEDIA_TRACK_TYPE_UNKNOWN:
            default:
                return context.getString(R.string.TrackType_unknown);
        }
    }
}
