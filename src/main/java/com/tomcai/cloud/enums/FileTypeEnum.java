package com.tomcai.cloud.enums;

public enum FileTypeEnum {
    IMAGE("图片", "image", "1", ""),
    VIDEO("视频", "video", "2", ""),
    DOCUMENT("文档", "document", "3", ""),
    AUDIO("音频", "audio", "4", ""),
    APPLICATION("应用", "application", "5", ""),
    TORRENT("种子", "torrent", "6", ""),
    OTHER("其它", "other", "7", "");

    private String name;
    private String nameEn;
    private String id;
    private String suffix;

    FileTypeEnum(String name, String nameEn, String id, String suffix) {
        this.name = name;
        this.nameEn = nameEn;
        this.id = id;
        this.suffix = suffix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
