package sallange.server.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/health-check")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<String> heathCheck() {
        return ResponseEntity.ok("up");
    }
}
