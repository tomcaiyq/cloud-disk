package com.tomcai.cloud.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class BaseEntity implements Serializable {
    protected Date createDate;
    protected Date updateDate;
    protected short del = 0;
}
