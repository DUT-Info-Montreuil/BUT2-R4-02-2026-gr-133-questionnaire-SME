package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.enums;

public enum LangueEnum {

    FR("fr");

    private final String code;

    LangueEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static LangueEnum fromCode(String code) {
        for (LangueEnum langue : values()) {
            if (langue.code.equalsIgnoreCase(code)) {
                return langue;
            }
        }
        throw new IllegalArgumentException("Langue inconnue : " + code);
    }
}
