package stan.geek.city.ui.adapters.cactegory;

import android.content.Context;
import android.provider.BaseColumns;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import stan.geek.city.R;
import stan.geek.city.database.Tables;
import stan.geek.city.helpers.SQliteHelper;
import stan.geek.city.listeners.adapters.ICategoryListener;
import stan.geek.city.ui.holders.adapters.GeekRecyclerAdapter;
import stan.geek.city.ui.holders.adapters.category.CategoryRecyclerHolder;
import stan.geek.city.units.taxonomy.Category;

public class CategoryRecyclerAdapter
        extends GeekRecyclerAdapter
{
    ICategoryListener listener;

    public CategoryRecyclerAdapter(Context context, ICategoryListener l)
    {
        super(context, R.layout.category);
        listener = l;
    }

    @Override
    protected RecyclerView.ViewHolder initHolder(View v)
    {
        return new CategoryRecyclerHolder(v);
    }

    @Override
    protected void initView(RecyclerView.ViewHolder h, int i)
    {
        getHolder(h).category_name.setText(getString(Tables.Category_name_COLUMN));
        int parent_id = mCursor.getInt(mCursor.getColumnIndex(Tables.Category_parent_id_COLUMN));
        if(parent_id > 0)
        {
            Category unit = SQliteHelper.tryGetCategoryFromId(parent_id);
            if(unit != null)
            {
                getHolder(h).category_parent.setText(unit.name);
            }
        }
        final int id = mCursor.getInt(mCursor.getColumnIndex(BaseColumns._ID));
        h.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.pressCategory(id);
            }
        });
    }

    CategoryRecyclerHolder getHolder(RecyclerView.ViewHolder holder)
    {
        return (CategoryRecyclerHolder) holder;
    }
}