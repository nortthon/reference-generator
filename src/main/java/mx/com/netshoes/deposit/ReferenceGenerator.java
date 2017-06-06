package mx.com.netshoes.deposit;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static mx.com.netshoes.deposit.DepositType.BANAMEX;

public class ReferenceGenerator {

    public String generate(final Long orderNumber, final Date vencDate, final DepositType bank, final BigDecimal amount) {

        final StringBuilder inputLine = new StringBuilder();
        inputLine.append(String.format("%010d", orderNumber));
        inputLine.append(String.format("%04d", getFechaVenc(vencDate, bank)));
        inputLine.append(getMonto(amount.doubleValue()));
        inputLine.append(2);
        inputLine.append(getLineaCaptura(inputLine.toString(), bank));

        return inputLine.toString();
    }

    private int getFechaVenc(Date vencDate, DepositType bank) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(vencDate);

        int yy = (cal.get(Calendar.YEAR) - bank.getBaseYear()) * 372;
        int mm = cal.get(Calendar.MONTH) * 31;
        int dd = cal.get(Calendar.DAY_OF_MONTH) -1;
        return yy + mm + dd;
    }

    private int getMonto(double orderAmount) {
        int amt = (int) (orderAmount * 100);
        final String monto = String.valueOf(amt);

        int p = 7; // 7, 3, 1;
        int sum = 0;
        for (int i = monto.length() -1; i >= 0; i--) {
            int digit = Character.digit(monto.charAt(i), 10);
            sum += (digit * p);

            if (p == 7)
                p = 3;
            else if (p == 3)
                p = 1;
            else
                p = 7;
        }

        return sum % 10;
    }

    private String getLineaCaptura(final String input, final DepositType bank) {

        final String line = BANAMEX.equals(bank) ? bank.getCode()+input : input;

        int sum = 0;
        int p = 11; // 11, 13, 17, 19, 23
        for (int i = line.length() - 1; i >=0 ; i--) {
            sum += Character.digit(line.charAt(i), 10) * p;

            if (p == 11)
                p = 13;
            else if (p == 13)
                p = 17;
            else if (p == 17)
                p = 19;
            else if (p == 19)
                p = 23;
            else
                p = 11;
        }

        return String.format("%02d", (sum % 97) + 1);
    }
}
