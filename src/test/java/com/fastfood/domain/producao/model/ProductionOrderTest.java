import com.fastfood.producao.domain.producao.model.ProductionOrder;
import com.fastfood.producao.domain.producao.model.ProductionStatus;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.*;

class ProductionOrderTest {

    @Test
    void shouldCreateNewProductionOrder() {
        Instant now = Instant.now();

        ProductionOrder order = new ProductionOrder("123", "999",
                ProductionStatus.RECEIVED, now, now);

        assertEquals("123", order.getId());
        assertEquals("999", order.getOrderId());
        assertEquals(ProductionStatus.RECEIVED, order.getStatus());
        assertNotNull(order.getCreatedAt());
        assertNotNull(order.getUpdatedAt());
    }

    @Test
    void shouldCreateUsingFactoryMethod() {
        ProductionOrder order = ProductionOrder.newFromOrderId("ABC-ORDER");

        assertNull(order.getId());
        assertEquals("ABC-ORDER", order.getOrderId());
        assertEquals(ProductionStatus.RECEIVED, order.getStatus());
        assertNotNull(order.getCreatedAt());
        assertNotNull(order.getUpdatedAt());
    }
}
