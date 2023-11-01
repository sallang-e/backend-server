package sallange.server.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sallange.server.application.CycleReturnService;
import sallange.server.config.argumentresolver.UserAuth;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cycles/return-cycle")
public class CycleReturnController {

    private final CycleReturnService cycleReturnService;

    @PostMapping
    public ResponseEntity<Void> returnCycle(
            UserAuth user
    ) {
        cycleReturnService.returnCycle(user.getId());
        return ResponseEntity.ok().build();
    }
}
