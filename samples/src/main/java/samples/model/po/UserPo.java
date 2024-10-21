package samples.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description:
 * @Author: lyflexi
 * @project: handson-mulit-tenant
 * @Date: 2024/10/21 16:24
 */
@Data
@TableName("user")
public class UserPo {
    private Long id;
    private String name;
}
