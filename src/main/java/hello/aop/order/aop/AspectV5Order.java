package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

/**
 * Created by I'm_joongyeon on 4/24/24.
 */
@Slf4j
public class AspectV5Order {

    @Aspect
    @Order(2)
    public static class LogAspect {
        @Around("hello.aop.order.aop.Pointcuts.allOrder()")
        //doLog는 어드바이스
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            // 메서드 뭐 호출했는지 쭉 남음
            log.info("[log] {}", joinPoint.getSignature());
            //실제 타겟 호출
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(1)
    public static class TransactionAspect {
        //hello.aop.order 패키지와 하위 패키지 이면서 동시에 클래스 이름 패턴이 *Service
        @Around("hello.aop.order.aop.Pointcuts.orderAndService()")
        public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
            try {
                log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
                ;
                return result;
            } catch (Exception e) {
                log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
                throw e;
            } finally {
                log.info("[리소스 릴리즈 {}]", joinPoint.getSignature());
            }
        }
    }
}
