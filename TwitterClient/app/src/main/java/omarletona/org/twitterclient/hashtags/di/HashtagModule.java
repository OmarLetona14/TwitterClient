package omarletona.org.twitterclient.hashtags.di;

import android.content.Context;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import omarletona.org.twitterclient.api.CustomTwitterApiClient;
import omarletona.org.twitterclient.hashtags.HashtagInteractor;
import omarletona.org.twitterclient.hashtags.HashtagInteractorImpl;
import omarletona.org.twitterclient.hashtags.HashtagPresenter;
import omarletona.org.twitterclient.hashtags.HashtagPresenterImpl;
import omarletona.org.twitterclient.hashtags.HashtagRepository;
import omarletona.org.twitterclient.hashtags.HashtagRepositoryImpl;
import omarletona.org.twitterclient.hashtags.adapters.HashtagsAdapter;
import omarletona.org.twitterclient.hashtags.entities.CustomTweet;
import omarletona.org.twitterclient.hashtags.entities.Hashtag;
import omarletona.org.twitterclient.hashtags.ui.HashtagView;
import omarletona.org.twitterclient.hashtags.ui.OnItemClickListener;
import omarletona.org.twitterclient.lib.base.EventBus;

/**
 * Created by Omar on 08/07/2016.
 */
@Module
public class HashtagModule {
    private HashtagView view;
    private OnItemClickListener clickListener;

    public HashtagModule(HashtagView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    List<CustomTweet> provideItems() {
        return new ArrayList<CustomTweet>();
    }

    @Provides
    @Singleton
    OnItemClickListener provideClickListener() {
        return this.clickListener;
    }

    @Provides
    HashtagsAdapter provideAdapter(List<CustomTweet> items, OnItemClickListener clickListener) {
        return new HashtagsAdapter(items, clickListener);
    }

    @Provides
    @Singleton
    HashtagView provideHashtagsView() {
        return this.view;
    }

    @Provides
    @Singleton
    HashtagPresenter provideHashtagsPresenter(HashtagView view, HashtagInteractor interactor, EventBus eventBus) {
        return new HashtagPresenterImpl(view, interactor, eventBus);
    }

    @Provides
    @Singleton
    HashtagInteractor provideHashtagsInteractor(HashtagRepository repository) {
        return new HashtagInteractorImpl(repository);
    }

    @Provides
    @Singleton
    HashtagRepository provideHashtagsRepository(CustomTwitterApiClient client, EventBus eventBus) {
        return new HashtagRepositoryImpl(client, eventBus);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient provideTwitterApiClient(TwitterSession session) {
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    TwitterSession provideTwitterSession() {
        return Twitter.getSessionManager().getActiveSession();
    }
}
