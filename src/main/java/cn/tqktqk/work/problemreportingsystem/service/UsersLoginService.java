package cn.tqktqk.work.problemreportingsystem.service;

import cn.tqktqk.work.problemreportingsystem.dao.UserDepartmentMapper;
import cn.tqktqk.work.problemreportingsystem.dao.UsersMapper;
import cn.tqktqk.work.problemreportingsystem.exceptions.ServerException;
import cn.tqktqk.work.problemreportingsystem.model.entity.UserDepartmentEntity;
import cn.tqktqk.work.problemreportingsystem.model.entity.UsersEntity;
import cn.tqktqk.work.problemreportingsystem.model.enums.ResponseEnum;
import cn.tqktqk.work.problemreportingsystem.model.result.UserInfoResult;
import cn.tqktqk.work.problemreportingsystem.model.vo.UserLoginVo;
import cn.tqktqk.work.problemreportingsystem.model.vo.UserRegisterVo;
import cn.tqktqk.work.problemreportingsystem.utils.FormUtils;
import cn.tqktqk.work.problemreportingsystem.utils.PasswordUtils;
import cn.tqktqk.work.problemreportingsystem.utils.Response;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
 * @Classname UsersLoginService
 * @Description TODO
 * @Date 2020/6/3 3:39 下午
 * @Author 麦麦麦阁
 */
@Service
public class UsersLoginService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UserDepartmentMapper userDepartmentMapper;

    @Transactional(rollbackFor = Exception.class)//开启事务
    public Response register(UserRegisterVo vo) {
        //校验表单是否完整
        if (FormUtils.beanFormHaveNull(vo)) {
            throw new ServerException(ResponseEnum.FORM_NOT_EXISTS);
        }
        //查找是否有相同的用户存在
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username",vo.getUsername());
        if (usersMapper.selectOne(wrapper)!=null){
            throw new ServerException(ResponseEnum.USER_IS_EXISTS);
        }

        //密码加密
        String encodePassword = PasswordUtils.encode(vo.getPassword());
        vo.setPassword(encodePassword);
        //vo->entity
        UsersEntity usersEntity = new UsersEntity();
        BeanUtils.copyProperties(vo,usersEntity);
        //插入到users表
        usersMapper.insert(usersEntity);
        //插入user_department关系表
        UserDepartmentEntity userDepartmentEntity = new UserDepartmentEntity();
        userDepartmentEntity.setUserId(usersEntity.getId());
        userDepartmentEntity.setDepartmentId(vo.getDepartmentsId());
        userDepartmentMapper.insert(userDepartmentEntity);
        return Response.success();
    }

    public Response<UserInfoResult> login(UserLoginVo vo) {
        //查找用户是否存在
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username",vo.getUsername());
        UsersEntity usersEntity = usersMapper.selectOne(wrapper);
        if (usersEntity==null){
            throw new ServerException(ResponseEnum.USER_NOT_EXIST);
        }
        //校验密码
        if (!PasswordUtils.match(vo.getPassword(),usersEntity.getPassword())) {
            throw new ServerException(ResponseEnum.CUSTOM,"密码错误");
        }
        //获取用户信息
        UserInfoResult userInfoResult = usersMapper.selectUserInfoByUserId(usersEntity.getId());
        return Response.success(userInfoResult);
    }
}
