package indiv.pechola.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import indiv.pechola.wms.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> listAll();
}
