package stan.geek.city.rest.requests.posts;

import android.content.Context;

import stan.geek.city.rest.requests.GeekRequest;
import stan.geek.city.rest.responses.posts.AllPostsResponse;

public class GetAllPosts
        extends GeekRequest
{
    public GetAllPosts(Context context)
    {
        super(context);
    }

    @Override
    public AllPostsResponse loadDataFromNetwork() throws Exception
    {
        super.loadDataFromNetwork();
        AllPostsResponse response = new AllPostsResponse(getService().getPosts());
        return response;
    }
}