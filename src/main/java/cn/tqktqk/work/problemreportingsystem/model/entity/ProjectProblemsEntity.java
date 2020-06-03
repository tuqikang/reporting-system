package cn.tqktqk.work.problemreportingsystem.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author tuqikang
 * @since 2020-06-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("project_problems")
@ApiModel(value="ProjectProblemsEntity对象", description="")
public class ProjectProblemsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目问题id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "项目组id")
    @TableField("group_id")
    private Long groupId;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "状态 解决 待解决")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "重要级别")
    @TableField("level")
    private Integer level;

    @ApiModelProperty(value = "完成人Id")
    @TableField("finish_id")
    private Long finishId;

    @ApiModelProperty(value = "完成时间")
    @TableField("finish_time")
    private LocalDateTime finishTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "问题方向（部门id）")
    @TableField("department_id")
    private Long departmentId;

    @ApiModelProperty(value = "逻辑删除1存在 2删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;


}
