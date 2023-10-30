package sallange.server.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RentRequest {

    private String cycleID;

    public static RentRequest from(final Long cycleId) {
        final String encryptedCycleId = String.valueOf(cycleId);
        return new RentRequest(encryptedCycleId);
    }
}
