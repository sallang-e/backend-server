package sallange.server.api.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CycleStatusUpdateRequest {

    private final Long cycleId;
    private final String status;
}
