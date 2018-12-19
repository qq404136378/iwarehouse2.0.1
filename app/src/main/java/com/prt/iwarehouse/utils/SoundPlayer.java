package com.prt.iwarehouse.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * @author 请叫我张懂
 * @Date 2018/4/5 22:35
 * @Description
 */

public class SoundPlayer {
    private Context context;
    private SoundPool soundPool;
    private int soundID;

    public SoundPlayer(Context context) {
        this.context = context;
        this.soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
    }

    /**
     * 加载声音资源
     *
     * @param resId
     * @return soundID
     */
    public int loadRes(int resId) {
        return this.soundPool.load(this.context, resId, 1);
    }

    /**
     * 播放声音
     *
     * @param soundID
     * @param priority
     * @param isLoop
     */
    public void play(int soundID, int priority, boolean isLoop) {
        this.soundPool.play(soundID, 1, 1, priority, isLoop ? -1 : 0, 1);
    }

    /**
     * 停止
     *
     * @param soundID
     */
    public void stop(int soundID) {
        this.soundPool.stop(soundID);
        this.soundPool.release();
    }

    /**
     * 播放声音
     *
     * @param resId
     * @param isLoop
     */
    public void play(int resId, final boolean isLoop) {
        this.soundID = loadRes(resId);
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                play(soundID, 0, isLoop);
            }
        });
    }

    /**
     * 停止
     */
    public void stop() {
        stop(this.soundID);
    }

}
