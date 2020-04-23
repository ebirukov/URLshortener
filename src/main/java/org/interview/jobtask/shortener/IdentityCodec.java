package org.interview.jobtask.shortener;

/**
 * Биективное отображение числовое значение в строку и обратно
 * По сути преобразует число из десятичной системы счисления в систему по основанию base и обратно
 * где base это длина алфавита alphabet
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
