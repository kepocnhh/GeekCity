package stan.geek.city.ui.activities;

import android.util.Log;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import stan.geek.city.GeekApp;
import stan.geek.city.R;
import stan.geek.city.listeners.fragments.main.IMainFragmentClick;
import stan.geek.city.rest.requests.posts.GetAllPosts;
import stan.geek.city.rest.responses.GeekResponse;
import stan.geek.city.rest.responses.posts.AllPostsResponse;
import stan.geek.city.ui.fragments.main.MainFragment;

public class Main
        extends StanActivity
    implements IMainFragmentClick
{
    //_______________VIEWS

    //_______________FIELDS

    public Main()
    {
        super(R.layout.main, R.id.main_frame);
    }

    @Override
    protected void initFragments()
    {
        addFragment(MainFragment.newInstance());
    }

    @Override
    protected void initViews()
    {
    }

    @Override
    protected void init()
    {
//        GetAllPosts request = new GetAllPosts(this);
//        GeekApp.spiceManager.execute(request, new RequestListener<GeekResponse>()
//        {
//            @Override
//            public void onRequestFailure(SpiceException spiceException)
//            {
//                Toast.makeText(getApplicationContext(), spiceException.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onRequestSuccess(GeekResponse geekResponse)
//            {
//                AllPostsResponse allPostsResponse = (AllPostsResponse) geekResponse;
//                Log.e("GetAllPosts", "count = " + allPostsResponse.posts.length);
//            }
//        });
    }
}