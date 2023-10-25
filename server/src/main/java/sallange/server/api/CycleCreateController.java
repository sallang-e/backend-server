package sallange.server.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sallange.server.entity.Cycle;
import sallange.server.entity.CycleStatus;
import sallange.server.repository.CycleRepository;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cycles")
public class CycleCreateController {

    private final CycleRepository cycleRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> createCycle() {
        final Cycle cycle = cycleRepository.save(
                new Cycle(CycleStatus.AVAILABLE)
        );

        return ResponseEntity
                .created(URI.create("/cycles/" + cycle.getId()))
                .build();
    }
}
