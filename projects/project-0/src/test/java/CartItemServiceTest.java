import com.ecommerce.models.CartItem;
import com.ecommerce.repos.CartItemDAOImpl;
import com.ecommerce.services.CartItemService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CartItemServiceTest {

    private CartItemDAOImpl mockCartItemDAOImpl;

    private CartItemService cartItemService;

    @Before
    public void setup() {
        mockCartItemDAOImpl = Mockito.mock(CartItemDAOImpl.class);
        cartItemService = new CartItemService(mockCartItemDAOImpl);
    }

    @Test
    public void addCartItemsShouldReturnCurrentCartList() {
        List<CartItem> cartProductList = new ArrayList<>();
        cartProductList.add(new CartItem(1, 1, 1, 3));
        cartProductList.add(new CartItem(1, 1, 1, 7));
        cartProductList.add(new CartItem(1, 1, 1, 9));
        CartItem mockCartItem = new CartItem(1, 1, 1, 3);
        when(mockCartItemDAOImpl.create(any(CartItem.class))).thenReturn(mockCartItem);



    }
}
