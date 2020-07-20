package com.anko.coursems.common.util;

import com.anko.coursems.entity.User;
import org.apache.shiro.SecurityUtils;

public class UserUtils {

    public static User getCurrentUser() {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
        return user;
    }

    public static void setCurrentUser(User user) {
        SecurityUtils.getSubject().getSession().setAttribute("currentUser", user);
    }
}
