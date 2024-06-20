package indiv.pechola.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indiv.pechola.wms.entity.User;
import indiv.pechola.wms.mapper.UserMapper;
import indiv.pechola.wms.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> listAll() {
        return userMapper.listAll();
    }
}
