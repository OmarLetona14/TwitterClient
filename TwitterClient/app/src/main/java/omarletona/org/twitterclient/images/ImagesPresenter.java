package omarletona.org.twitterclient.images;

import omarletona.org.twitterclient.images.events.ImagesEvent;

/**
 * Created by Omar on 07/07/2016.
 */
public interface ImagesPresenter {
    void onResume();
    void onPause();
    void onDestroy();

    void getImageTweets();
    void onEventMainThread(ImagesEvent event);

}
