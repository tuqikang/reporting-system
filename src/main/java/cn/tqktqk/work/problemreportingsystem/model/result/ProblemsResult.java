package cn.tqktqk.work.problemreportingsystem.model.result;

import cn.tqktqk.work.problemreportingsystem.model.enums.LevelEnum;
import cn.tqktqk.work.problemreportingsystem.model.enums.StatusEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
 * @Classname ProblemsResult
 * @Description TODO
 * @Date 2020/6/4 2:30 下午
 * @Author 麦麦麦阁
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("项目问题结果")
public class ProblemsResult {

    @ApiModelProperty(value = "项目问题id")
    private Long id;

    @ApiModelProperty(value = "项目组id")
    private Long groupId;

    @ApiModelProperty(value = "提出人id")
    private Long userId;

    @ApiModelProperty(value = "提出人名字")
    private String userName;

    @ApiModelProperty(value = "问题内容")
    private String content;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "状态名称")
    private String statusName;

    @ApiModelProperty(value = "重要级别")
    private Integer level;

    @ApiModelProperty(value = "重要级别名称")
    private String levelName;

    @ApiModelProperty(value = "完成人Id")
    private Long finishId;

    @ApiModelProperty(value = "完成人名称")
    private String finishName;

    @ApiModelProperty(value = "完成时间")
    private LocalDateTime finishTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "问题方向（部门id）")
    private Long departmentId;

    @ApiModelProperty(value = "问题方向（部门名称）")
    private String departmentName;

    public void setStatus(Integer status) {
        this.status = status;
        this.statusName = StatusEnum.getMsgByStatus(status);
    }

    public void setLevel(Integer level) {
        this.level = level;
        this.levelName = LevelEnum.getMsgByStatus(level);
    }
}
