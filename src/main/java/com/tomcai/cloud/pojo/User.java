package com.tomcai.cloud.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date birthday;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createDate;
    private int status;
}
