package indiv.pechola.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indiv.pechola.wms.entity.Record;
import indiv.pechola.wms.mapper.RecordMapper;
import indiv.pechola.wms.service.RecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pechola
 * @since 2024-07-09
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Resource
    private RecordMapper recordMapper;

    @Override
    public IPage pageCustom(Page<Record> page, Wrapper wrapper) {
        return recordMapper.pageCustom(page, wrapper);
    }
}
