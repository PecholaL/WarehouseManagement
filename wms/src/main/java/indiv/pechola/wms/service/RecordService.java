package indiv.pechola.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indiv.pechola.wms.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pechola
 * @since 2024-07-09
 */
public interface RecordService extends IService<Record> {

    IPage pageCustom(Page<Record> page, Wrapper wrapper);
}
