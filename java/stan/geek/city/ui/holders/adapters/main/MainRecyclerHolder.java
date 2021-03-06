package stan.geek.city.ui.holders.adapters.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import stan.geek.city.R;

public class MainRecyclerHolder
        extends RecyclerView.ViewHolder
{
    public ImageView post_image;
    public TextView post_title;
    public TextView post_excerpt;
//    public FrameLayout post_excerpt;

    public MainRecyclerHolder(View v)
    {
        super(v);
        post_image = (ImageView)v.findViewById(R.id.post_image);
        post_title = (TextView)v.findViewById(R.id.post_title);
        post_excerpt = (TextView)v.findViewById(R.id.post_excerpt);
//        post_excerpt = (FrameLayout)v.findViewById(R.id.post_excerpt);
    }
}