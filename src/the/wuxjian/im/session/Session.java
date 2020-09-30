package the.wuxjian.im.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wuxjian on 2020/9/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    private String userId;
    private String username;

}
