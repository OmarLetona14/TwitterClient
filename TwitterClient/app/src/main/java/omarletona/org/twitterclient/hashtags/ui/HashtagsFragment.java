package omarletona.org.twitterclient.hashtags.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import omarletona.org.twitterclient.R;
import omarletona.org.twitterclient.TwitterClientApp;
import omarletona.org.twitterclient.hashtags.HashtagPresenter;
import omarletona.org.twitterclient.hashtags.adapters.HashtagsAdapter;
import omarletona.org.twitterclient.hashtags.di.HashtagComponent;
import omarletona.org.twitterclient.hashtags.entities.CustomTweet;
import omarletona.org.twitterclient.hashtags.entities.Hashtag;
import omarletona.org.twitterclient.main.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HashtagsFragment extends Fragment implements HashtagView, OnItemClickListener{
    @Inject
    HashtagsAdapter adapter;
    @Inject
    HashtagPresenter hashtagsPresenter;

    @Bind(R.id.container)
    FrameLayout container;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public void onResume() {
        super.onResume();
        hashtagsPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        hashtagsPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        hashtagsPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);

        setupInjection();
        setupRecyclerView();
        if(MainActivity.search != null && MainActivity.search != ""){
            hashtagsPresenter.getHashtagTweets();
        }else{
            Snackbar.make(container, "Por favor, introduzca una busqueda", Snackbar.LENGTH_LONG).show();
        }

        return view;
    }

    private void setupInjection() {
        TwitterClientApp app = (TwitterClientApp)getActivity().getApplication();
        HashtagComponent component = app.getHashtagComponent(this, this, this);
        component.inject(this);
    }


    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onHashtagsError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setContent(List<CustomTweet> items) {
        adapter.setItems(items);
    }

    @Override
    public void showImages() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImages() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(CustomTweet tweet) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweet.getTweetURL()));
        startActivity(intent);
    }

}
