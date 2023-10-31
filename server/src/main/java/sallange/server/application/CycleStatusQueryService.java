package sallange.server.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sallange.server.entity.Cycle;
import sallange.server.entity.CycleStatus;
import sallange.server.repository.CycleRepository;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class CycleStatusQueryService {

    private final CycleRepository cycleRepository;

    public CycleStatus queryCycleStatus(final Long cycleID) {
        return findCycle(cycleID).getStatus();
    }

    private Cycle findCycle(final Long cycleID) {
        return cycleRepository.findById(cycleID)
                .orElseThrow(() -> new NoSuchElementException("[ERROR] 해당 살랑이는 존재하지 않습니다."));
    }
}
