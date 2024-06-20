package indiv.pechola.wms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "User")
public class User {
    private int id;
    private String no;
    private String name;
    private String password;
    private int age;
    private int sex;
    @TableField("phone")
    private String phoneNumber;  // 手动映射到"phone"
    private int roleId;  // 会自动映射到带下划线的"role_id"
}
