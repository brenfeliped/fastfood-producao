import com.fastfood.producao.domain.producao.produto.EnumTipoProduto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnumTipoProdutoTest {

    @Test
    void shouldReturnCorrectIds() {
        assertEquals(1, EnumTipoProduto.LANCHE.getId());
        assertEquals(2, EnumTipoProduto.ACOMPANHAMENTO.getId());
        assertEquals(3, EnumTipoProduto.BEBIDA.getId());
        assertEquals(4, EnumTipoProduto.SOBREMESA.getId());
    }

    @Test
    void shouldReturnCorrectNames() {
        assertEquals("Lanche", EnumTipoProduto.LANCHE.getCategoria());
        assertEquals("Bebida", EnumTipoProduto.BEBIDA.getCategoria());
    }
}
