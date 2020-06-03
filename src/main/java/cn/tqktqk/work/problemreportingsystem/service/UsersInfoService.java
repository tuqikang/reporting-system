package cn.tqktqk.work.problemreportingsystem.service;

import cn.tqktqk.work.problemreportingsystem.dao.DepartmentsMapper;
import cn.tqktqk.work.problemreportingsystem.dao.UserDepartmentMapper;
import cn.tqktqk.work.problemreportingsystem.dao.UsersMapper;
import cn.tqktqk.work.problemreportingsystem.exceptions.ServerException;
import cn.tqktqk.work.problemreportingsystem.model.entity.UsersEntity;
import cn.tqktqk.work.problemreportingsystem.model.enums.ResponseEnum;
import cn.tqktqk.work.problemreportingsystem.model.result.UserInfoResult;
import cn.tqktqk.work.problemreportingsystem.model.vo.PasswordVo;
import cn.tqktqk.work.problemreportingsystem.model.vo.UserUpdateVo;
import cn.tqktqk.work.problemreportingsystem.utils.PasswordUtils;
import cn.tqktqk.work.problemreportingsystem.utils.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.util.Password;

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
 * @Classname UsersInfoService
 * @Description TODO
 * @Date 2020/6/3 4:00 下午
 * @Author 麦麦麦阁
 */
@Service
public class UsersInfoService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UserDepartmentMapper userDepartmentMapper;

    @Autowired
    private DepartmentsMapper departmentsMapper;


    public Response<UserInfoResult> index(Long userId) {
        return Response.success(usersMapper.selectUserInfoByUserId(userId));
    }

    public Response update(Long userId, UserUpdateVo vo) {
        if (StringUtils.isEmpty(vo.getNickname())) {
            throw new ServerException(ResponseEnum.CUSTOM,"昵称不能为空");
        }
        //判断部门是否存在
        if (departmentsMapper.selectById(vo.getDepartmentsId())==null) {
            throw new ServerException(ResponseEnum.CUSTOM,"部门不存在");
        }
        //修改昵称
        usersMapper.updateNickname(userId,vo.getNickname());
        //修改部门
        userDepartmentMapper.updateByUserId(userId,vo.getDepartmentsId());
        return Response.success();
    }

    public Response password(Long userId, PasswordVo vo) {
        if (StringUtils.isEmpty(vo.getNewPassword())) {
            throw new ServerException(ResponseEnum.CUSTOM,"密码不能为空");
        }
        UsersEntity usersEntity = usersMapper.selectById(userId);
        if (!PasswordUtils.match(vo.getOldPassword(),usersEntity.getPassword())) {
            throw new ServerException(ResponseEnum.CUSTOM,"原密码错误");
        }
        if (vo.getNewPassword().equals(vo.getOldPassword())){
            throw new ServerException(ResponseEnum.CUSTOM,"新密码不能和原密码相同");
        }
        usersMapper.updatePassword(userId,PasswordUtils.encode(vo.getNewPassword()));
        return Response.success();
    }
}
