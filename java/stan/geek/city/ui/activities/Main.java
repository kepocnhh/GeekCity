package stan.geek.city.ui.activities;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import stan.geek.city.R;
import stan.geek.city.listeners.fragments.main.IMainFragmentClick;
import stan.geek.city.ui.fragments.main.MainFragment;

public class Main
        extends StanActivity
        implements IMainFragmentClick
{
    //_______________VIEWS

    //_______________FIELDS

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
        //        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setHomeButtonEnabled(true);
        DrawerLayout main_drawer = (DrawerLayout) findViewById(R.id.main_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, main_drawer, R.string.app_name, R.string.MainFragment);
        toggle.setDrawerIndicatorEnabled(true);
        main_drawer.setDrawerListener(toggle);
    }

    @Override
    protected void init()
    {
        addFragment(MainFragment.newInstance());
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add("menu1");
        menu.add("menu2");
        menu.add("menu3");
        menu.add("menu4");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}