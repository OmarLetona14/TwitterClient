package omarletona.org.twitterclient.hashtags.events;

import java.util.List;

import omarletona.org.twitterclient.hashtags.entities.CustomTweet;
import omarletona.org.twitterclient.hashtags.entities.Hashtag;

/**
 * Created by Omar on 08/07/2016.
 */
public class HashtagsEvent {
    private String error;
    private List<CustomTweet> tweets;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<CustomTweet> getHashtags() {
        return tweets;
    }

    public void setHashtags(List<CustomTweet> hashtags) {
        this.tweets = hashtags;
    }
}
