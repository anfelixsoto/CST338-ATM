import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ATMTest {
    private ATM atm;
    HashMap<String, Integer> values = new HashMap<>();
    HashMap<String, String> values2 = new HashMap<>();
    HashMap<String, Double> funds = new HashMap<>();

    @BeforeEach
    void setUp() {
        values.put("atmID",100);
        values2.put("bankName","OtterUnion");
        values2.put("atmLocation","BIT");
        funds.put("funds",100.00);

        atm = new ATM(values.get("atmID"),values2.get("bankName"),values2.get("atmLocation"),funds.get("funds"));
    }

    @AfterEach
    void tearDown() {
        atm = null;
        values = null;
        values2 = null;
        funds = null;
    }

    @Test
    void getATMId() {
        assertEquals(values.get("atmID"),atm.getATMId());
    }

    @Test
    void getBankName() {
        assertEquals(values2.get("bankName"),atm.getBankName());
    }

    @Test
    void getATMLocation() {
        assertEquals(values2.get("atmLocation"),atm.getATMLocation());
    }

    @Test
    void getFunds() {
        assertEquals(funds.get("funds"),atm.getFunds());
    }
}