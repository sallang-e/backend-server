package sallange.server.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sallange.server.api.request.CycleReturnRequest;
import sallange.server.application.CycleReturnService;
import sallange.server.config.argumentresolver.UserAuth;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class CycleReturnController {

    private final CycleReturnService cycleReturnService;

    @PostMapping("/cycles/return-cycle")
    public ResponseEntity<Void> returnCycle(
            UserAuth user
    ) {
        cycleReturnService.returnCycle(user.getId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/cycles/return-cycle")
    public ResponseEntity<Void> returnCycle(
            @RequestBody CycleReturnRequest request
    ) {
        cycleReturnService.returnCycleFromCycle(request.getCycleID());
        return ResponseEntity.ok().build();
    }
}
