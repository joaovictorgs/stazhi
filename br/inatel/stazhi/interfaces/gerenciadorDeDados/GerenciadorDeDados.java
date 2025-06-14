package br.inatel.stazhi.interfaces.gerenciadorDeDados;

public interface GerenciadorDeDados<T> {
  void criar(T obj);
  T carregar(int id); 
  void atualizar(T obj);
  void deletar(int id);
}