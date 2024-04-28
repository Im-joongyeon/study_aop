package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by I'm_joongyeon on 4/26/24.
 */
@Slf4j
@Component
public class CallServiceV1 {
    private CallServiceV1 callService1;


    @Autowired
    //생성자 주입이아니다.
    public void setCallService1(CallServiceV1 callService1) {
        log.info("callServiceV1 setter={}", callService1.getClass());
        this.callService1 = callService1;
    }

    public void external() {
        log.info("call external");
        callService1.internal(); // 내부 메서도 호출(this.internal())
    }

    public void internal() {
        log.info("call internal");
    }
}
