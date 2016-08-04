package omarletona.org.twitterclient.hashtags;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import omarletona.org.twitterclient.hashtags.entities.Hashtag;
import omarletona.org.twitterclient.hashtags.events.HashtagsEvent;
import omarletona.org.twitterclient.hashtags.ui.HashtagView;
import omarletona.org.twitterclient.lib.base.EventBus;

/**
 * Created by Omar on 08/07/2016.
 */
public class HashtagPresenterImpl implements HashtagPresenter {
    private EventBus eventBus;
    private HashtagView hashtagsView;
    private HashtagInteractor hashtagsInteractor;

    public HashtagPresenterImpl(HashtagView hashtagsView, HashtagInteractor hashtagsInteractor, EventBus eventBus) {
        this.eventBus = eventBus;
        this.hashtagsView = hashtagsView;
        this.hashtagsInteractor = hashtagsInteractor;
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        this.hashtagsView = null;
    }

    @Override
    public void getHashtagTweets(){
        if (this.hashtagsView != null){
            hashtagsView.hideImages();
            hashtagsView.showProgress();
        }
        this.hashtagsInteractor.getHashtagItemsList();
    }

    @Override
    @Subscribe
    public void onEventMainThread(HashtagsEvent event) {
        String errorMsg = event.getError();
        if (this.hashtagsView != null) {
            hashtagsView.showImages();
            hashtagsView.hideProgress();
            if (errorMsg != null) {
                this.hashtagsView.onHashtagsError(errorMsg);
            } else {
                List<Hashtag> items = event.getHashtags();
                if (items != null && !items.isEmpty()) {
                    this.hashtagsView.setContent(items);
                }
            }
        }
    }
}
