package indiv.pechola.wms.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author pechola
 * @since 2024-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Record对象", description="")
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private String id;

    private Integer goods;

    @TableField("userId")
    private Integer userid;

    @TableField("adminId")
    private Integer adminid;

    private Integer count;

    @TableField("createTime")
    private LocalDateTime createtime;

    private String note;

    @TableField(exist = false)
    private String inOrOut;
}
