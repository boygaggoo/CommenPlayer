package com.d.commenplayer.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.d.commenplayer.CommenPlayer;
import com.d.commenplayer.R;
import com.d.commenplayer.listener.OnShowThumbnailListener;
import com.d.commenplayer.util.MUtil;

public class AdapterPlayer extends FrameLayout {
    private FrameLayout flytThumbnail;
    private ImageView ivThumbnail;
    private ImageView ivThumbnailPlay;
    private CommenPlayer player;
    private boolean live;
    private String url;

    public AdapterPlayer(Context context) {
        super(context);
        init(context);
    }

    public AdapterPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AdapterPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.layout_aplayer, this);
        initView(root);
    }

    protected void initView(View root) {
        flytThumbnail = (FrameLayout) root.findViewById(R.id.flyt_thumbnail);
        ivThumbnail = (ImageView) root.findViewById(R.id.iv_thumbnail);
        ivThumbnailPlay = (ImageView) root.findViewById(R.id.iv_thumbnail_play);
        flytThumbnail.setVisibility(VISIBLE);
        ivThumbnailPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player == null) {
                    return;
                }
                player.pause();
                MUtil.peelInject(player, AdapterPlayer.this);
                flytThumbnail.setVisibility(GONE);
                player.play(url);
            }
        });
    }

    public AdapterPlayer setThumbnail(OnShowThumbnailListener listener) {
        if (listener != null) {
            listener.onShowThumbnail(ivThumbnail);
        }
        return this;
    }

    public void with(CommenPlayer player) {
        this.player = player;
    }

    public void inject() {
        flytThumbnail.setVisibility(GONE);
        if (player != null) {
            removeView(player);
            addView(player, 0);
        }
    }

    public void recycle(boolean pause) {
        flytThumbnail.setVisibility(VISIBLE);
        if (player != null) {
            if (pause && getChildAt(0) == player) {
                player.pause();
            }
            removeView(player);
        }
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
