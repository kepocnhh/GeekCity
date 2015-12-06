package stan.geek.city.ui.adapters.cactegory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import stan.geek.city.R;
import stan.geek.city.database.Tables;
import stan.geek.city.helpers.SQliteHelper;
import stan.geek.city.ui.holders.adapters.GeekRecyclerAdapter;
import stan.geek.city.ui.holders.adapters.category.CategoryRecyclerHolder;
import stan.geek.city.units.taxonomy.Category;

public class CategoryRecyclerAdapter
        extends GeekRecyclerAdapter
{
    public CategoryRecyclerAdapter(Context context)
    {
        super(context, R.layout.category);
    }

    @Override
    protected RecyclerView.ViewHolder initHolder(View v)
    {
        return new CategoryRecyclerHolder(v);
    }

    @Override
    protected void initView(RecyclerView.ViewHolder h, int i)
    {
        getHolder(h).category_name.setText(
                mCursor.getString(mCursor.getColumnIndex(Tables.Category_name_COLUMN)));
        int parent_id = mCursor.getInt(mCursor.getColumnIndex(Tables.Category_parent_id_COLUMN));
        if(parent_id > 0)
        {
            Category unit = SQliteHelper.tryGetCategoryFromId(parent_id);
            if(unit != null)
            {
                getHolder(h).category_parent.setText(unit.name);
            }
        }
    }

    CategoryRecyclerHolder getHolder(RecyclerView.ViewHolder holder)
    {
        return (CategoryRecyclerHolder) holder;
    }
}