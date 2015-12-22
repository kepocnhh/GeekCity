package stan.geek.city.rest.requests.posts;

import android.content.Context;

import stan.geek.city.database.SQliteApi;
import stan.geek.city.helpers.SQliteHelper;
import stan.geek.city.rest.requests.GeekRequest;
import stan.geek.city.rest.responses.posts.PostsResponse;

public class GetPosts
        extends GeekRequest
{
    private String category;
    private int page;

    public GetPosts(Context context, String c, int p)
    {
        super(context);
        category = c;
        page = p;
    }

    @Override
    public PostsResponse loadDataFromNetwork() throws Exception
    {
        super.loadDataFromNetwork();
        PostsResponse response = new PostsResponse(getService().getPostsFromCategoryPage(category, page));
        SQliteApi.startTransaction();
        SQliteHelper.savePostsSimple(response.posts);
        SQliteApi.endTransaction();
        return response;
    }
}