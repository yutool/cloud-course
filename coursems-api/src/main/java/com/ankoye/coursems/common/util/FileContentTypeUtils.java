package com.ankoye.coursems.common.util;

public class FileContentTypeUtils {

    public static String contentType(String FilenameExtension) {
        if (FilenameExtension.equals(".BMP") || FilenameExtension.equals(".bmp")
                || FilenameExtension.toUpperCase().equals(".BMP")) {
            return "image/bmp";
        }
        if (FilenameExtension.equals(".GIF") || FilenameExtension.equals(".gif")
                || FilenameExtension.toUpperCase().equals(".GIF")) {
            return "image/gif";
        }
        if (FilenameExtension.equals(".JPEG") || FilenameExtension.equals(".jpeg") || FilenameExtension.equals(".JPG")
                || FilenameExtension.equals(".jpg") || FilenameExtension.equals(".PNG")
                || FilenameExtension.equals(".png") || FilenameExtension.toUpperCase().equals(".JPEG")
                || FilenameExtension.toUpperCase().equals(".JPG") || FilenameExtension.toUpperCase().equals(".PNG")) {
            return "image/jpeg";
        }
        if (FilenameExtension.equals(".HTML") || FilenameExtension.equals(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equals(".TXT") || FilenameExtension.equals(".txt")
                || FilenameExtension.toUpperCase().equals(".TXT")) {
            return "text/plain";
        }
        if (FilenameExtension.equals(".VSD") || FilenameExtension.equals(".vsd")
                || FilenameExtension.toUpperCase().equals(".VSD")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equals(".PPTX") || FilenameExtension.equals(".pptx") || FilenameExtension.equals(".PPT")
                || FilenameExtension.equals(".ppt") || FilenameExtension.toUpperCase().equals(".PPTX")
                || FilenameExtension.toUpperCase().equals(".PPT")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equals(".DOCX") || FilenameExtension.equals(".docx") || FilenameExtension.equals(".DOC")
                || FilenameExtension.equals(".doc") || FilenameExtension.toUpperCase().equals(".DOCX")
                || FilenameExtension.toUpperCase().equals(".DOC")) {
            return "application/msword";
        }
        if (FilenameExtension.equals(".XML") || FilenameExtension.equals(".xml")
                || FilenameExtension.toUpperCase().equals(".XML")) {
            return "text/xml";
        }
        if (FilenameExtension.equals(".pdf") || FilenameExtension.equals(".PDF")
                || FilenameExtension.toUpperCase().equals(".PDF")) {
            return "application/pdf";
        }
        return null;
    }

    public static String getImgType(String contentType) {
        if(isImage(contentType)) {
            return contentType.substring("image".length()+1);
        }
        return null;
    }

    public static boolean isImage(String contentType) {
        return contentType.startsWith("image");
    }

    public static String getFileType(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

}

