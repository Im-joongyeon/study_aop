package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by I'm_joongyeon on 4/24/24.
 */
@Slf4j
@Aspect

//포인트컷을 따로 분리
public class AspectV2 {

    @Pointcut("execution(* hello.aop.order..*(..))")
    //파라미터 들어가면 파라미터까지 맞춰주어야함.
    private void allOrder(){}

    @Around("allOrder()")
    //doLog는 어드바이스
    public Object doLog(ProceedingJoinPoint joinPoint) throws  Throwable {
        // 메서드 뭐 호출했는지 쭉 남음
        log.info("[log] {}", joinPoint.getSignature());

        //실제 타겟 호출
        return joinPoint.proceed();
    }

    //doLog2가 있으면 allOrder()하나로 사용 가능
}
