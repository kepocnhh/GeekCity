package stan.geek.city.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.List;

import stan.geek.city.listeners.adapters.IGeekAdapterListener;
import stan.geek.city.ui.holders.adapters.GeekHolder;

public abstract class GeekAdapter
        extends SimpleAdapter
{
    private Activity activity;
    protected IGeekAdapterListener listener;
    private LayoutInflater inflater;
    protected List data;
    protected int resourceID;

    public GeekAdapter(Activity context,
                       List d,
                       IGeekAdapterListener l, int id)
    {
        super(context, d, id, new String[]{}, new int[]{});
        resourceID = id;
        activity = context;
        listener = l;
        data = d;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private GeekHolder init(View vi)
    {
        GeekHolder holder = initItems(vi);
        holder.parent = vi;
        return holder;
    }
    private void realize(GeekHolder holder,final int p)
    {
        holder.parent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pressItem(p);
                listener.pressItem(p);
            }
        });
        realizeItem(holder, p);
    }

    protected abstract void pressItem(int p);
    protected abstract GeekHolder initItems(View vi);
    protected abstract void realizeItem(GeekHolder holder, int p);

    @Override
    public View getView(int p, View convertView, ViewGroup parent)
    {
        GeekHolder holder;
        if (convertView == null)
        {
            convertView = inflater.inflate(resourceID, parent, false);
            holder = init(convertView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (GeekHolder) convertView.getTag();
        }
        realize(holder, p);
        return convertView;
    }
}