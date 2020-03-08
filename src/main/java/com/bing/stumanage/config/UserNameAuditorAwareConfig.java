package com.bing.stumanage.config;

import com.bing.stumanage.utils.ShiroUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Description 自动更新创建人，创建时间，最后操作人，最后操作时间
 * @Author zhaobing
 * @Date 2020/1/28 15:23
 **/

/**
 * Spring Data JPA通过AuditorAware<T>接口获取用户信息,
 * 其中泛型T可以为String保存用户名，也可以为Long/Integer保存用户ID
 */
@Component
public class UserNameAuditorAwareConfig implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            String username = getUserName();
            return Optional.ofNullable(username);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    /**
     * 获取当前登录用户名
     * @return
     */
    private String getUserName() {
        return ShiroUtils.getUser() == null ? null : ShiroUtils.getUser().getUsername();
    }
}
