package com.gg.file;

import java.io.Serializable;
import java.util.Arrays;

/***
 * 用户上传的文件信息
 */
public class FastDFSFile implements Serializable {
    private String name;//文件名
    private byte[] content;//文件内容
    private String ext;//文件扩展名
    private String md5;//文件md5摘要值
    private String author;//文件作者

    public FastDFSFile() {
    }

    public FastDFSFile(String name, byte[] content, String ext) {
        this.name = name;
        this.content = content;
        this.ext = ext;
    }

    public FastDFSFile(String name, byte[] content, String ext, String md5) {
        this.name = name;
        this.content = content;
        this.ext = ext;
        this.md5 = md5;
    }

    public FastDFSFile(String name, byte[] content, String ext, String md5, String author) {
        this.name = name;
        this.content = content;
        this.ext = ext;
        this.md5 = md5;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "FastDFSFile{" +
                "name='" + name + '\'' +
                ", content=" + Arrays.toString(content) +
                ", ext='" + ext + '\'' +
                ", md5='" + md5 + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
