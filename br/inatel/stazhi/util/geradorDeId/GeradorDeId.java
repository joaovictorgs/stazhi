package br.inatel.stazhi.util.geradorDeId;

import java.util.Random;
public class GeradorDeId {
    private static final Random random = new Random();

    public static int gerarId() {
        return 100000 + random.nextInt(900000);
    }
}