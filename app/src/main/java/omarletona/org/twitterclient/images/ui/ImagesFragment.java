package omarletona.org.twitterclient.images.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.twitter.sdk.android.core.TwitterApiClient;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import omarletona.org.twitterclient.R;
import omarletona.org.twitterclient.TwitterClientApp;
import omarletona.org.twitterclient.images.ImagesPresenter;
import omarletona.org.twitterclient.images.adapters.ImagesAdapter;
import omarletona.org.twitterclient.images.adapters.OnItemClickListener;
import omarletona.org.twitterclient.images.di.ImagesComponent;
import omarletona.org.twitterclient.images.di.ImagesModule;
import omarletona.org.twitterclient.images.entities.Image;
import omarletona.org.twitterclient.lib.di.LibsModule;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImagesFragment extends Fragment implements ImagesView, OnItemClickListener{
    @Inject
    ImagesAdapter adapter;
    @Inject
    ImagesPresenter imagesPresenter;

    @Bind(R.id.container)
    FrameLayout container;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public void onResume() {
        super.onResume();
        imagesPresenter.onResume();
    }


    @Override
    public void onPause() {
        imagesPresenter.onPause();
        super.onPause();
    }


    @Override
    public void onDestroy() {
        imagesPresenter.onDestroy();
        super.onDestroy();
    }



    public ImagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);

        setupInjection();
        setupRecyclerView();
        imagesPresenter.getImageTweets();

        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
    }

    private void setupInjection() {
        TwitterClientApp app = (TwitterClientApp)getActivity().getApplication();
        ImagesComponent imagesComponent = app.getImagesComponent(this, this, this);
        imagesComponent.inject(this);
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
    public void onError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setContent(List<Image> items) {
        adapter.setItems(items);
    }

    @Override
    public void onItemClick(Image image) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(image.getTweetURL()));
        startActivity(intent);
    }
}
