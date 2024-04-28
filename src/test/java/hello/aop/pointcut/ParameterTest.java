package hello.aop.pointcut;

import hello.aop.member.MemberService;
import hello.aop.member.annotation.ClassAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * Created by I'm_joongyeon on 4/25/24.
 */
@Slf4j
@Import({ParameterTest.ParameterAspect.class})
@SpringBootTest
public class ParameterTest {
    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService Proxy={}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ParameterAspect {
        @Pointcut("execution(* hello.aop.member..*..*(..))")
        private void allMember() {

        }
        @Around("allMember()")
        public Object logArgs1(ProceedingJoinPoint joinPoint) throws Throwable {
            Object arg = joinPoint.getArgs()[0];
            log.info("[logArgs1]{} args = {}", joinPoint.getSignature(), arg);
            return joinPoint.proceed();
        }

        @Before("allMember() && target(obj)")
        public void targetArgs(ProceedingJoinPoint joinPoint, MemberService obj) {
            log.info("[targetArgs]{} args = {}", joinPoint.getSignature(), obj.getClass());
        }

        @Before("allMember() && @target(annotation)")
        public void atTarget(ProceedingJoinPoint joinPoint, ClassAop annotation) {
            log.info("[@target]{} obj = {}", joinPoint.getSignature(), annotation);
        }
    }
}
