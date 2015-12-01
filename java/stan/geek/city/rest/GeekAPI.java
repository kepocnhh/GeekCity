package stan.geek.city.rest;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import stan.geek.city.units.Post;

public interface GeekAPI
{
    //_____________POSTS_______________//

    @GET("/wp-json/posts")
    List<Post> getPosts();

    @GET("/wp-json/posts")
    List<Post> getPostsFromPage(@Query("page") int page);
}