package hello.aop.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * Created by I'm_joongyeon on 4/23/24.
 */

@Slf4j
@Repository
public class OrderRepository {

    public String save(String itemId) {
        log.info("[orderRepository] 실행");
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생 !");
        }
        return "ok";
    }
}
