package sallange.server.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sallange.server.api.request.RentRequest;
import sallange.server.entity.Cycle;
import sallange.server.entity.RentHistory;
import sallange.server.entity.RentType;
import sallange.server.entity.Users;
import sallange.server.exception.RentException;
import sallange.server.repository.CycleRepository;
import sallange.server.repository.RentHistoryRepository;
import sallange.server.repository.UsersRepository;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class CycleRentService {

    private final UsersRepository usersRepository;
    private final CycleRepository cycleRepository;
    private final RentHistoryRepository rentHistoryRepository;

    public Long rent(final Long userId, final RentRequest request) {
        final Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("[ERROR] 해당 아이디의 유저는 존재하지 않습니다."));

        if (!user.isAvailable()) {
            throw new RentException(3, "[ERROR] 살랑이 사용 가능 회수가 없는 유저입니다!");
        }

        if (rentHistoryRepository.findByUserIdAndType(userId, RentType.RENT).isPresent()) {
            throw new RentException(4, "[ERROR] 해당 유저는 이미 살랑이를 대여중입니다!");
        }

        final Long cycleId = decryptCycleId(request.getCycleID());
        final Cycle cycle = cycleRepository.findById(cycleId).orElseThrow(() -> new NoSuchElementException("[ERROR] 존재하지 않는 살랑이입니다."));

        if (cycle.isRent()) {
            throw new RentException(1, "[ERROR] 다른 사용자가 이미 사용중인 살랑이입니다!");
        }

        if (cycle.isBroken()) {
            throw new RentException(2, "[ERROR] 해당 살랑이는 고장났거나, 비활성화 상태입니다.");
        }

        cycle.rent();
        user.reduceRentCount();

        final RentHistory rentHistory = rentHistoryRepository.save(new RentHistory(user.getId(), cycle.getId(), RentType.RENT));
        return rentHistory.getId();
    }

    private Long decryptCycleId(final String encryptedCycleId) {
        return Long.valueOf(encryptedCycleId);
    }
}
