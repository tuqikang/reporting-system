package cn.tqktqk.work.problemreportingsystem.utils;

import cn.tqktqk.work.problemreportingsystem.model.enums.ResponseEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

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
public class ResponsePage<T> implements Serializable {

    /**
     * 响应编码200为成功
     */
    private Integer code;

    /**
     * 请求消耗时间
     */
    private boolean success;

    /**
     * 成功数据
     */
    private List<T> data;

    /**
     * 描述
     */
    private String msg;

    /**
     * 总数
     */
    private long count;

    /**
     * 总页数
     */
    private long pages;

    public static ResponsePage success() {
        ResponsePage response = new ResponsePage();
        response.success = true;
        response.code = ResponseEnum.SUCCESS.getCode();
        response.msg = ResponseEnum.SUCCESS.getMsg();
        return response;
    }

    public static ResponsePage success(List<Object> data) {
        ResponsePage response = success();
        response.data = data;
        return response;
    }

    public static ResponsePage success(List<Object> data,long count,long pages) {
        ResponsePage response = success();
        response.data = data;
        response.count = count;
        response.pages = pages;
        return response;
    }

    public static ResponsePage fail(ResponseEnum responseEnum) {
        ResponsePage response = new ResponsePage();
        response.success = false;
        response.code = responseEnum.getCode();
        response.msg = responseEnum.getMsg();
        return response;
    }

    public static ResponsePage fail(ResponseEnum responseEnum, List<Object> data) {
        ResponsePage response = fail(responseEnum);
        response.setData(data);
        return response;
    }

}
