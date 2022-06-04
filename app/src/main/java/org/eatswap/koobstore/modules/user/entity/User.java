package org.eatswap.koobstore.modules.user.entity;

import org.eatswap.koobstore.base.BaseEntity;
import org.greenrobot.greendao.annotation.NotNull;

public class User extends BaseEntity {
    @NotNull
    private String username;

    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
