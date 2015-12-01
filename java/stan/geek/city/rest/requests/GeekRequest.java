package stan.geek.city.rest.requests;

import android.content.Context;
import android.util.Log;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import stan.geek.city.rest.GeekAPI;
import stan.geek.city.rest.policies.NotRetryPolicy;
import stan.geek.city.rest.responses.GeekResponse;

public class GeekRequest
        extends RetrofitSpiceRequest<GeekResponse, GeekAPI>
{
    protected Context mContext;

    public GeekRequest(Context context)
    {
        super(GeekResponse.class, GeekAPI.class);
        this.setRetryPolicy(NotRetryPolicy.factory());
        mContext = context;
    }

    @Override
    public GeekResponse loadDataFromNetwork() throws Exception
    {
        Log.e("Exec", "****************************************Request*************************************************");
        return null;
    }
}