package omarletona.org.twitterclient.images.di;

import javax.inject.Singleton;

import dagger.Component;
import omarletona.org.twitterclient.images.ui.ImagesFragment;
import omarletona.org.twitterclient.lib.di.LibsModule;

/**
 * Created by Omar on 07/07/2016.
 */
@Singleton
@Component(modules = {ImagesModule.class, LibsModule.class})
public interface ImagesComponent {
    void inject(ImagesFragment fragment);
    //ImagesPresenter getPresenter();
}