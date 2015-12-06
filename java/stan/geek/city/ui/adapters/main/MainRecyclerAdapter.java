package stan.geek.city.ui.adapters.main;

import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import stan.geek.city.R;
import stan.geek.city.database.Tables;
import stan.geek.city.helpers.PicturesLoader;
import stan.geek.city.listeners.adapters.main.IMainRecyclerAdapterListener;
import stan.geek.city.ui.holders.adapters.GeekRecyclerAdapter;
import stan.geek.city.ui.holders.adapters.main.MainRecyclerHolder;
import stan.geek.city.units.Post;

public class MainRecyclerAdapter
        extends GeekRecyclerAdapter
{
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
        getHolder(h).post_title.setText(
                mCursor.getString(mCursor.getColumnIndex(Tables.PostSimple_title_COLUMN)));
        getHolder(h).post_excerpt.loadDataWithBaseURL(null, mCursor.getString(mCursor.getColumnIndex(Tables.PostSimple_excerpt_COLUMN)), "text/html", "utf-8", null);
        PicturesLoader.loadImage(getHolder(h).post_image, mCursor.getString(mCursor.getColumnIndex(Tables.PostSimple_featured_image_COLUMN)));
    }

    MainRecyclerHolder getHolder(RecyclerView.ViewHolder holder)
    {
        return (MainRecyclerHolder) holder;
    }
}