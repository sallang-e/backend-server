package sallange.server.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sallange.server.entity.Cycle;
import sallange.server.entity.CycleStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class CycleRepositoryTest {

    @Autowired
    private CycleRepository cycleRepository;

    @Test
    void 특정_아이디와_상태로_자전거를_조회한다() {
        final Cycle availableCycle = cycleRepository.save(new Cycle(CycleStatus.AVAILABLE));
        final Cycle brokenCycle = cycleRepository.save(new Cycle(CycleStatus.BROKEN));

        final Long availableCycleId = availableCycle.getId();
        final Long brokenCycleId = brokenCycle.getId();

        final Optional<Cycle> findAvailableCycle = cycleRepository.findByIdAndStatus(availableCycleId, CycleStatus.AVAILABLE);
        final Optional<Cycle> findBrokenCycle = cycleRepository.findByIdAndStatus(brokenCycleId, CycleStatus.AVAILABLE);

        assertAll(
                () -> assertThat(findAvailableCycle).isNotEmpty(),
                () -> assertThat(findAvailableCycle.get())
                        .usingRecursiveComparison()
                        .isEqualTo(availableCycle),
                () -> assertThat(findBrokenCycle).isEmpty()
        );
    }
}
