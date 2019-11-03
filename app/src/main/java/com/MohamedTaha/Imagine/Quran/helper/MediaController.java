package com.MohamedTaha.Imagine.Quran.helper;

import android.media.session.MediaSessionManager;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;

public class MediaController {

    //MediaSession
    private MediaSessionManager mediaSessionManager;
    private MediaSessionCompat mediaSession;
    public static MediaControllerCompat.TransportControls transportControls;
    private MediaControllerCompat controller;
    private MediaMetadataCompat mediaMetadata;
    private MediaDescriptionCompat description;
}
