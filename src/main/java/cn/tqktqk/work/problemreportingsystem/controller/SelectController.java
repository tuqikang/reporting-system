package cn.tqktqk.work.problemreportingsystem.controller;

import cn.tqktqk.work.problemreportingsystem.model.enums.LevelEnum;
import cn.tqktqk.work.problemreportingsystem.model.enums.StatusEnum;
import cn.tqktqk.work.problemreportingsystem.model.result.DepartmentResult;
import cn.tqktqk.work.problemreportingsystem.model.result.LevelResult;
import cn.tqktqk.work.problemreportingsystem.model.result.StatusResult;
import cn.tqktqk.work.problemreportingsystem.model.result.UserSimpleResult;
import cn.tqktqk.work.problemreportingsystem.service.DepartmentService;
import cn.tqktqk.work.problemreportingsystem.utils.ResponsePage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * @Classname SelectController
 * @Description TODO
 * @Date 2020/6/4 2:16 下午
 * @Author 麦麦麦阁
 */
@RestController
@Api(value = "各种下拉列表接口")
@RequestMapping("/select")
public class SelectController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department")
    @ApiOperation("获取部门列表")
    public ResponsePage<DepartmentResult> departmentSelect(){
        return departmentService.select();
    }

    @GetMapping("/department/{department}")
    @ApiOperation("获取部门人员列表")
    public ResponsePage<UserSimpleResult> usersSelectForDepartment(@PathVariable("department") Long department){
        return departmentService.selectForDepartment(department);
    }

    @GetMapping("/level")
    @ApiOperation("级别下拉列表")
    public ResponsePage<LevelResult> levelSelect(){
        List<LevelResult> levelResults = LevelEnum.levelResults();
        return ResponsePage.success(levelResults,levelResults.size(),1L);
    }

    @GetMapping("/status")
    @ApiOperation("状态下拉列表")
    public ResponsePage<StatusResult> statusSelect(){
        List<StatusResult> statusResults = StatusEnum.statusResults();
        return ResponsePage.success(statusResults,statusResults.size(),1L);
    }

}
