package com.xiaocai.base.drools;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/1/25 17:08
 */
public class EntityInfoVo implements Serializable {
    private Long crowdCode;
    /**
     * 是否命中
     * true：命中
     * false:不命中
     */
    private Boolean hit;
    /**
     * 值说明
     */
    private String valueDes;
    /**
     * 规则说明
     */
    private String ruleDes;
    public Long getCrowdCode() {
        return crowdCode;
    }
    public void setCrowdCode(Long crowdCode) {
        this.crowdCode = crowdCode;
    }
    public Boolean getHit() {
        return hit;
    }
    public void setHit(Boolean hit) {
        this.hit = hit;
    }
    public String getValueDes() {
        return valueDes;
    }
    public void setValueDes(String valueDes) {
        this.valueDes = valueDes;
    }
    public String getRuleDes() {
        return ruleDes;
    }
    public void setRuleDes(String ruleDes) {
        this.ruleDes = ruleDes;
    }
    public EntityInfoVo(Long crowdCode, Boolean hit, String valueDes, String ruleDes) {
        this.crowdCode = crowdCode;
        this.hit = hit;
        this.valueDes = valueDes;
        this.ruleDes = ruleDes;
    }

    public EntityInfoVo() {
    }

}


