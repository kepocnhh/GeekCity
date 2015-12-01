package stan.geek.city;

import android.app.Application;
import android.content.Context;

import stan.geek.city.rest.GeekSpiceManager;

public class GeekApp
        extends Application
{
    public static GeekSpiceManager spiceManager = new GeekSpiceManager();
    public static Context app_context;

    @Override
    public void onCreate()
    {
        super.onCreate();
        app_context = getApplicationContext();
    }
}