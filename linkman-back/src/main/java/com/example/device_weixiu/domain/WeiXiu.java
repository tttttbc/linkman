package com.example.device_weixiu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName wei_xiu
 */
@TableName(value ="wei_xiu")
@Data
public class WeiXiu implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "bao_xiu_id")
    private String baoXiuId;

    /**
     * 
     */
    @TableField(value = "info")
    private String info;

    /**
     * 
     */
    @TableField(value = "niu_ma")
    private String niuMa;

    /**
     * 
     */
    @TableField(value = "state")
    private String state;

    /**
     * 
     */
    @TableField(value = "money")
    private Integer money;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}