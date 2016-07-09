package omarletona.org.twitterclient.hashtags;

import omarletona.org.twitterclient.hashtags.events.HashtagsEvent;

/**
 * Created by Omar on 08/07/2016.
 */
public interface HashtagPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getHashtagTweets();
    void onEventMainThread(HashtagsEvent event);

}
