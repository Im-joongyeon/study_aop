package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by I'm_joongyeon on 4/26/24.
 */
@Slf4j
@Component
public class InternalService {

    public void internal() {
        log.info("call internal");
    }
}
