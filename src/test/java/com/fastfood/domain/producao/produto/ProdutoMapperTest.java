import com.fastfood.producao.domain.producao.produto.EnumTipoProduto;
import com.fastfood.producao.domain.producao.produto.Produto;
import com.fastfood.producao.domain.producao.produto.dto.ProdutoDTO;
import com.fastfood.producao.domain.producao.produto.dto.ProdutoMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoMapperTest {

    @Test
    void shouldMapDtoToDomain() {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(UUID.randomUUID());
        dto.setNome("Fries");
        dto.setDescricao("Potato");
        dto.setPreco(new BigDecimal("9.50"));
        dto.setCategoria(EnumTipoProduto.ACOMPANHAMENTO);
        dto.setImagemUrl("fries.png");

        Produto domain = ProdutoMapper.toDomain(dto);

        assertEquals(dto.getId(), domain.getId());
        assertEquals(dto.getNome(), domain.getNome());
        assertEquals(dto.getDescricao(), domain.getDescricao());
        assertEquals(dto.getPreco(), domain.getPreco());
        assertEquals(dto.getCategoria(), domain.getCategoria());
        assertEquals(dto.getImagemUrl(), domain.getImagemUrl());
    }

    @Test
    void shouldMapDomainToDto() {
        Produto produto = new Produto();
        produto.setId(UUID.randomUUID());
        produto.setNome("Ice Cream");
        produto.setDescricao("Sweet");
        produto.setPreco(new BigDecimal("15.00"));
        produto.setCategoria(EnumTipoProduto.SOBREMESA);
        produto.setImagemUrl("ice.png");

        ProdutoDTO dto = ProdutoMapper.toDTO(produto);

        assertEquals(produto.getId(), dto.getId());
        assertEquals(produto.getNome(), dto.getNome());
        assertEquals(produto.getDescricao(), dto.getDescricao());
        assertEquals(produto.getPreco(), dto.getPreco());
        assertEquals(produto.getCategoria(), dto.getCategoria());
        assertEquals(produto.getImagemUrl(), dto.getImagemUrl());
    }
}
