package com.txxw.sso.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Enci
 * @since 2023-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tx_access")
@ApiModel(value="Access对象", description="")
public class Access implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "项目名称")
    private String name;

    @ApiModelProperty(value = "访问链接")
    private String url;


}
