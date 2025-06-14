package br.inatel.stazhi.interfaces.autenticavel;

public interface Autenticavel {
  
    void login(String email,String senha);
    void cadastro(String email,String senha);

}
