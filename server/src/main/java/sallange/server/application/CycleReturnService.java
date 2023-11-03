package sallange.server.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sallange.server.entity.Cycle;
import sallange.server.entity.RentHistory;
import sallange.server.entity.RentType;
import sallange.server.entity.User;
import sallange.server.repository.CycleRepository;
import sallange.server.repository.RentHistoryRepository;
import sallange.server.repository.UserRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class CycleReturnService {

    private final UserRepository userRepository;
    private final RentHistoryRepository rentHistoryRepository;
    private final CycleRepository cycleRepository;

    public void returnCycle(final Long userId) {
        final User user = findUser(userId);
        final RentHistory rentHistory = findRentHistory(user);
        final Cycle cycle = findCycle(rentHistory.getCycleId());
        rentHistory.returnCycle();
        cycle.available();
    }

    public void returnCycleFromCycle(final Long cycleId) {
        final Cycle cycle = findCycle(cycleId);
        final RentHistory rentHistory = findRentHistory(cycleId);
        final Long userId = rentHistory.getUserId();
        final User user = findUser(userId);

        rentHistory.returnCycle();
        cycle.available();
    }

    private RentHistory findRentHistory(final Long cycleID) {
        final Optional<RentHistory> rentHistory = rentHistoryRepository.findByCycleIdAndType(cycleID, RentType.RENT);
        if (rentHistory.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 대여 이력이 없는 자전거는 반납할 수 없습니다.");
        }

        return rentHistory.get();
    }

    private User findUser(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("[ERROR] 해당 아이디의 유저는 존재하지 않습니다."));
    }

    private RentHistory findRentHistory(final User user) {
        return rentHistoryRepository.findByUserIdAndType(user.getId(), RentType.RENT)
                .orElseThrow(() -> new NoSuchElementException("[ERROR] 해당 유저가 현재 대여중인 살랑이가 없습니다."));
    }

    private Cycle findCycle(final Long cycleId) {
        return cycleRepository.findById(cycleId).orElseThrow(() -> new NoSuchElementException("[ERROR] 해당 살랑이는 존재하지 않습니다."));
    }
}
