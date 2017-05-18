package com.example.phemmelliot.ilab;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.example.phemmelliot.ilab.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;

import java.util.ArrayList;

public class DrawerActivity extends AppCompatActivity implements NewsFragment.OnFragmentInteractionListener{
    private String Tag = getClass().getSimpleName();
    private ListView listview;
    private ArrayList<News> NewsList;
    private NewsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);


        initCollapsingToolbar();
        try {
            Glide.with(this).load(R.drawable.hitech).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

        listview = (ListView) findViewById(R.id.list);

        NewsList = new ArrayList<>();
        adapter = new NewsAdapter(this, NewsList);
        listview.setAdapter(adapter);
        prepareNews();
        
        
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
//        recyclerView.setLayoutManager(mLayoutManager);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.addItemDecoration(new MainActivity.GridSpacingItemDecoration(2, dpToPx(0), true));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);

        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                 Glide.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            
        });
        AccountHeader header = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.header)
                .withSavedInstance(savedInstanceState)
                .addProfiles(new ProfileDrawerItem()
                        .withEmail("adegokefemi17@gmail.com")
                        .withIcon(R.drawable.d)
                        .withName("Adegoke Femi"))
                .build();

        //TODO: Try to get the area of interests from firebase onto the draweritems using the method below getting the the items inside an array
        PrimaryDrawerItem items = null;
        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(true)
                .withTranslucentStatusBar(true)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(header) 
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("All News Feed").withIcon(R.drawable.ic_menu_camera).withIdentifier(1),
                        new PrimaryDrawerItem().withName("Favorites").withIcon(R.drawable.ic_menu_gallery).withIdentifier(2),
                        new PrimaryDrawerItem().withName("Profile").withIcon(R.drawable.ic_menu_share).withIdentifier(3)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener(){
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem)
                    {
                        if(drawerItem != null)
                        {
                            Intent intent = null;
                            if(drawerItem.getIdentifier() == 1)
                            {

                            }
                            else if(drawerItem.getIdentifier() == 2)
                            {
                                NewsFragment fragment = new NewsFragment();
                                FragmentManager manager = getSupportFragmentManager();
                                manager.beginTransaction().replace(R.id.inside_layout, fragment).commit();
                            }
                            else if(drawerItem.getIdentifier() == 3)
                            {
                                NewsFragment fragment = new NewsFragment();
                                FragmentManager manager = getSupportFragmentManager();
                                manager.beginTransaction().replace(R.id.inside_layout, fragment).commit();
                            }

                            if(intent != null)
                            {
                                DrawerActivity.this.startActivity(intent);
                            }
                        }
                        return false;
                    }
                })
                .withActionBarDrawerToggleAnimated(true)
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .build();

        if(drawer == null)
        {
            Log.d(Tag, "The drawer wasn't initialized");

            
        } else {
            Log.d(Tag, "There is nothing wrong with the drawer");

        }


         //drawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
         //getSupportActionBar().setDisplayHomeAsUpEnabled(false);



    }
    public void onFragmentInteraction(Uri data)
    {

    }
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("");
        AppBarLayout appBarLayout = (AppBarLayout)findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset != 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
    private void prepareNews() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11};

        News a = new News("True Romance", "Is it really a true romance, well if you say so so shall it be", "www.google.com", covers[0]);
        NewsList.add(a);

        a = new News("Kotlin Replaces Java", "There was an outburst on wednesday when google announced that kotlin can now officially replace java in android development" +
                "Which according to people happens because oracle was trying to sue google for using their language", "http:google.com",covers[1]);
        NewsList.add(a);

        a = new News("Kotlin Replaces Java", "There was an outburst on wednesday when google announced that kotlin can now officially replace java in android development" +
                "Which according to people happens because oracle was trying to sue google for using their language", "http:google.com",covers[2]);
        NewsList.add(a);

        a = new News("Kotlin Replaces Java", "There was an outburst on wednesday when google announced that kotlin can now officially replace java in android development" +
                "Which according to people happens because oracle was trying to sue google for using their language", "http:google.com",covers[3]);
        NewsList.add(a);

        a = new News("Kotlin Replaces Java", "There was an outburst on wednesday when google announced that kotlin can now officially replace java in android development" +
                "Which according to people happens because oracle was trying to sue google for using their language", "http:google.com",covers[4]);
        NewsList.add(a);

        a = new News("Kotlin Replaces Java", "There was an outburst on wednesday when google announced that kotlin can now officially replace java in android development" +
                "Which according to people happens because oracle was trying to sue google for using their language", "http:google.com",covers[5]);
        NewsList.add(a);

        a = new News("Kotlin Replaces Java", "There was an outburst on wednesday when google announced that kotlin can now officially replace java in android development" +
                "Which according to people happens because oracle was trying to sue google for using their language", "http:google.com",covers[6]);
        NewsList.add(a);
        a = new News("Kotlin Replaces Java", "There was an outburst on wednesday when google announced that kotlin can now officially replace java in android development" +
                "Which according to people happens because oracle was trying to sue google for using their language", "http:google.com",covers[7]);
        NewsList.add(a);

        a = new News("Kotlin Replaces Java", "There was an outburst on wednesday when google announced that kotlin can now officially replace java in android development" +
                "Which according to people happens because oracle was trying to sue google for using their language", "http:google.com",covers[8]);
        NewsList.add(a);

        a = new News("Kotlin Replaces Java", "There was an outburst on wednesday when google announced that kotlin can now officially replace java in android development" +
                "Which according to people happens because oracle was trying to sue google for using their language", "http:google.com",covers[9]);
        NewsList.add(a);






        adapter.notifyDataSetChanged();
    }
        

}