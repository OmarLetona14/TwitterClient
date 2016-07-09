package omarletona.org.twitterclient.images.di;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import omarletona.org.twitterclient.api.CustomTwitterApiClient;
import omarletona.org.twitterclient.images.ImageRepository;
import omarletona.org.twitterclient.images.ImagesInteractor;
import omarletona.org.twitterclient.images.ImagesInteractorImpl;
import omarletona.org.twitterclient.images.ImagesPresenter;
import omarletona.org.twitterclient.images.ImagesPresenterImpl;
import omarletona.org.twitterclient.images.ImagesRepositoryImpl;
import omarletona.org.twitterclient.images.adapters.ImagesAdapter;
import omarletona.org.twitterclient.images.adapters.OnItemClickListener;
import omarletona.org.twitterclient.images.entities.Image;
import omarletona.org.twitterclient.images.ui.ImagesView;
import omarletona.org.twitterclient.lib.base.EventBus;
import omarletona.org.twitterclient.lib.base.ImageLoader;

/**
 * Created by Omar on 07/07/2016.
 */
@Module
public class ImagesModule {
    private ImagesView view;
    private OnItemClickListener clickListener;

    public ImagesModule(ImagesView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    List<Image> provideItems() {
        return new ArrayList<Image>();
    }

    @Provides
    @Singleton
    OnItemClickListener provideClickListener() {
        return this.clickListener;
    }

    @Provides
    ImagesAdapter provideAdapter(List<Image> items, OnItemClickListener clickListener, ImageLoader imageLoader) {
        return new ImagesAdapter(items, clickListener, imageLoader);
    }

    @Provides
    @Singleton
    ImagesView provideImagesView() {
        return this.view;
    }

    @Provides
    @Singleton
    ImagesPresenter provideImagesPresenter(ImagesView view, ImagesInteractor interactor, EventBus eventBus) {
        return new ImagesPresenterImpl(view, interactor, eventBus);
    }

    @Provides
    @Singleton
    ImagesInteractor provideImagesInteractor(ImageRepository repository) {
        return new ImagesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ImageRepository provideImagesRepository(CustomTwitterApiClient client, EventBus eventBus) {
        return new ImagesRepositoryImpl(client, eventBus);
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
