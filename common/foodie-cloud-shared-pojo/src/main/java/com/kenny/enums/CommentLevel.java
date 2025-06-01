package com.kenny.enums;

/**
 * @Desc: Item comment level enum
 */
public enum CommentLevel {
    GOOD(1, "Positive"),
    NORMAL(2, "Neutral"),
    BAD(3, "Negative");

    public final Integer type;
    public final String value;

    CommentLevel(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
