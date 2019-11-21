package com.tomcai.cloud.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private String id;
    private String username;
    private String password;
    private String nickName;
    private String email;
    private String phone;
    private String avatarUrl;
    private String gender;
    private Date birthday;
    private Date createDate;
    private int status;
}
