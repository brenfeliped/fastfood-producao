import com.fastfood.producao.domain.producao.model.ProductionStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductionStatusTest {

    @Test
    void shouldContainAllStatuses() {
        assertNotNull(ProductionStatus.RECEIVED);
        assertNotNull(ProductionStatus.IN_PREPARATION);
        assertNotNull(ProductionStatus.READY);
        assertNotNull(ProductionStatus.FINISHED);
    }
}
