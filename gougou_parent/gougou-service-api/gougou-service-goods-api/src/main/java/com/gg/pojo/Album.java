package com.gg.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 相册
 */
@Table(name = "tb_album")
public class Album implements Serializable {
    @Id
    private Integer id;
    private String title;
    private String image;
    @Column(name = "image_items")
    private String imageItems;

    public Album() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageItems() {
        return imageItems;
    }

    public void setImageItems(String imageItems) {
        this.imageItems = imageItems;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", imageItems='" + imageItems + '\'' +
                '}';
    }
}
