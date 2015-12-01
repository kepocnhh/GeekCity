package stan.geek.city.rest.responses.posts;

import java.util.List;

import stan.geek.city.rest.responses.GeekResponse;
import stan.geek.city.units.Post;

public class PostsResponse
        extends GeekResponse
{
    public PostsResponse(List<Post> ps)
    {
        posts = ps;
    }

    public List<Post> posts;
}