package models;
import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import play.test.UnitTest;

public class DespesaTest extends UnitTest {

    @Test
    public void adicionaDespesaComDuasTagsNaoCadastradas() {
        Despesa despesa = new Despesa(new Date(), "Estacionamento", new BigDecimal("-10"));
        despesa.setTags("carro, passeio");
        despesa.save();
        
        Despesa estacionamento = Despesa.find("byDescricao", "Estacionamento").first();
        
        assertEquals("carro", estacionamento.tags.get(0).nome);
        assertEquals("passeio", estacionamento.tags.get(1).nome);
        
        assertEquals(2, Tag.findAll().size());
    }
    
    @Test
    public void adicionaDespesaComTagContendoCaracteresEspeciais() {
        Despesa despesa = new Despesa(new Date(), "Passagem aerea", new BigDecimal("-500"));
        despesa.setTags("viagem-&*{}[]/()_@#");
        despesa.save();
        
        Despesa estacionamento = Despesa.find("byDescricao", "Passagem aerea").first();
        
        assertEquals("viagem", estacionamento.tags.get(0).nome);
    }
}
