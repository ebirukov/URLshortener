package org.interview.jobtask.shortener;

/**
 * Биективное отображение числовое значение в строку и обратно
 * По сути преобразует число из десятичной системы счисления в систему по основанию base и обратно
 * где base это длина алфавита alphabet
 * Для авфавита abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 отображение из десятичной системы
 * в 62-ричную
 * 0  → a
 * 1  → b
 * ...
 * 52 → 0
 * 61 → 9
 * Например, в 62-ричном кода baaac = [1,0,0,0,2] = 1×62^4 + 0×62^3 + 0×62^2 + 61×62^1 + 0×62^0 = 14776338
 */
public class IdentityCodec {

    private final String alphabet;

    private final int base;

    private IdentityCodec(String alphabet) {
        this.alphabet = alphabet;
        this.base = alphabet.length();
    }

    public static IdentityCodec of(String alphabet) {
        return new IdentityCodec(alphabet);
    }

    public String encode(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(alphabet.charAt(n % base));
            n /= base;
        }
        return sb.reverse().toString();
    }

    public int decode(String keyword) {
        int n = 0;
        for (int i = 0; i < keyword.length(); i++) {
            n = n * base + alphabet.indexOf(keyword.charAt(i));
        }
        return n;
    }

}
