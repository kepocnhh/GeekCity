package stan.geek.city.ui.fragments.main;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import stan.geek.city.GeekApp;
import stan.geek.city.R;
import stan.geek.city.database.SQliteApi;
import stan.geek.city.helpers.SQliteHelper;
import stan.geek.city.listeners.fragments.main.IMainFragmentClick;
import stan.geek.city.rest.requests.posts.GetPosts;
import stan.geek.city.rest.responses.GeekResponse;
import stan.geek.city.rest.responses.posts.PostsResponse;
import stan.geek.city.ui.adapters.main.MainRecyclerAdapter;
import stan.geek.city.ui.fragments.StanFragment;
import stan.geek.city.units.taxonomy.Category;

public class MainFragment
        extends StanFragment
{
    private static final String CATEGORY_id_TAG = "category_id";

    //___________________VIEWS
    RecyclerView main_recycler;
    private SwipyRefreshLayout swipyrefreshlayout;
    View main_progress;

    //_______________FIELDS
    private Category category;
    private MainRecyclerAdapter adapter;
    private int page = 0;
    private int postsCount = -1;

    static public MainFragment newInstance()
    {
        return new MainFragment();
    }

    static public MainFragment newInstance(int category_id)
    {
        MainFragment fragment = new MainFragment();
        Bundle args = fragment.getArguments();
        args.putInt(CATEGORY_id_TAG, category_id);
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment()
    {
        super(R.layout.main_fragment, R.string.MainFragment);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        main_progress = v.findViewById(R.id.main_progress);
        main_progress.setVisibility(View.VISIBLE);
        main_recycler = (RecyclerView) v.findViewById(R.id.main_recycler);
        swipyrefreshlayout = (SwipyRefreshLayout) v.findViewById(R.id.swipyrefreshlayout);
        initList();
        init();
    }

    private void init()
    {
        category = null;
        int category_id = getArguments().getInt(CATEGORY_id_TAG, -1);
        if(category_id > 0)
        {
            Category unit = SQliteHelper.tryGetCategoryFromId(category_id);
            if(unit != null)
            {
                category = unit;
            }
        }
        getPosts(new RequestListener<GeekResponse>()
        {

            @Override
            public void onRequestFailure(SpiceException spiceException)
            {
                swipyrefreshlayout.setRefreshing(false);
                loadNewPosts();
                main_progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onRequestSuccess(GeekResponse geekResponse)
            {
                swipyrefreshlayout.setRefreshing(false);
                PostsResponse postsResponse = (PostsResponse) geekResponse;
                if (postsResponse.posts != null && postsResponse.posts.size() > 0)
                {
                    loadNewPosts();
                }
                main_progress.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void getPosts(RequestListener<GeekResponse> rl)
    {
        String category_slug = null;
        if(category != null)
        {
            category_slug = category.slug;
        }
        GetPosts request = new GetPosts(getActivity(), category_slug, page + 1);
        GeekApp.spiceManager.execute(request, rl);
    }
    private void getPosts()
    {
        getPosts(new RequestListener<GeekResponse>()
        {

            @Override
            public void onRequestFailure(SpiceException spiceException)
            {
                swipyrefreshlayout.setRefreshing(false);
                loadNewPosts();
            }

            @Override
            public void onRequestSuccess(GeekResponse geekResponse)
            {
                swipyrefreshlayout.setRefreshing(false);
                PostsResponse postsResponse = (PostsResponse) geekResponse;
                if(postsResponse.posts != null && postsResponse.posts.size() > 0)
                {
                    loadNewPosts();
                }
            }
        });
    }

    private void initList()
    {
        adapter = new MainRecyclerAdapter(getActivity());
        main_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        main_recycler.setAdapter(adapter);
        swipyrefreshlayout.setColorSchemeResources(R.color.red, R.color.black, R.color.red);
        swipyrefreshlayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction)
            {
                if (direction == SwipyRefreshLayoutDirection.TOP)
                {
                    refreshTop();
                }
                else
                {
                    refreshBot();
                }
            }
        });
    }

    private void loadNewPosts()
    {
        Cursor c = null;
        if(category == null)
        {
            c = SQliteApi.getPostSimpleFromPage(page + 1);
        }
        else
        {
            c = SQliteApi.getPostSimpleFromCategoryPage(category.ID, page + 1);
        }
        adapter.swapCursor(c);
        int pc = c.getCount();
        if (pc != postsCount || page == 0)
        {
            postsCount = pc;
            page++;
        }
        Log.e("loadNewPosts", "postsCount = " + postsCount);
    }

    private void refreshTop()
    {
        page = 0;
        getPosts();
    }

    private void refreshBot()
    {
        getPosts();
    }

    private IMainFragmentClick getClickListener()
    {
        return (IMainFragmentClick) clickListener;
    }
}