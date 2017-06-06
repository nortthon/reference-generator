package mx.com.netshoes.deposit;

import lombok.Getter;

/**
 * Created by lucas.augusto on 23/03/17.
 */
@Getter
public enum DepositType {
    HSBC_MEX("HSBC", 2013, 3202),
    SANTANDER_MEX("SANTANDER", 2009, 0224),
    BANAMEX("BANAMEX", 2013, 394501),
    BANORTE("BANORTE", 2013, 112275),
    BANCOMER("BBVA BANCOMER", 2014, 1120123);

    private String name;

    private int baseYear;

    private int code;

    DepositType(final String name, int baseYear, int code) {
        this.name = name;
        this.baseYear = baseYear;
        this.code = code;
    }
}
