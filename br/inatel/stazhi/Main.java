package br.inatel.stazhi;

import br.inatel.stazhi.cli.LoginCLI;
import br.inatel.stazhi.model.usuario.Usuario;

public class Main {
    public static void main(String[] args) {
       
        Usuario usuario = LoginCLI.iniciar();

        if (usuario != null) {
            usuario.showMenu();
        }

    }

}
