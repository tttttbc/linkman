package com.example.device_weixiu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * @TableName spare
 */
@TableName(value = "spare")
@Data
public class Spare implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField(value = "info")
    private String info;

    /**
     *
     */
    @TableField(value = "state")
    private String state;
    @TableField(value = "num")
    private Integer num;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}