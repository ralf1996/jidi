package com.jidi.ssm.pojo;

public class UserVO extends User {

    UserFile userFile;

    public UserFile getUserFile() {
        return userFile;
    }

    public void setUserFile(UserFile userFile) {
        this.userFile = userFile;
    }
}
