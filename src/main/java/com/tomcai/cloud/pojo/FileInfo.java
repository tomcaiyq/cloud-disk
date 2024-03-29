package com.tomcai.cloud.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class FileInfo extends BaseEntity {
    private Long id;
    private String fileName;
    private String userFileName;
    private String username;
    private String url;
    private String userUrl;
    private Long size;
    private String icon;
    private String sizeShow;
    private String type;
    private String typeId;
    private String md5;
    private String parentId;
    private String suffix;
    private Date uploadDate;
}
