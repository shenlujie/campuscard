package org.slj.domain;

import javax.persistence.*;

/**
 * @Description: 角色
 * @create: 2019/5/12
 * @Author: SLJ
 */
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色类型
     */
    @Column(name = "type")
    private String type;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色类型
     *
     * @return type - 角色类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置角色类型
     *
     * @param type 角色类型
     */
    public void setType(String type) {
        this.type = type;
    }
}
