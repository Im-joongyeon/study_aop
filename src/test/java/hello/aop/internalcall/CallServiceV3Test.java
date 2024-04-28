package hello.aop.internalcall;

import hello.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * Created by I'm_joongyeon on 4/26/24.
 */
@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV3Test {

    @Autowired
    CallServiceV2 callService;

    @Test
    void external() {
        callService.external();
    }

}