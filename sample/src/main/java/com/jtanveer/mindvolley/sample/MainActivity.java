package com.jtanveer.mindvolley.sample;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.jtanveer.mindvolley.DataRequestCallback;
import com.jtanveer.mindvolley.ImageRequestCallback;
import com.jtanveer.mindvolley.MindVolley;
import com.jtanveer.mindvolley.ModelMapper;
import com.jtanveer.mindvolley.sample.github.GithubResponse;
import com.jtanveer.mindvolley.sample.model.FeedResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView imgCheck1;
    ImageView imgCheck2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        imgCheck1 = findViewById(R.id.img_check1);
        imgCheck2 = findViewById(R.id.img_check2);

        MindVolley.init();
        MindVolley.getInstance().getImageVolley().from("https://images.unsplash.com/photo-1464550580740-b3f73fd373cb?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&w=1080&fit=max&s=899c346de4765f353375b8a5bd6cfc0e")
                .load(new ImageRequestCallback() {
                    @Override
                    public void onImageLoaded(Bitmap bitmap) {
                        imgCheck1.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(int fallbackImageResource) {
                        imgCheck1.setImageResource(fallbackImageResource);
                    }
                });

        MindVolley.getInstance().getImageVolley().from("https://images.unsplash.com/photo-1464536194743-0c49f0766ef6?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&w=1080&fit=max&s=7a862e9abeaa58183f65378c396a6e86")
                .load(new ImageRequestCallback() {
                    @Override
                    public void onImageLoaded(Bitmap bitmap) {
                        imgCheck2.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(int fallbackImageResource) {
                        imgCheck2.setImageResource(fallbackImageResource);
                    }
                });

//        MindVolley.getInstance().getDataVolley().modelMapper(new ModelMapper<FeedResponse, FeedResponse>()).newRequest(FeedResponse.class, FeedResponse.class).from("http://pastebin.com/raw/wgkJgazE").load(new DataRequestCallback<List<FeedResponse>, List<FeedResponse>>() {
//            @Override
//            public void onSuccess(List<FeedResponse> success) {
//                System.out.println(success.get(0).getUser().getName());
//            }
//
//            @Override
//            public void onError(List<FeedResponse> error) {
//
//            }
//        });

        MindVolley.getInstance().getDataVolley().modelMapper(new ModelMapper<GithubResponse, FeedResponse>()).newRequest(GithubResponse.class, FeedResponse.class).from("https://api.github.com/users/jtanveer").load(new DataRequestCallback<GithubResponse, FeedResponse>() {
            @Override
            public void onSuccess(GithubResponse success) {
                System.out.println(success.getName());
            }

            @Override
            public void onError(FeedResponse error) {
                System.out.println(error.getColor());
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
