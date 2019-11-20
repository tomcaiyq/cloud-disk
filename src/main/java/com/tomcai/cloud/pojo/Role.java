package com.tomcai.cloud.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Role extends BaseEntity {
    private String name;
}
