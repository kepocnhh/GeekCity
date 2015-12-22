package stan.geek.city.rest.requests.posts;

import android.content.Context;

import stan.geek.city.database.SQliteApi;
import stan.geek.city.rest.requests.GeekRequest;
import stan.geek.city.rest.responses.posts.PostsResponse;

public class GetPostsFromCategory
        extends GeekRequest
{
    private String category;

    public GetPostsFromCategory(Context context, String c)
    {
        super(context);
        category = c;
    }

    @Override
    public PostsResponse loadDataFromNetwork() throws Exception
    {
        super.loadDataFromNetwork();
        PostsResponse response = new PostsResponse(getService().getPostsFromCategory(category));
        SQliteApi.startTransaction();
        for(int i=0; i<response.posts.size(); i++)
        {
            SQliteApi.insertPostSimple(response.posts.get(i).getCV());
        }
        SQliteApi.endTransaction();
        return response;
    }
}