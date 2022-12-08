package com.weng.demo.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *  <p>
 *
 * @author ChuanJie.Weng
 * @date 2022-10-11
 */
@Data
 @Accessors(chain = true)
@TableName("IM_ENTERPRISE_ORGANIZATION")
public class ImEnterpriseOrganization{

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String orgid;

    @TableField("orgIds")
    private String orgIds;
}
