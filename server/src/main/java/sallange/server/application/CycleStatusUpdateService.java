package sallange.server.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sallange.server.entity.Cycle;
import sallange.server.entity.CycleStatus;
import sallange.server.repository.CycleRepository;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class CycleStatusUpdateService {

    private final CycleRepository cycleRepository;

    public Cycle updateCycleStatus(final Long cycleId, final String status) {
        final Cycle cycle = findCycle(cycleId);
        final CycleStatus cycleStatus = CycleStatus.from(status);

        if (cycle.getStatus() == cycleStatus) {
            throw new IllegalArgumentException("[ERROR] 현재와 동일한 상태이므로 변경 불가합니다!");
        }

        if (cycleStatus == CycleStatus.RENT) {
            throw new IllegalArgumentException("[ERROR] 어드민에서 업데이트 가능한 상태는 BROKEN, AVAILABLE만 가능합니다.");
        }

        cycle.changeStatus(cycleStatus);
        return cycle;
    }

    private Cycle findCycle(final Long cycleId) {
        return cycleRepository.findById(cycleId)
                .orElseThrow(() -> new NoSuchElementException("[ERROR] 해당 살랑이는 존재하지 않습니다."));
    }
}
