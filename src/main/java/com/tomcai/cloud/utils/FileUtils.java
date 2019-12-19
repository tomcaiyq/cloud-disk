package com.tomcai.cloud.utils;

import com.tomcai.cloud.enums.FileTypeEnum;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileUtils {
    private static final List<String> IMAGE = Arrays.asList("jpg", "png", "gif", "bmp", "jpeg");
    private static final List<String> VIDEO = Arrays.asList("avi", "mp4", "mkv", "m4v", "rmvb", "3gp");
    private static final List<String> DOCUMENT = Arrays.asList("txt", "doc", "docx", "xls", "pdf", "xlsx", "html");
    private static final List<String> AUDIO = Arrays.asList("mp3", "wav", "mmf", "amr", "aac");
    private static final List<String> APPLICATION = Arrays.asList("exe", "apk");
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
        else if (APPLICATION.contains(suffix))
            type = FileTypeEnum.APPLICATION;
        else if (TORRENT.contains(suffix))
            type = FileTypeEnum.TORRENT;
        else
            type = FileTypeEnum.OTHER;
        type.setSuffix(suffix);
        return type;
    }

    public static String fileSize(Long size) {
        StringBuilder sb;
        BigDecimal decimal;
        if (size < (1 << 10)) {
            sb = new StringBuilder();
            sb.append(size).append("B");
        } else if (size < (1 << 20)) {
            sb = new StringBuilder();
            sb.append(size / (1 << 10)).append("KB");
        } else if (size < (1 << 30)) {
            sb = new StringBuilder();
            decimal = BigDecimal.valueOf((double) size / (double) (1 << 20));
            sb.append(decimal.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue()).append("M");
        } else {
            sb = new StringBuilder();
            decimal = BigDecimal.valueOf((double) size / (double) (1 << 30));
            sb.append(decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()).append("G");
        }
        return sb.toString();
    }

    public static String fileName(String suffix) {
        return suffix.toUpperCase() + "_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString().substring(3, 8) +
                "." + suffix;
    }

    public static String dirName() {
        return LocalDate.now().toString().replaceAll("-", "");
    }

    public static String fileName(String old, String suffix) {
        return old.substring(0, old.lastIndexOf(".")) + "_" + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + "." + suffix;
    }

}
