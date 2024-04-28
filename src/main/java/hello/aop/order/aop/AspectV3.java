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
public class AspectV3 {
    @Pointcut("execution(* hello.aop.order..*(..))")
    //파라미터 들어가면 파라미터까지 맞춰주어야함.
    private void allOrder(){}

    //클래스 이름 패턴이 *Service인것 => 트랜잭션은 비지니스 로직 실행할때 트랜잭션 실행하고, 비지니스 로직 끝날때 커밋 또는 롤백
                        //
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService(){}

    @Around("allOrder()")
    //doLog는 어드바이스
    public Object doLog(ProceedingJoinPoint joinPoint) throws  Throwable {
        // 메서드 뭐 호출했는지 쭉 남음
        log.info("[log] {}", joinPoint.getSignature());

        //실제 타겟 호출
        return joinPoint.proceed();
    }

    //hello.aop.order 패키지와 하위 패키지 이면서 동시에 클래스 이름 패턴이 *Service
    @Around("allOrder() && allService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());;
            return result;
        }  catch (Exception e) {
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[리소스 릴리즈 {}]", joinPoint.getSignature());
        }
    }

    //doLog는 ordeerRepository와 orderService모두 적용
    //doTransaction은 orderService만 적용됨
}
