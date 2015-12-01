package stan.geek.city.rest;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

public class GeekSpiceService
        extends RetrofitGsonSpiceService
{
    public final static String BASE_URL = "http://geekcity.ru/";

    @Override
    public void onCreate()
    {
        super.onCreate();
        addRetrofitInterface(GeekAPI.class);
    }

    @Override
    protected String getServerUrl()
    {
        return BASE_URL;
    }

    @Override
    public int getThreadCount()
    {
        return 10;
    }
}