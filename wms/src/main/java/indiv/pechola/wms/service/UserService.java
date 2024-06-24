package indiv.pechola.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indiv.pechola.wms.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pechola
 * @since 2024-06-22
 */
public interface UserService extends IService<User> {

    IPage pageC(Page<User> page, Wrapper wrapper);
}
