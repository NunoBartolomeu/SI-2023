package exercises;

import model.DataScope;
import org.junit.Test;

import static org.junit.Assert.*;

public class Exercise1_BTest {

    @Test
    public void associarCracha() throws Exception {
        try (DataScope ds = new DataScope()) {
            String s = Exercise1_B.associarCracha(4, "LOL1234567", "begin");
            assertEquals("Cracha Obtido!", s);
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}