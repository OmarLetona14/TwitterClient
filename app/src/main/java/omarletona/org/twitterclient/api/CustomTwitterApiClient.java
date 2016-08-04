package omarletona.org.twitterclient.api;

import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterApiClient;

/**
 * Created by Omar on 06/07/2016.
 */
public class CustomTwitterApiClient extends TwitterApiClient {
    public CustomTwitterApiClient(Session session) {
        super(session);
    }
    public TimeLineService getTimelineService(){
        return getService(TimeLineService.class);
    }
}
