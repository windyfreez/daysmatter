package com.itsean.days_matter.pojo;

import lombok.Data;
import java.io.Serializable;

/**
 * 全局统一响应结果类
 * 支持序列化，便于网络传输和日志打印
 */
@Data
public class Result<T> implements Serializable {

    // 序列化版本号（避免反序列化异常）
    private static final long serialVersionUID = 1L;

    // 响应状态码：200=成功，400=参数错误，401=未授权，403=禁止访问，500=服务器错误
    private int code;

    // 响应信息（成功/失败描述）
    private String msg;

    // 响应数据（成功时返回，失败时可为null）
    private T data;

    // ========== 无参构造器（必要，支持反序列化） ==========
    public Result() {}

    // ========== 全参构造器（便于灵活创建） ==========
    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // ========== 成功响应（常用场景） ==========
    /**
     * 成功 + 带数据
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    /**
     * 成功 + 无数据（如删除、更新接口）
     */
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null);
    }

    /**
     * 成功 + 自定义消息 + 带数据
     */
    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(200, msg, data);
    }

    // ========== 失败响应（常用场景） ==========
    /**
     * 失败 + 自定义状态码 + 消息
     */
    public static <T> Result<T> fail(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    /**
     * 失败 + 自定义状态码 + 消息 + 错误数据（如参数校验失败详情）
     */
    public static <T> Result<T> fail(int code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    /**
     * 通用参数错误（简化调用，无需写400码）
     */
    public static <T> Result<T> paramError(String msg) {
        return new Result<>(400, msg, null);
    }

    /**
     * 通用服务器错误（简化调用，无需写500码）
     */
    public static <T> Result<T> serverError(String msg) {
        return new Result<>(500, msg, null);
    }
}