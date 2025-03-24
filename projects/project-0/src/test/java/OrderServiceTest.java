import com.ecommerce.models.Order;
import com.ecommerce.models.Status;
import com.ecommerce.repos.OrderDAOImpl;
import com.ecommerce.services.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    private OrderDAOImpl mockedOrderDAOImpl;

    private OrderService orderService;

    @Before
    public void setup() {
        mockedOrderDAOImpl = Mockito.mock(OrderDAOImpl.class);
        orderService = new OrderService(mockedOrderDAOImpl);
    }

    @Test
    public void getOrderByUserIdShouldReturnAListOfOrders() {
        List<Order> mockedOrderList = new ArrayList<>();
        mockedOrderList.add(new Order(1, 1, 30.01f, Status.PENDING, new Date()));
        mockedOrderList.add(new Order(2, 1, 30.02f, Status.PENDING, new Date()));
        mockedOrderList.add(new Order(3, 1, 30.03f, Status.PENDING, new Date()));
        when(mockedOrderDAOImpl.getOrdersByUserId(1)).thenReturn(mockedOrderList);

        List<Order> returnedOrderList = orderService.getOrdersForUser(1);

        assertFalse(returnedOrderList.isEmpty());
        assertEquals(3, returnedOrderList.size());
    }
}
