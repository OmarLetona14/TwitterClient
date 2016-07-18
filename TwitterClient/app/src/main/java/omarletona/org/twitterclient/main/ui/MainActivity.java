package omarletona.org.twitterclient.main.ui;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;

import butterknife.Bind;
import butterknife.ButterKnife;
import omarletona.org.twitterclient.LoginActivity;
import omarletona.org.twitterclient.R;
import omarletona.org.twitterclient.hashtags.ui.HashtagsFragment;
import omarletona.org.twitterclient.images.ui.ImagesFragment;
import omarletona.org.twitterclient.main.ui.adapters.MainSectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.container)
    ViewPager viewPager;
    @Bind(R.id.myEditText)
    EditText searchEditText;
    public static String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupAdapter();
    }

    private void setupAdapter() {

            Fragment[] fragments = new Fragment[] {new ImagesFragment(),
                    new HashtagsFragment()};

            String[] titles = new String[] {getString(R.string.main_header_images),
                    getString(R.string.main_header_hashtags)};

            setSupportActionBar(toolbar);

            MainSectionsPagerAdapter adapter = new MainSectionsPagerAdapter(getSupportFragmentManager(),
                    fragments,
                    titles);
            viewPager.setAdapter(adapter);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        if(id == R.id.action_search){
            if(searchEditText.getVisibility() == View.GONE){
                searchEditText.setVisibility(View.VISIBLE);
                return true;
            }else{
                search = searchEditText.getText().toString();
                searchEditText.setVisibility(View.GONE);
                hideKeyboard();
                setupAdapter();
                return true;
            }

        }

        return super.onOptionsItemSelected(item);
    }

    private void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static String getSearch(){
        return search;
    }

    private void logout() {
        Twitter.logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
