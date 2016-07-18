package omarletona.org.twitterclient.hashtags.entities;

import java.util.List;

/**
 * Created by Omar on 16/07/2016.
 */
public class CustomTweet {
    private List<String> hashtags;
    private String id;
    private String imageURL;
    private String tweetText;
    private int favoriteCount;
    private final static String BASE_TWEET_URL = "https://twitter.com/null/status/";

    public CustomTweet(List<String> hashtags, String id, String imageURL, String tweetText, int favoriteCount) {
        this.hashtags = hashtags;
        this.id = id;
        this.imageURL = imageURL;
        this.tweetText = tweetText;
        this.favoriteCount = favoriteCount;
    }

    public CustomTweet() {
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public static String getTweetURL() {
        return BASE_TWEET_URL;
    }
}
