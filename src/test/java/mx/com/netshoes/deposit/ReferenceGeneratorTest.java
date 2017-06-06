package mx.com.netshoes.deposit;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ReferenceGeneratorTest {

    @Test
    public void reference_by_EP_order_4020352_and_BANORTE() {
        final Long orderNumber = 4469269L;
        final Date vencDate = date(2017, 03, 26);
        final DepositType depositType = DepositType.BANORTE;
        final BigDecimal amount = new BigDecimal(1119.00D);

        final ReferenceGenerator generator = new ReferenceGenerator();
        final String reference = generator.generate(orderNumber, vencDate, depositType, amount);

        assertEquals("000446926915750247", reference);
    }

    @Test
    public void reference_by_EP_order_4469264_and_BBVA_BANCOMER() {
        final Long orderNumber = 4469264L;
        final Date vencDate = date(2017, 03, 26);
        final DepositType depositType = DepositType.BANCOMER;
        final BigDecimal amount = new BigDecimal(933.00D);

        final ReferenceGenerator generator = new ReferenceGenerator();
        final String reference = generator.generate(orderNumber, vencDate, depositType, amount);

        assertEquals("000446926412031250", reference);
    }

    @Test
    public void reference_by_EP_order_4469255_and_BANAMEX() {
        final Long orderNumber = 4469255L;
        final Date vencDate = date(2017, 03, 26);
        final DepositType depositType = DepositType.BANAMEX;
        final BigDecimal amount = new BigDecimal(1099.00D);

        final ReferenceGenerator generator = new ReferenceGenerator();
        final String reference = generator.generate(orderNumber, vencDate, depositType, amount);

        assertEquals("000446925515753264", reference);
    }

    private Date date(int year, int month, int day) {
        final Calendar c = Calendar.getInstance();
        c.set(year, month -1, day, 0, 0, 0);
        return c.getTime();
    }
}
