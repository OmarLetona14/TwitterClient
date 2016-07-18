package omarletona.org.twitterclient.hashtags.ui;

import java.util.List;

import omarletona.org.twitterclient.hashtags.entities.CustomTweet;
import omarletona.org.twitterclient.hashtags.entities.Hashtag;

/**
 * Created by Omar on 08/07/2016.
 */
public interface HashtagView {

    void showImages();
    void hideImages();
    void showProgress();
    void hideProgress();

    void onHashtagsError(String error);
    void setContent(List<CustomTweet> items);
}
