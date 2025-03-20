import org.junit.jupiter.api.Test;

import com.example.Mapa;

public class TabelaHashTests {

  @Test
  public void testNovaTabelaHash() {
    Mapa m = new TabelaHash();
    boolean contemChave = m.contemChave(1);
    int tamanho = m.tamanho();
    boolean contemValor = m.contemValor(1);
    
  }
}
