package br.inatel.stazhi.interfaces.gerenciadorComId;

import br.inatel.stazhi.interfaces.gerenciadorDeDados.GerenciadorDeDados;

public interface GerenciadorComID<T> extends GerenciadorDeDados<T> {
    T carregar(int id);
    void deletar(int id);
}