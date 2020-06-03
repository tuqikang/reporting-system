package cn.tqktqk.work.problemreportingsystem.controller;

import cn.tqktqk.work.problemreportingsystem.model.result.UserInfoResult;
import cn.tqktqk.work.problemreportingsystem.model.vo.UserLoginVo;
import cn.tqktqk.work.problemreportingsystem.model.vo.UserRegisterVo;
import cn.tqktqk.work.problemreportingsystem.service.UsersLoginService;
import cn.tqktqk.work.problemreportingsystem.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * @Classname UsersLoginController
 * @Description 虽然是登陆，但是只能是个假登陆了，没有使用security。
 * @Date 2020/6/3 3:23 下午
 * @Author 麦麦麦阁
 */
@RestController
@Api(value = "用户登陆注册接口")
@RequestMapping("/users/login")
public class UsersLoginController {

    @Autowired
    private UsersLoginService usersLoginService;

    @PostMapping
    @ApiOperation("登陆")
    public Response<UserInfoResult> login(@RequestBody UserLoginVo vo){
        return usersLoginService.login(vo);
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    public Response register(@RequestBody UserRegisterVo vo){
        return usersLoginService.register(vo);
    }

}
