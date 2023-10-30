package sallange.server.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sallange.server.application.CycleReturnService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/return-cycle")
public class CycleReturnController {

    private final CycleReturnService cycleReturnService;

    @PostMapping("/{userId}")
    public ResponseEntity<Void> returnCycle(@PathVariable Long userId) {
        cycleReturnService.returnCycle(userId);
        return ResponseEntity.ok().build();
    }
}
