import com.ecommerce.models.Product;
import com.ecommerce.repos.ProductDAOImpl;
import com.ecommerce.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductDAOImpl mockProductDAOImpl;

    private ProductService productService;

    @Before
    public void setup() {
        mockProductDAOImpl = Mockito.mock(ProductDAOImpl.class);
        productService = new ProductService(mockProductDAOImpl);
    }

    @Test
    public void getAvailableProductsShouldReturnListOfProducts() {
        List<Product> mockProductList = new ArrayList<>();
        mockProductList.add(new Product(1, "Gluten-Free Brownies", "Decadent brownies made without gluten, rich and chocolatey.", 4.99f, 1));
        mockProductList.add(new Product(2, "Buttermilk Pancakes", "Fluffy pancakes with buttermilk flavor.", 3.99f, 2));
        mockProductList.add(new Product(3, "Smart Thermostat", "Wi-Fi enabled thermostat for energy-saving temperature control.", 149.99f, 3));
        when(mockProductDAOImpl.getAvailableProducts()).thenReturn(mockProductList);

        List<Product> returnedProductList = productService.fetchAvailableProducts();

        assertFalse(returnedProductList.isEmpty());
        assertEquals(3, returnedProductList.size());
    }
}
