package br.com.vargas.course_bff.enums;

public enum EActive {
    TRUE(true),
    FALSE(false);

    private final Boolean value;

    EActive(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }
}
