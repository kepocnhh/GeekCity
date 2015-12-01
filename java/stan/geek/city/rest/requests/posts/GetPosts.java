package stan.geek.city.rest.requests.posts;

import android.content.Context;

import stan.geek.city.rest.requests.GeekRequest;
import stan.geek.city.rest.responses.posts.PostsResponse;

public class GetPosts
        extends GeekRequest
{
    private int page;
    public GetPosts(Context context, int p)
    {
        super(context);
        page = p;
    }

    @Override
    public PostsResponse loadDataFromNetwork() throws Exception
    {
        super.loadDataFromNetwork();
        PostsResponse response = new PostsResponse(getService().getPostsFromPage(page));
        return response;
    }
}