package Util;

public class RegNumberGenerator {
    private Integer code = 0;

    public Integer getNextCode() {
        this.code = this.code + 1;
        return this.code;
    }
}

