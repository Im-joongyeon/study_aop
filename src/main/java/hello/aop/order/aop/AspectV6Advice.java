package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

/**
 * Created by I'm_joongyeon on 4/24/24.
 */
@Slf4j
public class AspectV6Advice {

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

    @Before("hello.aop.order.aop.Pointcuts.orderAndService()")
    //ProceedingJoinPoint는 쓸 수없음 => Around에서만 사용가능
    public void doBefore(JoinPoint joinPoint) {
        //@Before는 proceed를 해주기때문에 내가 실행시키지 않아도 됨.
        log.info("[before] {}", joinPoint.getSignature());
    }
                                                                                //여기 result가 아래 함수 parameter(Obejct)에 매핑됨
    @AfterReturning(value="hello.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("[return] {} return = {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value="hello.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {} message = {}", ex);
    }
    @After(value="hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }
}
