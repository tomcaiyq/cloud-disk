package com.tomcai.cloud.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FileInfo extends BaseEntity {
    private String fileName;
    private String url;
    private Long size;
    private String type;
    private String typeId;
    private String fatherId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private String uploadDate;
    private String uploader;
    private String uploaderId;
    private String suffix;
}
