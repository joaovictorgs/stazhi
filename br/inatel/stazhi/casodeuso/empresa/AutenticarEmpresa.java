package br.inatel.stazhi.casodeuso.empresa;

import br.inatel.stazhi.execoes.dadosInvalidosException.DadosInvalidosException;
import br.inatel.stazhi.model.empresa.Empresa;
import br.inatel.stazhi.repositorio.empresaRepositorio.EmpresaRepositorio;

public class AutenticarEmpresa {

    private final EmpresaRepositorio empresaRepositorio;

    public AutenticarEmpresa() {
        this.empresaRepositorio = new EmpresaRepositorio();
    }

    public Empresa executar(String email, String senha) throws DadosInvalidosException {
        Empresa empresa = empresaRepositorio.buscarPorEmail(email);

        if (empresa == null || !empresa.getEmail().equals(email)) {
            throw new DadosInvalidosException( "Senha inválida ou empresa não encontrada.");
        }

        return empresa;
    }
}
