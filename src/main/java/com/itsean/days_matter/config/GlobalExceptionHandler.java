package com.itsean.days_matter.config;

import com.itsean.days_matter.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * 全局异常处理器：覆盖所有接口的参数错误、格式错误、业务异常等
 */
@Slf4j
@RestControllerAdvice // 作用于所有 @RestController
public class GlobalExceptionHandler {

    // 1. 处理 @Valid 触发的 POJO 参数校验错误（如 @Min、@NotNull）
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleValidError(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMsg = bindingResult.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("；"));
        log.warn("参数校验失败：{}", errorMsg);
        return Result.paramError(errorMsg);
    }


    // 3. 处理日期格式错误（如 2025-13-01、2025-02-30、abc123 等）
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<?> handleDateFormatError(MethodArgumentTypeMismatchException e) {
        // 判断是否是 LocalDate 类型转换失败
        if (e.getRequiredType() == LocalDate.class) {
            log.warn("日期格式错误：输入值={}", e.getValue());
            return Result.paramError("日期格式错误，请传入 yyyy-MM-dd 格式（如 2025-12-31）");
        }
        // 其他类型转换错误（如 periodDay 传入字符串）
        log.warn("参数类型错误：参数={}，期望类型={}", e.getName(), e.getRequiredType().getSimpleName());
        return Result.paramError("参数类型错误，请传入正确格式");
    }

    // 4. 处理 JSON 格式错误（如前端传参缺少大括号、逗号等）
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<?> handleJsonFormatError(HttpMessageNotReadableException e) {
        log.warn("JSON 格式错误：{}", e.getMessage().substring(0, 100));
        return Result.paramError("请求参数格式错误，请检查 JSON 格式是否正确");
    }

    // 5. 处理缺少必填参数错误（如前端漏传 date、periodDay 等）
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<?> handleMissingParamError(MissingServletRequestParameterException e) {
        log.warn("缺少必填参数：{}", e.getParameterName());
        return Result.paramError("缺少必填参数：" + e.getParameterName());
    }

    // 7. 处理所有未捕获的未知异常（兜底）
    @ExceptionHandler(Exception.class)
    public Result<?> handleUnknownError(Exception e) {
        log.error("服务器内部错误", e);
        return Result.serverError("系统繁忙，请稍后重试");
    }
}