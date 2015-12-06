package stan.geek.city.helpers;

import android.net.Uri;

public class UriHelper
{
    public static final String AUTHORITY = "stan.geek.city";

    static final String POSTS_PATH = "posts";

    public static Uri getPostsUri()
    {
        return Uri.parse("content://" + AUTHORITY + "/" + POSTS_PATH);
    }
}