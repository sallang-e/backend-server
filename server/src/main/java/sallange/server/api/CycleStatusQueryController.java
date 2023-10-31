package sallange.server.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sallange.server.api.request.CycleStatusQueryRequest;
import sallange.server.api.response.CycleStatusQueryResponse;
import sallange.server.application.CycleStatusQueryService;
import sallange.server.entity.CycleStatus;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/cycles")
public class CycleStatusQueryController {

    private final CycleStatusQueryService cycleStatusQueryService;

    @GetMapping("/status")
    public ResponseEntity<CycleStatusQueryResponse> queryCycleStatus(
            @RequestBody CycleStatusQueryRequest request
    ) {
        final CycleStatus status = cycleStatusQueryService.queryCycleStatus(request.getCycleID());
        return ResponseEntity.ok(new CycleStatusQueryResponse(status.name()));
    }
}
