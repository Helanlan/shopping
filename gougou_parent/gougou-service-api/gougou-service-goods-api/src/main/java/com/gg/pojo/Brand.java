package com.gg.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 品牌
 */
@Table(name = "tb_brand")
public class Brand implements Serializable {

    @Id
    private Integer id;
    private String name;
    private String image;
    private String letter;
    private Integer seq;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return "Brand{" + "id=" + id + ", name='" + name + '\'' + ", image='" + image + '\'' + ", letter='" + letter + '\'' + ", seq=" + seq + '}';
    }
}
