package sallange.server.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sallange.server.api.request.CycleStatusUpdateRequest;
import sallange.server.api.response.CycleStatusUpdateResponse;
import sallange.server.application.CycleStatusUpdateService;
import sallange.server.entity.Cycle;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/cycles")
public class CycleStatusUpdateController {

    private final CycleStatusUpdateService cycleStatusUpdateService;

    @PostMapping("/change-status")
    public ResponseEntity<CycleStatusUpdateResponse> changeStatus(
            @RequestBody CycleStatusUpdateRequest request
    ) {
        final Cycle cycle = cycleStatusUpdateService.updateCycleStatus(request.getCycleId(), request.getStatus());
        return ResponseEntity
                .ok(new CycleStatusUpdateResponse(cycle.getId(), cycle.getStatus()));
    }
}
