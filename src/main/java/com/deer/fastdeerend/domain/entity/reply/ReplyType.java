package com.deer.fastdeerend.domain.entity.reply;

public enum ReplyType {
    COMMENT("COMMENT"),
    REPLY("REPLY");

    private final String value;

    ReplyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
