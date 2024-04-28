package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by I'm_joongyeon on 4/24/24.
 */
@Slf4j
@SpringBootTest
public class ArgsTest {
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        MemberServiceImpl.class.getMethod("hello", String.class);
    }


    private AspectJExpressionPointcut pointcut(String expression) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return pointcut;
    }


    @Test
    void printMethod() {
        log.info("helloMethod={}", helloMethod);
    }

}
