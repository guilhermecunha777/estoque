package produto.estoque.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import produto.estoque.estoque.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {}
