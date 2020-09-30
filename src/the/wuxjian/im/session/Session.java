package the.wuxjian.im.session;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by wuxjian on 2020/9/30
 */
@Data
@AllArgsConstructor
public class Session {
    private String userId;
    private String username;

}
