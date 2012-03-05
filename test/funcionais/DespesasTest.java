package funcionais;
import java.util.List;

import org.junit.*;
import org.junit.Before;

import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import models.*;

public class DespesasTest extends FunctionalTest {

    @Before
    public void setUp() {
        Fixtures.deleteAllModels();
        Fixtures.loadModels("data.yml");
    }
    
    @Test
    public void testPaginaListarExibeTodasAsDespesas() {
        Response response = GET("/despesas/listar");
        assertIsOk(response);
        assertContentType("text/html", response);
        List<Despesa> despesas = (List<Despesa>) renderArgs("despesas");
        assertEquals(3, despesas.size());
    }
    
}