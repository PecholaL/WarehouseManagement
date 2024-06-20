package indiv.pechola.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indiv.pechola.wms.entity.User;
import indiv.pechola.wms.mapper.UserMapper;
import indiv.pechola.wms.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
