package stan.geek.city.rest.responses.category;

import java.util.List;

import stan.geek.city.rest.responses.GeekResponse;
import stan.geek.city.units.taxonomy.Category;

public class AllCategoryResponse
        extends GeekResponse
{
    public AllCategoryResponse(List<Category> cs)
    {
        categories = cs;
    }

    public List<Category> categories;
}