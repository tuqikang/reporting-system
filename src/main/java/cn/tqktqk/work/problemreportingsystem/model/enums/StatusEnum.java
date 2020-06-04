package cn.tqktqk.work.problemreportingsystem.model.enums;

import cn.tqktqk.work.problemreportingsystem.exceptions.ServerException;
import cn.tqktqk.work.problemreportingsystem.model.result.StatusResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
 * @Classname LevelEnum
 * @Description TODO
 * @Date 2020/6/4 2:19 下午
 * @Author 麦麦麦阁
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum StatusEnum {

    待解决(1, "待解决"),
    已解决(2, "已解决");

    private Integer status;

    private String msg;

    public static Integer getStatusByMsg(String msg) {
        StatusEnum[] values = StatusEnum.values();
        for (StatusEnum value : values) {
            if (value.getMsg().equals(msg)) {
                return value.status;
            }
        }
        throw new ServerException(ResponseEnum.FAIL);
    }

    public static String getMsgByStatus(Integer level) {
        StatusEnum[] values = StatusEnum.values();
        for (StatusEnum value : values) {
            if (value.getStatus() - level == 0) {
                return value.msg;
            }
        }
        throw new ServerException(ResponseEnum.FAIL);
    }

    public static List<StatusResult> statusResults(){
        List<StatusResult> results = Arrays.stream(StatusEnum.values()).map(p -> new StatusResult(p.getStatus(), p.getMsg())).collect(Collectors.toList());
        return results;
    }

}
