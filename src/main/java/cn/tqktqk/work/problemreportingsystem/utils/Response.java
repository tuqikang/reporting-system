package cn.tqktqk.work.problemreportingsystem.utils;

import cn.tqktqk.work.problemreportingsystem.model.enums.ResponseEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * █████▒█      ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 * ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 * ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 * ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 * ░     ░ ░      ░  ░
 *
 * @Classname Response
 * @Description TODO
 * @Date 2020/6/2 12:15 下午
 * @Author 麦麦麦阁
 */
@JsonInclude
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Response<T> implements Serializable {

    /**
     * 响应编码200为成功
     */
    private Integer code;

    /**
     * 请求是否成功
     */
    private boolean success;

    /**
     * 成功数据
     */
    private T data;

    /**
     * 描述
     */
    private String msg;

    public static Response success() {
        Response response = new Response();
        response.success = true;
        response.code = ResponseEnum.SUCCESS.getCode();
        response.msg = ResponseEnum.SUCCESS.getMsg();
        return response;
    }

    public static Response success(Object data) {
        Response response = success();
        response.data = data;
        return response;
    }

    public static Response fail(ResponseEnum responseEnum) {
        Response response = new Response();
        response.success = false;
        response.code = responseEnum.getCode();
        response.msg = responseEnum.getMsg();
        return response;
    }

    public static Response fail(ResponseEnum responseEnum,String msg) {
        Response response = new Response();
        response.success = false;
        response.code = responseEnum.getCode();
        response.msg = msg;
        return response;
    }

    public static Response fail(ResponseEnum responseEnum, Object data) {
        Response response = fail(responseEnum);
        response.setData(data);
        return response;
    }

}
