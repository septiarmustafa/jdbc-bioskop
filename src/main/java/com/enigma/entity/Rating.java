package com.enigma.entity;

public class Rating {
    private Integer id;
    private String code;
    private String desc;

    public Rating(Integer id, String code, String desc) {
        this.id = id;
        this.code = code;
        this.desc = desc;
    }

    public Rating(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Rating() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Rating" +
                "id=" + id +
                ", code=" + code +
                ", desc='" + desc + '\''
                ;
    }
}
