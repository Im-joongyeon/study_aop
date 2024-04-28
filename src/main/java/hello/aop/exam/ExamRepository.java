package hello.aop.exam;

import hello.aop.exam.annotation.Retry;
import hello.aop.exam.annotation.Trace;
import org.springframework.stereotype.Repository;

/**
 * Created by I'm_joongyeon on 4/26/24.
 */
@Repository
public class ExamRepository {
    private static int seq = 0;

    /***
     * 5번에 1번 실패하는 요청
     */

    @Trace
    @Retry(4)
    String save(String itemId) {
        seq++;
        if(seq % 5 == 0) {
            throw new IllegalStateException("예외 발생");
        }
        return "ok";
    }

}
