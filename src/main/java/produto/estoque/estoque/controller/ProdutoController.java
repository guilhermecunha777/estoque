package produto.estoque.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import produto.estoque.estoque.model.Produto;
import produto.estoque.estoque.service.ProdutoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> listar(){
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.buscarPorId(id);
        if (produto.isPresent())
            return ResponseEntity.ok(produto.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        Optional<Produto>  produtoExistente = produtoService.buscarPorId(id);
        if (produtoExistente.isPresent()) {
            Produto p = produtoExistente.get();
            p.setNome(produto.getNome());
            p.setQuantidade(produto.getQuantidade());
            p.setPreco(produto.getPreco());
            produtoService.salvar(p);
            return ResponseEntity.ok(p);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        Optional<Produto>  produtoExistente = produtoService.buscarPorId(id);
        if (produtoExistente.isPresent()){
            produtoService.deletar(id);
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
