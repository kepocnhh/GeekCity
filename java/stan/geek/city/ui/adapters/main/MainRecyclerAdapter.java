package stan.geek.city.ui.adapters.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import stan.geek.city.R;
import stan.geek.city.database.Tables;
import stan.geek.city.helpers.PicturesLoader;
import stan.geek.city.ui.holders.adapters.GeekRecyclerAdapter;
import stan.geek.city.ui.holders.adapters.main.MainRecyclerHolder;

public class MainRecyclerAdapter
        extends GeekRecyclerAdapter
{
    public MainRecyclerAdapter(Context context, int l)
    {
        super(context, l);
    }
    public MainRecyclerAdapter(Context context)
    {
        super(context, R.layout.post);
    }

    @Override
    protected RecyclerView.ViewHolder initHolder(View v)
    {
        return new MainRecyclerHolder(v);
    }

    @Override
    protected void initView(RecyclerView.ViewHolder h, int i)
    {
        getHolder(h).post_title.setText(getString(Tables.PostSimple_title_COLUMN));
        getHolder(h).post_excerpt.setText(getString(Tables.PostSimple_excerpt_COLUMN));
        PicturesLoader.loadImage(getHolder(h).post_image,
                getString(Tables.PostSimple_featured_image_COLUMN));
    }

    MainRecyclerHolder getHolder(RecyclerView.ViewHolder holder)
    {
        return (MainRecyclerHolder) holder;
    }
}