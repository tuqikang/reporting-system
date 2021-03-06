package cn.tqktqk.work.problemreportingsystem.utils;

import cn.tqktqk.work.problemreportingsystem.anno.Ignore;

import java.lang.reflect.Field;

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
 * @Classname BeanUtils
 * @Description TODO
 * @Date 2020/6/3 3:36 下午
 * @Author 麦麦麦阁
 */
public class FormUtils {

    public static <T> boolean beanFormHaveNull(T bean) {
        // 得到类对象
        Class apVoClass = bean.getClass();
        //得到属性集合
        Field[] fs = apVoClass.getDeclaredFields();
        boolean flag = false;
        //遍历属性
        for (Field f : fs) {
            // 设置属性是可以访问的(私有的也可以)
            f.setAccessible(true);
            //省略有Igonre注解的字段
            if (f.isAnnotationPresent(Ignore.class)) {
                continue;
            }
            // 得到此属性的值
            Object val = null;
            try {
                val = f.get(bean);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            //只要有1个属性为空
            if (val == null) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
