package com.kokmobi.server.bean;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/10/25
 *          Time: 19:13
 * @author: mm
 * @since 3.0
 */
public class Platform {
    private int id;
    private String name;
    private String describe;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
