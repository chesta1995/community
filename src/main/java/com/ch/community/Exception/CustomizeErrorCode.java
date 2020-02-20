package com.ch.community.Exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND(2001, "你找的问题不在了，要不要换个试试？"),
    NO_LOGIN(2004,"未选中任何问题进行评论"),
    SYS_ERROR(2002,"服务器出问题了"),
    TARGET_PARAM_NOT_FOUND(2003, "未选中任何问题或评论进行回复"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "你找的评论不在了，要不要换个试试？");

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;

     CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
