package hello.aop.exam;

import hello.aop.exam.annotation.Trace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by I'm_joongyeon on 4/26/24.
 */
@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;

    @Trace
    public void request(String itemId) {
        examRepository.save(itemId);
    }
}
