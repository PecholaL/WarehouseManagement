package indiv.pechola.wms.entity;

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
 * @since 2024-07-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Menu对象", description="")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String id;

    @TableField("menuCode")
    private String menucode;

    @TableField("menuName")
    private String menuname;

    @TableField("menuLevel")
    private String menulevel;

    @TableField("menuParentCode")
    private String menuparentcode;

    @TableField("menuClick")
    private String menuclick;

    @TableField("menuRight")
    private String menuright;

    @TableField("menuComponent")
    private String menucomponent;

    @TableField("menuIcon")
    private String menuicon;


}
