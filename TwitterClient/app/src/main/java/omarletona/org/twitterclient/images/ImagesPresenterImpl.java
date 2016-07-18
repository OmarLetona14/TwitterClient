package omarletona.org.twitterclient.images;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import omarletona.org.twitterclient.hashtags.entities.CustomTweet;
import omarletona.org.twitterclient.images.entities.Image;
import omarletona.org.twitterclient.images.events.ImagesEvent;
import omarletona.org.twitterclient.images.ui.ImagesView;
import omarletona.org.twitterclient.lib.base.EventBus;

/**
 * Created by Omar on 07/07/2016.
 */
public class ImagesPresenterImpl implements ImagesPresenter{
    private EventBus eventBus;
    private ImagesView imagesView;
    private final ImagesInteractor imagesInteractor;

    public ImagesPresenterImpl(ImagesView imagesView, ImagesInteractor imagesInteractor, EventBus eventBus) {
        this.eventBus = eventBus;
        this.imagesView = imagesView;
        this.imagesInteractor = imagesInteractor;
    }
    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        imagesView = null;

    }

    @Override
    public void getImageTweets() {
        if (this.imagesView != null){
            imagesView.hideImages();
            imagesView.showProgress();
        }

        this.imagesInteractor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(ImagesEvent event) {
        String errorMsg = event.getError();
        if (this.imagesView != null) {
            imagesView.showImages();
            imagesView.hideProgress();
            if (errorMsg != null) {
                this.imagesView.onError(errorMsg);
            } else {
                List<CustomTweet> items = event.getImages();
                if (items != null && !items.isEmpty()) {
                    this.imagesView.setContent(items);
                }
            }
        }
    }
}
