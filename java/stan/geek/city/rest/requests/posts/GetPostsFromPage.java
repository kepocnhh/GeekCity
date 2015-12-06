package stan.geek.city.rest.requests.posts;

import android.content.Context;

import stan.geek.city.database.SQliteApi;
import stan.geek.city.rest.requests.GeekRequest;
import stan.geek.city.rest.responses.posts.PostsResponse;

public class GetPostsFromPage
        extends GeekRequest
{
    private int page;
    public GetPostsFromPage(Context context, int p)
    {
        super(context);
        page = p;
    }

    @Override
    public PostsResponse loadDataFromNetwork() throws Exception
    {
        super.loadDataFromNetwork();
        PostsResponse response = new PostsResponse(getService().getPostsFromPage(page));
        SQliteApi.startTransaction();
        for(int i=0; i<response.posts.size(); i++)
        {
            SQliteApi.insertPostSimple(response.posts.get(i).getCV());
        }
        SQliteApi.endTransaction();
        return response;
    }
}