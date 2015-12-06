package stan.geek.city.rest;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import stan.geek.city.units.Post;
import stan.geek.city.units.taxonomy.Category;

public interface GeekAPI
{
    //_____________POSTS_______________//

    @GET("/wp-json/posts")
    List<Post> getPosts();

    @GET("/wp-json/posts")
    List<Post> getPostsFromPage(@Query("page") int page);


    //_____________CATEGORIES_______________//

    @GET("/wp-json/taxonomies/category/terms")
    List<Category> getCategories();
}