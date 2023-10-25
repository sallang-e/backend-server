package sallange.server.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sallange.server.api.request.RentRequest;
import sallange.server.application.RentService;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rent")
public class RentController {

    private final RentService rentService;

    @PostMapping("/{userId}")
    public ResponseEntity<Void> rent(
            @PathVariable Long userId,
            @RequestBody RentRequest request
    ) {
        final Long rentHistoryId = rentService.rent(userId, request);

        return ResponseEntity
                .created(URI.create("/rent-history/" + rentHistoryId))
                .build();
    }
}
