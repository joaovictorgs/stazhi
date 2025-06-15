package br.inatel.stazhi.casodeuso.empresa;

import br.inatel.stazhi.execoes.UsuarioJaExisteException.UsuarioJaExisteException;
import br.inatel.stazhi.model.empresa.Empresa;
import br.inatel.stazhi.repositorio.empresaRepositorio.EmpresaRepositorio;
import br.inatel.stazhi.util.geradorDeId.GeradorDeId;

public class CriarEmpresa {
  public void executar(String nome, String cnpj, String senha, String email, String setor) throws UsuarioJaExisteException {
      var repo = new EmpresaRepositorio();

      if (repo.existePorCNPJ(cnpj)) {
          throw new UsuarioJaExisteException("Já existe uma empresa com esse CNPJ.");
      }

      int id = GeradorDeId.gerarId();

      Empresa novaEmpresa = new Empresa(id, nome, senha, email, cnpj, setor);
      repo.criar(novaEmpresa);
  }
}
