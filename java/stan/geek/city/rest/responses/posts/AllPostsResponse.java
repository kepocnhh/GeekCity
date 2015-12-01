package stan.geek.city.rest.responses.posts;

import java.util.List;

import stan.geek.city.rest.responses.GeekResponse;
import stan.geek.city.units.Post;

public class AllPostsResponse
    extends GeekResponse
{
    public AllPostsResponse(List<Post> ps)
    {
        posts = ps;
    }

    public List<Post> posts;
}