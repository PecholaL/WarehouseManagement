package indiv.pechola.wms.service.impl;

import indiv.pechola.wms.entity.User;
import indiv.pechola.wms.mapper.UserMapper;
import indiv.pechola.wms.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pechola
 * @since 2024-06-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
