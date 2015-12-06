package stan.geek.city.ui.holders.adapters.category;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import stan.geek.city.R;

public class CategoryRecyclerHolder
        extends RecyclerView.ViewHolder
{
    public TextView category_parent;
    public TextView category_name;

    public CategoryRecyclerHolder(View v)
    {
        super(v);
        category_parent = (TextView)v.findViewById(R.id.category_parent);
        category_name = (TextView)v.findViewById(R.id.category_name);
    }
}