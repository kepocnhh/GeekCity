package stan.geek.city.ui.adapters.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import org.apache.commons.lang3.StringEscapeUtils;

import stan.geek.city.R;
import stan.geek.city.database.Tables;
import stan.geek.city.helpers.PicturesLoader;
import stan.geek.city.ui.holders.adapters.GeekRecyclerAdapter;
import stan.geek.city.ui.holders.adapters.main.MainRecyclerHolder;

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
        getHolder(h).post_title.setText(mCursor.getString(
                mCursor.getColumnIndex(Tables.PostSimple_title_COLUMN)));
        getHolder(h).post_excerpt.setText(mCursor.getString(mCursor.getColumnIndex(Tables.PostSimple_excerpt_COLUMN)));
//        getHolder(h).post_title.setText(getString(Tables.PostSimple_title_COLUMN));
//        WebView post_excerpt = new WebView(mContext);
//        post_excerpt.getSettings().setJavaScriptEnabled(true);
//        post_excerpt.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
//        post_excerpt.loadDataWithBaseURL(null, mCursor.getString(mCursor.getColumnIndex(Tables.PostSimple_excerpt_COLUMN)), "text/html", "utf-8", null);
//        post_excerpt.setOnLongClickListener(new View.OnLongClickListener()
//        {
//            @Override
//            public boolean onLongClick(View v)
//            {
//                return true;
//            }
//        });
//        TextView post_excerpt = new TextView(mContext);
//        post_excerpt.setTextColor(mContext.getResources().getColor(R.color.black));
//        post_excerpt.setText(mCursor.getString(mCursor.getColumnIndex(Tables.PostSimple_excerpt_COLUMN)));
//        getHolder(h).post_excerpt.removeAllViews();
//        getHolder(h).post_excerpt.addView(post_excerpt);
        PicturesLoader.loadImage(getHolder(h).post_image, mCursor.getString(mCursor.getColumnIndex(Tables.PostSimple_featured_image_COLUMN)));
    }

    private String getString(String cn)
    {
        return StringEscapeUtils.unescapeHtml4(mCursor.getString(mCursor.getColumnIndex(cn)));
    }

    MainRecyclerHolder getHolder(RecyclerView.ViewHolder holder)
    {
        return (MainRecyclerHolder) holder;
    }
}