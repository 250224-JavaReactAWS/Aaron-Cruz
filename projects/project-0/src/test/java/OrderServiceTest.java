import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    @Before
    public void setup() {
        mockedOrderDAOImpl = Mockito.mock(OrderDAOImpl.class);
        OrderService orderService = new OrderService(mockedOrderDAOImpl);
    }

    @Test
    public void getOrderByUserIdShouldReturnAListOfOrders() {
        List<Order> mockedOrderList = new ArrayList<>();
        mockedOrderList.add(new Order(1, 30.01, Status.PENDING));
        mockedOrderList.add(new Order(1, 30.02, Status.PENDING));
        mockedOrderList.add(new Order(1, 30.03, Status.PENDING));
        when(mockerOrderDAOImpl.getOrdersByUserId(1)).thenReturn(mockedOrderList);

        List<Order> returnedOrderList = orderService.getOrdersForUser();

        assertFalse(orderList.isEmpty());
        assertEquals(3, returnedOrderList.size());
    }
}
