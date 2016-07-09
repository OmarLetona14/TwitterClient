package omarletona.org.twitterclient.lib.di;


import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import omarletona.org.twitterclient.lib.GlideImageLoader;
import omarletona.org.twitterclient.lib.GreenRobotEventBus;
import omarletona.org.twitterclient.lib.base.EventBus;
import omarletona.org.twitterclient.lib.base.ImageLoader;

/**
 * Created by Omar on 07/07/2016.
 */
@Module
public class LibsModule {

    private Fragment fragment;

    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    public ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    public RequestManager providesRequestManager(Fragment fragment){
        return Glide.with(fragment);
    }

    @Provides
    @Singleton
    public Fragment providesFragment(){
        return this.fragment;
    }

    @Provides
    @Singleton
    public EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    public org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }
}
