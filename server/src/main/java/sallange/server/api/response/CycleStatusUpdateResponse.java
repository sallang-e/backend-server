package sallange.server.api.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sallange.server.entity.CycleStatus;

@RequiredArgsConstructor
@Getter
public class CycleStatusUpdateResponse {

    private final Long cycleID;
    private final CycleStatus status;
}
