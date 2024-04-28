package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by I'm_joongyeon on 4/26/24.
 */
@Slf4j
@Component
public class CallServiceV2 {

    //ApplicationContext는 기능이 너무 많아...
//    private final ApplicationContext applicationContext;

    private final ObjectProvider<CallServiceV2> callServiceV2Provider;

    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceV2Provider) {
        this.callServiceV2Provider = callServiceV2Provider;
    }

    public void external() {
        log.info("call external");
//        CallServiceV2 callServiceV2 = applicationContext.getBean(CallServiceV2.class);
//        callServiceV2.internal(); // 내부 메서도 호출(this.internal())

        CallServiceV2 callServiceV2 = callServiceV2Provider.getObject();
        callServiceV2.internal();


    }

    public void internal() {
        log.info("call internal");
    }
}
