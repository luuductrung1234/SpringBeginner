package com.learn.simpleconsoleapp.services;

import com.learn.simpleconsoleapp.models.UserInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * A stateful authentication service. Just simulate authentication process
 *
 * @see com.learn.simpleconsoleapp.models.UserInfo
 * @see com.learn.simpleconsoleapp.beans.SecretBean
 * @see com.learn.simpleconsoleapp.seedworks.SecurityAdvice
 */
@Service
@Scope("prototype")
public class SecurityManager {
    private static ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    public void login(String userName, String password) {
        threadLocal.set(new UserInfo(userName, password));
    }

    public void logout() {
        threadLocal.set(null);
    }

    public UserInfo getLoggedOnUser() {
       return threadLocal.get();
    }
}
