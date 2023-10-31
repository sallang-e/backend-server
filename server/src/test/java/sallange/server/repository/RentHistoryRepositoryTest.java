package sallange.server.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sallange.server.auth.OAuthProvider;
import sallange.server.entity.Cycle;
import sallange.server.entity.CycleStatus;
import sallange.server.entity.RentHistory;
import sallange.server.entity.RentType;
import sallange.server.entity.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class RentHistoryRepositoryTest {

    @Autowired
    private RentHistoryRepository rentHistoryRepository;

    @Autowired
    private CycleRepository cycleRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    void 유저_아이디와_대여_타입으로_조회한다() {
        final Cycle cycle1 = cycleRepository.save(new Cycle(CycleStatus.AVAILABLE));
        final Cycle cycle2 = cycleRepository.save(new Cycle(CycleStatus.AVAILABLE));
        final Cycle cycle3 = cycleRepository.save(new Cycle(CycleStatus.AVAILABLE));
        final Cycle cycle4 = cycleRepository.save(new Cycle(CycleStatus.AVAILABLE));

        final User gitchan = usersRepository.save(new User("깃짱", OAuthProvider.KAKAO, 123L, 2));
        final User hoonchan = usersRepository.save(new User("훈짱", OAuthProvider.KAKAO, 124L, 1));

        final RentHistory rentHistory = rentHistoryRepository.save(new RentHistory(
                gitchan.getId(),
                cycle1.getId(),
                RentType.RENT
        ));

        final RentHistory returnHistory = rentHistoryRepository.save(new RentHistory(
                gitchan.getId(),
                cycle2.getId(),
                RentType.RETURN
        ));

        final Optional<RentHistory> gitchanRentHistory = rentHistoryRepository.findByUserIdAndType(gitchan.getId(), RentType.RENT);
        final Optional<RentHistory> hoonchanRentHistory = rentHistoryRepository.findByUserIdAndType(hoonchan.getId(), RentType.RENT);

        assertAll(
                () -> assertThat(gitchanRentHistory).isNotEmpty(),
                () -> assertThat(gitchanRentHistory.get())
                        .usingRecursiveComparison()
                        .isEqualTo(rentHistory),
                () -> assertThat(hoonchanRentHistory).isEmpty()
        );
    }
}
