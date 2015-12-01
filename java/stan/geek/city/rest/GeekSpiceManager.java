package stan.geek.city.rest;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.request.SpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;

public class GeekSpiceManager
        extends SpiceManager
{

    public GeekSpiceManager()
    {
        super(GeekSpiceService.class);
    }

    @Override
    public <T> void execute(final SpiceRequest<T> request, final RequestListener<T> requestListener)
    {
        execute(request, null, DurationInMillis.ONE_SECOND, requestListener);
    }
}