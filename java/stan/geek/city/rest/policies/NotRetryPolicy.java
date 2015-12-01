package stan.geek.city.rest.policies;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.retry.RetryPolicy;

public class NotRetryPolicy
        implements RetryPolicy
{
    public static NotRetryPolicy policy;

    public static NotRetryPolicy factory()
    {
        if (policy==null) {
            policy = new NotRetryPolicy();
        }
        return policy;
    }

    @Override
    public int getRetryCount()
    {
        return 0;
    }
    @Override
    public void retry(SpiceException e)
    {

    }
    @Override
    public long getDelayBeforeRetry()
    {
        return 0;
    }
}