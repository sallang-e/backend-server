package sallange.server.entity;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum CycleStatus {

    AVAILABLE,
    RENT,
    BROKEN,
    ;

    public static CycleStatus from(final String status) {
        return Arrays.stream(CycleStatus.values())
                .filter(it -> it.name().equals(status))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("[ERROR] 살랑이의 상태는 AVAILABLE, RENT, BROKEN 중에서 입력해 주세요"));
    }
}
