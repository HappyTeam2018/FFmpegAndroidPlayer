package com.fuchao.ffmpegandroidplayer;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Liang on 2017/3/29.
 */

public class Utils {

    private static final String TAG = "URI";

    public static String getVideoPath(Intent intent) {
        Uri mVideoUri = null;
        String mVideoPath = null;
        String intentAction = intent.getAction();
        if (!TextUtils.isEmpty(intentAction)) {
            if (intentAction.equals(Intent.ACTION_VIEW)) {
                mVideoPath = intent.getDataString();
                return Uri.decode(mVideoPath);
            } else if (intentAction.equals(Intent.ACTION_SEND)) {
                mVideoUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
                String scheme = mVideoUri.getScheme();
                if (TextUtils.isEmpty(scheme)) {
                    Log.e(TAG, "Null unknown scheme\n");
                    return null;
                }
                if (scheme.equals(ContentResolver.SCHEME_ANDROID_RESOURCE)) {
                    mVideoPath = mVideoUri.getPath();
                    return Uri.decode(mVideoPath);
                } else if (scheme.equals(ContentResolver.SCHEME_CONTENT)) {
                    Log.e(TAG, "Can not resolve content below Android-ICS\n");
                } else {
                    Log.e(TAG, "Unknown scheme " + scheme + "\n");
                }
            }
        }
        return null;
    }
}
