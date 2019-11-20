package com.tomcai.cloud.utils;

import com.tomcai.cloud.enums.FileTypeEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileUtils {
    private static final List<String> IMAGE = Arrays.asList("jpg", "png", "gif", "bmp", "jpeg");
    private static final List<String> VIDEO = Arrays.asList("avi", "mp4", "mkv", "m4v", "rmvb", "3gp");
    private static final List<String> DOCUMENT = Arrays.asList("txt", "doc", "docx", "xls", "pdf", "xlsx", "html");
    private static final List<String> AUDIO = Arrays.asList("mp3", "wav", "mmf", "amr", "aac");
    private static final List<String> TORRENT = Collections.singletonList("torrent");

    public static FileTypeEnum fileType(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        FileTypeEnum type;
        if (IMAGE.contains(suffix))
            type = FileTypeEnum.IMAGE;
        else if (VIDEO.contains(suffix))
            type = FileTypeEnum.VIDEO;
        else if (DOCUMENT.contains(suffix))
            type = FileTypeEnum.DOCUMENT;
        else if (AUDIO.contains(suffix))
            type = FileTypeEnum.AUDIO;
        else if (TORRENT.contains(suffix))
            type = FileTypeEnum.TORRENT;
        else
            type = FileTypeEnum.OTHER;
        type.setSuffix(suffix);
        return type;
    }
}
