package hello.aop.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by I'm_joongyeon on 4/24/24.
 */
@Target(ElementType.TYPE) //클래스에 붙이는 어노테이션
@Retention(RetentionPolicy.RUNTIME) //런타임 실행할떄까지 어노테이션이 살아있게하는데 이렇게해야 인식할 수 있게함
public @interface ClassAop {
}
