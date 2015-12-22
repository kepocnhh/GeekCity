package stan.geek.city.ui.activities;

import android.database.Cursor;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import stan.geek.city.GeekApp;
import stan.geek.city.R;
import stan.geek.city.database.SQliteApi;
import stan.geek.city.helpers.SQliteHelper;
import stan.geek.city.listeners.adapters.ICategoryListener;
import stan.geek.city.listeners.fragments.main.IMainFragmentClick;
import stan.geek.city.rest.requests.category.GetAllCategory;
import stan.geek.city.rest.responses.GeekResponse;
import stan.geek.city.ui.adapters.cactegory.CategoryRecyclerAdapter;
import stan.geek.city.ui.fragments.main.MainFragment;
import stan.geek.city.units.taxonomy.Category;

public class Main
        extends StanActivity
        implements IMainFragmentClick, ICategoryListener
{
    //_______________VIEWS
    android.support.v7.widget.RecyclerView category_recycler;
    android.support.v7.widget.Toolbar toolbar;
    DrawerLayout main_drawer;

    //_______________FRAGMENTS

    //_______________FIELDS
    private CategoryRecyclerAdapter categoryAdapter;
    private Category category;

    public Main()
    {
        super(R.layout.main, R.id.main_frame);
    }

    @Override
    protected void initFragments()
    {
    }

    @Override
    protected void initViews()
    {
        category_recycler = (RecyclerView) findViewById(R.id.category_recycler);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        main_drawer = (DrawerLayout) findViewById(R.id.main_drawer);
        initList();
    }

    private void initList()
    {
        categoryAdapter = new CategoryRecyclerAdapter(this, this);
        category_recycler.setLayoutManager(new LinearLayoutManager(this));
        category_recycler.setAdapter(categoryAdapter);
    }

    @Override
    protected void init()
    {
        category = null;
        initRequest();
        initToolbar();
        addFragment(MainFragment.newInstance());
    }

    private void initToolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, main_drawer,
                toolbar, R.string.app_name, R.string.app_name)
        {
            @Override
            public void onDrawerClosed(View drawerView)
            {
                super.onDrawerClosed(drawerView);
                if(category == null)
                {
                    toolbar.setSubtitle(R.string.posts);
                }
                else
                {
                    toolbar.setSubtitle(category.name);
                }
//                Log.e("ActionBarDrawerToggle", "onDrawerClosed");
            }

            @Override
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
                toolbar.setSubtitle(R.string.category);
//                Log.e("ActionBarDrawerToggle", "onDrawerOpened");
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)
            {
                super.onDrawerSlide(drawerView, slideOffset);
//                Log.e("ActionBarDrawerToggle", "onDrawerSlide - slideOffset = " + slideOffset);
            }

            @Override
            public void onDrawerStateChanged(int newState)
            {
                super.onDrawerStateChanged(newState);
//                Log.e("ActionBarDrawerToggle", "onDrawerStateChanged - newState = " + newState);
            }
        };
        main_drawer.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void initRequest()
    {
        GetAllCategory request = new GetAllCategory(this);
        GeekApp.spiceManager.execute(request, new RequestListener<GeekResponse>()
        {
            @Override
            public void onRequestFailure(SpiceException spiceException)
            {
                settingToolbar();
            }

            @Override
            public void onRequestSuccess(GeekResponse geekResponse)
            {
                settingToolbar();
            }
        });
    }

    private void settingToolbar()
    {
        Cursor c = SQliteApi.getCategory();
        categoryAdapter.swapCursor(c);
        Log.e("getCategory", "Category Count = " + c.getCount());
    }

    @Override
    public void pressCategory(int id)
    {
        Category unit = SQliteHelper.tryGetCategoryFromId(id);
        if(unit != null)
        {
            if(unit.ID == 1)
            {
                if(category != null)
                {
                    category = null;
                    replaceFragment(MainFragment.newInstance());
                }
            }
            else
            {
                if( (category != null && category.ID != unit.ID) || category == null)
                {
                    category = unit;
                    replaceFragment(MainFragment.newInstance(category.ID));
                }
            }
        }
        main_drawer.closeDrawer(GravityCompat.START);
    }
}