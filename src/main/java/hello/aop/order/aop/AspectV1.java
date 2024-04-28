package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by I'm_joongyeon on 4/24/24.
 */
@Slf4j
@Aspect
public class AspectV1 {
            // 아래 표현식 포인트컷
    @Around("execution(* hello.aop.order..*(..))")
                //doLog는 어드바이스
    public Object doLog(ProceedingJoinPoint joinPoint) throws  Throwable {
        // 메서드 뭐 호출했는지 쭉 남음
        log.info("[log] {}", joinPoint.getSignature());

        //실제 타겟 호출
        return joinPoint.proceed();
    }

    //위 함수의 결과로 hello.aop.order 밑의 모든 메서드(스프링이관리하는)는 AOP적용의 대상이 된다.
}
