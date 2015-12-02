package stan.geek.city.ui.fragments.main;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import stan.geek.city.GeekApp;
import stan.geek.city.R;
import stan.geek.city.database.SQliteApi;
import stan.geek.city.listeners.adapters.main.IMainRecyclerAdapterListener;
import stan.geek.city.listeners.fragments.main.IMainFragmentClick;
import stan.geek.city.rest.requests.posts.GetPosts;
import stan.geek.city.rest.responses.GeekResponse;
import stan.geek.city.rest.responses.posts.PostsResponse;
import stan.geek.city.ui.adapters.main.MainRecyclerAdapter;
import stan.geek.city.ui.fragments.StanFragment;

public class MainFragment
        extends StanFragment
{
    //___________________VIEWS
    RecyclerView main_recycler;

    private SwipyRefreshLayout swipyrefreshlayout;
    private MainRecyclerAdapter adapter;
    private int page = 0;
    private int lastPostID = -1;

    static public MainFragment newInstance()
    {
        MainFragment fragment = new MainFragment();
        Bundle args = fragment.getArguments();
        //        fragment.setArguments(args);
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
        main_recycler = (RecyclerView) v.findViewById(R.id.main_recycler);
        swipyrefreshlayout = (SwipyRefreshLayout) v.findViewById(R.id.swipyrefreshlayout);
        initList();
        init();
    }

    private void init()
    {
        getPosts();
    }

    private void getPosts()
    {
        GetPosts request = new GetPosts(getActivity(), page + 1);
        GeekApp.spiceManager.execute(request, new RequestListener<GeekResponse>()
        {

            @Override
            public void onRequestFailure(SpiceException spiceException)
            {
                swipyrefreshlayout.setRefreshing(false);
                loadNewPosts();
//                Toast.makeText(getActivity(), spiceException.getMessage(),
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestSuccess(GeekResponse geekResponse)
            {
                swipyrefreshlayout.setRefreshing(false);
                PostsResponse postsResponse = (PostsResponse) geekResponse;
                if (postsResponse.posts != null && postsResponse.posts.size() > 0)
                {
//                    lastPostID = postsResponse.posts.get(postsResponse.posts.size()-1).ID;
//                    adapter.swapList(postsResponse.posts);
                    loadNewPosts();
//                    adapter.swapCursor(SQliteApi.getPostSimpleFromPage(page + 1));
//                    page++;
                }
            }
        });
    }

    private void initList()
    {
        adapter = new MainRecyclerAdapter(getActivity(), new IMainRecyclerAdapterListener()
        {
            @Override
            public void pressItem(int pos)
            {

            }
        });
        main_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        main_recycler.setAdapter(adapter);
        swipyrefreshlayout.setColorSchemeResources(R.color.red, R.color.black, R.color.white);
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
        adapter.swapCursor(SQliteApi.getPostSimpleFromPage(page + 1));
        page++;
    }
    private void refreshTop()
    {
        page = 0;
//        adapter.clearList();
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