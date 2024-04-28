package hello.aop.order.aop;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by I'm_joongyeon on 4/24/24.
 */
public class Pointcuts {
    @Pointcut("execution(* hello.aop.order..*(..))")
    //파라미터 들어가면 파라미터까지 맞춰주어야함.
    public void allOrder(){}

    //클래스 이름 패턴이 *Service인것 => 트랜잭션은 비지니스 로직 실행할때 트랜잭션 실행하고, 비지니스 로직 끝날때 커밋 또는 롤백
    //
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}

    @Pointcut("allOrder() && allService()")
    public void orderAndService() {}
}
