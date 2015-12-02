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
import stan.geek.city.ui.holders.adapters.main.MainRecyclerHolder;
import stan.geek.city.units.Post;

public class MainRecyclerAdapter
        extends RecyclerView.Adapter<MainRecyclerHolder>
{
    private Context mContext;
    private IMainRecyclerAdapterListener clickListener;
    private Cursor mCursor;
//    private List<Post> posts;

    public MainRecyclerAdapter(Context context, IMainRecyclerAdapterListener oc)
    {
        mContext = context;
//        posts = new ArrayList<>();
        clickListener = oc;
    }

    @Override
    public MainRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.post, parent, false);
        MainRecyclerHolder holder = new MainRecyclerHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MainRecyclerHolder holder, int i)
    {
        if(mCursor.moveToPosition(i))
        {
//            holder.post_title.setText(posts.get(position).title);
//            holder.post_excerpt.loadDataWithBaseURL(null, posts.get(position).excerpt, "text/html", "utf-8", null);
//            PicturesLoader.loadImage(holder.post_image, posts.get(position).featured_image.guid);
            holder.post_title.setText(mCursor.getString(mCursor.getColumnIndex(Tables.PostSimple_title_COLUMN)));
            holder.post_excerpt.loadDataWithBaseURL(null, mCursor.getString(mCursor.getColumnIndex(Tables.PostSimple_excerpt_COLUMN)), "text/html", "utf-8", null);
            PicturesLoader.loadImage(holder.post_image, mCursor.getString(mCursor.getColumnIndex(Tables.PostSimple_featured_image_COLUMN)));
        }
    }

    @Override
    public long getItemId(int position)
    {
//        return posts.get(position).ID;
        mCursor.moveToPosition(position);
        return mCursor.getInt(mCursor.getColumnIndex(Tables.GeekID));
    }

    @Override
    public int getItemCount()
    {
        if(mCursor == null)
        {
            return 0;
        } else
        {
            return mCursor.getCount();
        }
    }

    public void swapCursor(Cursor c)
    {
        if(mCursor!=null)
        {
            mCursor.close();
        }
        mCursor = c;
        notifyDataSetChanged();
    }
//    public void swapList(List<Post> ps)
//    {
//        posts.addAll(ps);
//        notifyDataSetChanged();
//    }
//    public void clearList()
//    {
//        posts = new ArrayList<>();
//        notifyDataSetChanged();
//    }
}