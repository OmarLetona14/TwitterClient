package omarletona.org.twitterclient.api;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Omar on 06/07/2016.
 */
public interface TimeLineService {
    @GET("/1.1/statuses/home_timeline.json")
    void homeTimeline(@Query("count") Integer count,
                      @Query("trim_user") Boolean trim_user,
                      @Query("exclude_replies") Boolean exclude_replies,
                      @Query("contributor_details") Boolean contributor_details,
                      @Query("include_entities") Boolean include_entities,
                      Callback<List<Tweet>> callback);
}
