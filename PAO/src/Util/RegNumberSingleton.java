package Util;

public class RegNumberSingleton {

    private static RegNumberSingleton INSTANCE;
    private Integer code = 0;

    private RegNumberSingleton() {
    }

    public static RegNumberSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RegNumberSingleton();
        }
        return INSTANCE;
    }

    public Integer getNextCode() {
        this.code = this.code + 1;
        return this.code;
    }
}
