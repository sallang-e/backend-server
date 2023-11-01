package sallange.server.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sallange.server.api.request.RentRequest;
import sallange.server.application.CycleRentService;
import sallange.server.config.argumentresolver.UserAuth;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cycles/rent")
public class CycleRentController {

    private final CycleRentService cycleRentService;

    @PostMapping
    public ResponseEntity<Void> rent(
            UserAuth user,
            @RequestBody RentRequest request
    ) {
        final Long rentHistoryId = cycleRentService.rent(user.getId(), request);

        return ResponseEntity
                .created(URI.create("/rent-history/" + rentHistoryId))
                .build();
    }
}
