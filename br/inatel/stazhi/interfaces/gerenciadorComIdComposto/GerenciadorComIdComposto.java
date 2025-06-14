package br.inatel.stazhi.interfaces.gerenciadorComIdComposto;

import br.inatel.stazhi.interfaces.gerenciadorDeDados.GerenciadorDeDados;

public interface GerenciadorComIdComposto<T, K> extends GerenciadorDeDados<T> {
    T carregar(K chave);
    void deletar(K chave);
}