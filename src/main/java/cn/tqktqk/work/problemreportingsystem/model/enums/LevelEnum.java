package cn.tqktqk.work.problemreportingsystem.model.enums;

import cn.tqktqk.work.problemreportingsystem.exceptions.ServerException;
import cn.tqktqk.work.problemreportingsystem.model.result.LevelResult;
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
public enum LevelEnum {

    一般(1, "一般"),
    重要(2, "重要"),
    紧急(3, "紧急");

    private Integer level;

    private String msg;

    public static Integer getStatusByMsg(String msg) {
        LevelEnum[] values = LevelEnum.values();
        for (LevelEnum value : values) {
            if (value.getMsg().equals(msg)) {
                return value.level;
            }
        }
        throw new ServerException(ResponseEnum.FAIL);
    }

    public static String getMsgByStatus(Integer level) {
        LevelEnum[] values = LevelEnum.values();
        for (LevelEnum value : values) {
            if (value.getLevel() - level == 0) {
                return value.msg;
            }
        }
        throw new ServerException(ResponseEnum.FAIL);
    }

    public static List<LevelResult> levelResults(){
        List<LevelResult> results = Arrays.stream(LevelEnum.values()).map(p -> new LevelResult(p.getLevel(), p.getMsg())).collect(Collectors.toList());
        return results;
    }

}
