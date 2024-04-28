package hello.aop.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

/**
 * Created by I'm_joongyeon on 4/26/24.
 */

@Slf4j
@Component
@RequiredArgsConstructor
//찐이다 V3가 주로 사용됨. => 구조를 분리
public class CallServiceV3 {

    private final InternalService internalService;
    public void external() {
        log.info("call external");
        internalService.internal();
    }


}
