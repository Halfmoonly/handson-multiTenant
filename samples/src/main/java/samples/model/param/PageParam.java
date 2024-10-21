package samples.model.param;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @Author: ly
 * @Date: 2024/6/18 22:27
 */
@Data
public class PageParam<T> extends Page<T> {
    // 默认页码
    private static final long DEFAULT_CURRENT = 1;
    // 默认每页条数
    private static final long DEFAULT_SIZE = 10;

    // 扩展字段：其他查询条件参数可以添加到这里
    private String orderBy; // 排序字段
    private boolean asc;    // 是否升序

    // 默认构造函数
    public PageParam() {
        super(DEFAULT_CURRENT, DEFAULT_SIZE);
    }

    // 自定义构造函数
    public PageParam(long current, long size) {
        super(current, size);
    }

    public IPage<T> getPage() {
        return new Page<>(getCurrent(), getSize());
    }

    // 添加更多的参数逻辑，如果需要的话
}