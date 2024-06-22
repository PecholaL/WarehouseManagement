package indiv.pechola.wms.entity;

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
 * @author pechola
 * @since 2024-06-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "user id")
    private String no;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "password")
    private String password;

    @ApiModelProperty(value = "age")
    private Integer age;

    @ApiModelProperty(value = "sex, 0-female, 1-male")
    private Integer sex;

    @ApiModelProperty(value = "phone number")
    private String phone;

    @ApiModelProperty(value = "role, 0-owner, 1-administrator, 2-user")
    private Integer roleId;


}
