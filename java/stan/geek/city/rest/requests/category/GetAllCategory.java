package stan.geek.city.rest.requests.category;

import android.content.Context;

import stan.geek.city.database.SQliteApi;
import stan.geek.city.rest.requests.GeekRequest;
import stan.geek.city.rest.responses.category.AllCategoryResponse;

public class GetAllCategory
        extends GeekRequest
{
    public GetAllCategory(Context context)
    {
        super(context);
    }

    @Override
    public AllCategoryResponse loadDataFromNetwork() throws Exception
    {
        super.loadDataFromNetwork();
        AllCategoryResponse response = new AllCategoryResponse(getService().getCategories());
        SQliteApi.startTransaction();
        for(int i=0; i<response.categories.size(); i++)
        {
            SQliteApi.insertCategory(response.categories.get(i).getCV());
        }
        SQliteApi.endTransaction();
        return response;
    }
}