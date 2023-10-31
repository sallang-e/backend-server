package sallange.server.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sallange.server.api.request.CycleStatusUpdateRequest;
import sallange.server.application.CycleStatusUpdateService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/cycles")
public class CycleStatusUpdateController {

    private final CycleStatusUpdateService cycleStatusUpdateService;

    @PatchMapping("/change-status")
    public ResponseEntity<Void> changeStatus(
            @RequestBody CycleStatusUpdateRequest request
    ) {
        cycleStatusUpdateService.updateCycleStatus(request.getCycleId(), request.getStatus());
        return ResponseEntity.ok().build();
    }
}
