package com.fht.controller.exception;


import com.fht.common.bean.R;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常捕捉类
 *
 * @author Bean
 */
@ControllerAdvice
public class ExceptionController {

    // 登录异常抛出异常捕捉
    @ExceptionHandler(value = UnknownAccountException.class)
    @ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
    public R AccountException(UnknownAccountException e) {
        return R.error(501, "用户名或密码错误");
    }

    // 密码错误异常捕捉
    @ExceptionHandler(value = IncorrectCredentialsException.class)
    @ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
    public R AccountException(IncorrectCredentialsException e) {
        return R.error(502, "用户名或密码错误");
    }

    // shiro配置出错
    @ExceptionHandler(value = UnavailableSecurityManagerException.class)
    @ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
    public R UnavailableSecurityManagerException(UnavailableSecurityManagerException e) {
        return R.error(503, "shiro配置出错，尝试检查是否开启！");
    }

    /// 角色權权限异常捕捉
    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
    public R roleException(UnauthorizedException e) {
        return R.error(504, "当前用户没有权限");
    }

    // 其它异常异常捕捉
    @ExceptionHandler(value = AccountException.class)
    @ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
    public R NullUsernameAndPasswordException(AccountException e) {

        return R.error(506, e.getMessage());
    }

    // 其它异常异常捕捉
    @ExceptionHandler(value = Exception.class)
    @ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
    public R allException(Exception e) {
        e.printStackTrace();
        return R.error(507, "系统出现异常，请稍后再试");
    }
    //            } catch (IncorrectCredentialsException e) {
//                msg = "登录密码错误";
//                System.out.println("登录密码错误!!!" + e);
//            } catch (ExcessiveAttemptsException e) {
//                msg = "登录失败次数过多";
//                System.out.println("登录失败次数过多!!!" + e);
//            } catch (LockedAccountException e) {
//                msg = "帐号已被锁定";
//                System.out.println("帐号已被锁定!!!" + e);
//            } catch (DisabledAccountException e) {
//                msg = "帐号已被禁用";
//                System.out.println("帐号已被禁用!!!" + e);
//            } catch (ExpiredCredentialsException e) {
//                msg = "帐号已过期";
//                System.out.println("帐号已过期!!!" + e);
//            } catch (UnknownAccountException e) {
//                msg = "帐号不存在";
//                System.out.println("帐号不存在!!!" + e);
//            } catch (UnauthorizedException e) {
//                msg = "您没有得到相应的授权！";
//                System.out.println("您没有得到相应的授权！" + e);
//            }  catch (AccountException e) {
//                msg = "账号或密码错误！";
//                System.out.println("账号或密码错误！" + e);
//            } catch (Exception e) {
//                System.out.println("出错！！！" + e);
//            }
//            map.put("msg", msg);
//            return map;

}
