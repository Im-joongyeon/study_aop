package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by I'm_joongyeon on 4/26/24.
 */
@Slf4j
@SpringBootTest
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); // JDK 동적 프록시

        //프록시를 인터페이스로 캐스팅 => 성공
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        //불가. 이유는 ? 위 프록시는 인터페이스를 바탕으로 만든거기때문에 인터페이스를 구현한 인스턴스에 대한 정보가 없기 때문에.
//        MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;

    }


    @Test
    void cglibPRoxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);

        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;

    }

}
