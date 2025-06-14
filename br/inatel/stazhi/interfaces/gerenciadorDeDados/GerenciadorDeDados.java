package br.inatel.stazhi.interfaces.gerenciadorDeDados;

public interface GerenciadorDeDados<T> {
    void criar(T obj);
    void atualizar(T obj);
}
