import com.fastfood.producao.domain.producao.produto.EnumTipoProduto;
import com.fastfood.producao.domain.producao.produto.Produto;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void shouldCreateProdutoWithAllFields() {
        UUID id = UUID.randomUUID();
        BigDecimal preco = new BigDecimal("29.90");

        Produto produto = new Produto(id, "X-Burger", "Delicious", preco,
                EnumTipoProduto.LANCHE, "img.png");

        assertEquals(id, produto.getId());
        assertEquals("X-Burger", produto.getNome());
        assertEquals("Delicious", produto.getDescricao());
        assertEquals(preco, produto.getPreco());
        assertEquals(EnumTipoProduto.LANCHE, produto.getCategoria());
        assertEquals("img.png", produto.getImagemUrl());
    }

    @Test
    void shouldAllowSetters() {
        Produto produto = new Produto();
        UUID id = UUID.randomUUID();

        produto.setId(id);
        produto.setNome("Coke");
        produto.setDescricao("Soda");
        produto.setPreco(BigDecimal.TEN);
        produto.setCategoria(EnumTipoProduto.BEBIDA);
        produto.setImagemUrl("coke.jpg");

        assertEquals(id, produto.getId());
        assertEquals("Coke", produto.getNome());
        assertEquals("Soda", produto.getDescricao());
        assertEquals(BigDecimal.TEN, produto.getPreco());
        assertEquals(EnumTipoProduto.BEBIDA, produto.getCategoria());
        assertEquals("coke.jpg", produto.getImagemUrl());
    }
}
