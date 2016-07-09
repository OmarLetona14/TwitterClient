package omarletona.org.twitterclient;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;
import omarletona.org.twitterclient.hashtags.di.DaggerHashtagComponent;
import omarletona.org.twitterclient.hashtags.di.HashtagComponent;
import omarletona.org.twitterclient.hashtags.di.HashtagModule;
import omarletona.org.twitterclient.hashtags.ui.HashtagView;
import omarletona.org.twitterclient.images.adapters.OnItemClickListener;
import omarletona.org.twitterclient.images.di.DaggerImagesComponent;
import omarletona.org.twitterclient.images.di.ImagesComponent;
import omarletona.org.twitterclient.images.di.ImagesModule;
import omarletona.org.twitterclient.images.ui.ImagesView;
import omarletona.org.twitterclient.lib.di.LibsModule;

/**
 * Created by Omar on 06/07/2016.
 */
public class TwitterClientApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initFabric();
    }

    private void initFabric() {
       TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

    }
    public ImagesComponent getImagesComponent(Fragment fragment, ImagesView view, OnItemClickListener listener){
        return   DaggerImagesComponent
                .builder()
                .libsModule(new LibsModule(fragment))
                .imagesModule(new ImagesModule(view, listener))
                .build();

    }
    public HashtagComponent getHashtagComponent(Fragment fragment, HashtagView view, omarletona.org.twitterclient.hashtags.ui.OnItemClickListener listener){
        return   DaggerHashtagComponent
                .builder()
                .libsModule(new LibsModule(fragment))
                .hashtagModule(new HashtagModule(view, listener))
                .build();
    }
}
