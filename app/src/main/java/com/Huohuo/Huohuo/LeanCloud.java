package com.Huohuo.Huohuo;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVOSCloud;

import org.litepal.LitePalApplication;

/**
 * Created by yqhok on 2017-03-13.
 */

public class LeanCloud extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        AVOSCloud.initialize(this, "BeDY3fUOX1vdTWUevFb7CtyK-gzGzoHsz", "MFc2ErISiyEoizBpkloF8o2d");
        LitePalApplication.initialize(context);
    }
}
