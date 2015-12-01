package stan.geek.city.helpers;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import stan.geek.city.GeekApp;

public class PicturesLoader
{
    private static Picasso picasso;
    private static Context context;
    private static PicturesLoader instance;

    private PicturesLoader()
    {
        picasso = new Picasso.Builder(context).downloader(new OkHttpDownloader(new OkHttpClient())).build();
    }
    public static PicturesLoader getInstance(Context app_context)
    {
        context = app_context;
        if(instance == null)
        {
            instance = new PicturesLoader();
        }
        return instance;
    }
    public Picasso getPicasso()
    {
        return picasso;
    }

    public static void loadImage(ImageView iv, String url)
    {
        getInstance(GeekApp.app_context)
                .getPicasso()
                .with(GeekApp.app_context)
                .load(url)
                .into(iv);
    }
}