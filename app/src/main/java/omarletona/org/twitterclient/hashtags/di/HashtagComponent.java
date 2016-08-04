package omarletona.org.twitterclient.hashtags.di;

import javax.inject.Singleton;

import dagger.Component;
import omarletona.org.twitterclient.TwitterClientApp;
import omarletona.org.twitterclient.hashtags.ui.HashtagsFragment;
import omarletona.org.twitterclient.lib.di.LibsModule;

/**
 * Created by Omar on 08/07/2016.
 */

@Singleton
@Component(modules = {HashtagModule.class, LibsModule.class})
public interface HashtagComponent {
    void inject(HashtagsFragment fragment);
}

