package sallange.server.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sallange.server.api.request.RentRequest;
import sallange.server.entity.Cycle;
import sallange.server.entity.CycleStatus;
import sallange.server.entity.RentHistory;
import sallange.server.entity.RentType;
import sallange.server.entity.Users;
import sallange.server.repository.CycleRepository;
import sallange.server.repository.RentHistoryRepository;
import sallange.server.repository.UsersRepository;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class RentService {

    private final UsersRepository usersRepository;
    private final CycleRepository cycleRepository;
    private final RentHistoryRepository rentHistoryRepository;

    public Long rent(final Long userId, final RentRequest request) {
        final Users users = usersRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);

        if (!users.isAvailable()) {
            throw new IllegalStateException("[ERROR] 살랑이 사용 가능 회수가 없는 유저입니다!");
        }

        final Long cycleId = decryptCycleId(request.getCycleID());
        final Cycle cycle = cycleRepository.findByIdAndStatus(cycleId, CycleStatus.AVAILABLE)
                  .orElseThrow(() -> new IllegalStateException("[ERROR] 해당 살랑이는 고장났거나, 비활성화 상태입니다."));

        final RentHistory rentHistory = rentHistoryRepository.save(new RentHistory(users.getId(), cycle.getId(), RentType.RENT));
        return rentHistory.getId();
    }

    private Long decryptCycleId(final String encryptedCycleId) {
        return Long.valueOf(encryptedCycleId);
    }
}
