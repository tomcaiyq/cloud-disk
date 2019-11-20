package com.tomcai.cloud.enums;

public enum FileTypeEnum {
    IMAGE("图片", "image", "1"),
    VIDEO("视频", "video", "2"),
    DOCUMENT("文档", "document", "3"),
    AUDIO("音频", "audio", "4"),
    TORRENT("种子", "torrent", "5"),
    OTHER("其它", "other", "6");

    private String name;
    private String nameEn;
    private String id;

    FileTypeEnum(String name, String nameEn, String id) {
        this.name = name;
        this.nameEn = nameEn;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getId() {
        return id;
    }
}
