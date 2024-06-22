package indiv.pechola.wms.mapper;

import indiv.pechola.wms.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pechola
 * @since 2024-06-22
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
