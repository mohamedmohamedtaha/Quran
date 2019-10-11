package com.MohamedTaha.Imagine.Quran.helper.util;

import com.MohamedTaha.Imagine.Quran.getData.Model;

import java.util.ArrayList;
import android.os.Handler;

public class PlayerConstants {

    //List of Songs
    public static ArrayList<Model> SONGS_LIST = new ArrayList<>();

    //Song number which is Playing right now from SONGS_LIST
    public static int SONG_NUMBER = 0;

    //Song is Playing or paused
    public static boolean SONG_PAUESD = true;
    //Song changed (changeTextToNext, previous)
    public static boolean SONG_CHANGED = false;
    //handler for song changed(changeTextToNext, previous) defined in Service (SOngService)
    public static Handler SONG_CHANGE_HANDLER;
    //handler for song play/pause defined in Service(SongService)
    public static Handler PLAY_PAUSE_HANDLER;
    //handler for showing song progress defined in Activities(ManiActivity, AudioPlayerActivity)
    public static Handler PROGRESSER_HANDLER;
}
