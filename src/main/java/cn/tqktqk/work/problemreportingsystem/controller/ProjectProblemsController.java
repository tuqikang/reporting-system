package cn.tqktqk.work.problemreportingsystem.controller;

import cn.tqktqk.work.problemreportingsystem.model.result.ProblemsResult;
import cn.tqktqk.work.problemreportingsystem.model.vo.ProblemVo;
import cn.tqktqk.work.problemreportingsystem.service.ProjectProblemsService;
import cn.tqktqk.work.problemreportingsystem.utils.Response;
import cn.tqktqk.work.problemreportingsystem.utils.ResponsePage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
 * @Classname ProjectProblemsController
 * @Description TODO
 * @Date 2020/6/4 2:28 下午
 * @Author 麦麦麦阁
 */
@RestController
@Api(value = "项目问题相关接口")
@RequestMapping("/project/problems")
public class ProjectProblemsController {

    @Autowired
    private ProjectProblemsService projectProblemsService;

    @GetMapping("/{groupId}")
    @ApiOperation("获取对应项目的问题列表")
    public ResponsePage<ProblemsResult> list(@PathVariable("groupId") Long groupId,
                                             @ApiParam("用户id") @RequestParam(value = "userId") Long userId,
                                             @ApiParam("问题级别") @RequestParam(value = "level",defaultValue = "0")Integer level,
                                             @ApiParam("问题状态") @RequestParam(value = "status",defaultValue = "0") Integer status,
                                             @ApiParam("问题方向") @RequestParam(value = "department",defaultValue = "0")Long department){
        return projectProblemsService.list(groupId,userId,level,status,department);
    }

    @PostMapping("/{groupId}")
    @ApiOperation("上报项目问题")
    public Response store(@PathVariable("groupId") Long groupId,@RequestBody ProblemVo vo){
        return projectProblemsService.store(groupId,vo);
    }

    @DeleteMapping("/{problemsId}")
    @ApiOperation("删除某个项目问题")
    public Response deleted(@PathVariable("problemsId") Long problemsId,@ApiParam("用户id") @RequestParam(value = "userId") Long userId){
        return projectProblemsService.deleted(problemsId,userId);
    }

    @PostMapping("/fix/{problemsId}")
    @ApiOperation("解决某个项目问题")
    public Response fix(@PathVariable("problemsId") Long problemsId,@ApiParam("用户id") @RequestParam(value = "userId") Long userId){
        return projectProblemsService.fix(problemsId,userId);
    }

}
