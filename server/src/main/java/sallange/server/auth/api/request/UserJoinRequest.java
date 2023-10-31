package sallange.server.auth.api.request;

public class UserJoinRequest {

    private final String name;
    private final String oAuthProvider;
    private final Long oAuthId;
    private final Integer leftRentCount;

    public UserJoinRequest(final String name, final String oAuthProvider, final Long oAuthId, final Integer leftRentCount) {
        this.name = name;
        this.oAuthProvider = oAuthProvider;
        this.oAuthId = oAuthId;
        this.leftRentCount = leftRentCount;
    }

    public String getName() {
        return name;
    }

    public String getoAuthProvider() {
        return oAuthProvider;
    }

    public Long getoAuthId() {
        return oAuthId;
    }

    public Integer getLeftRentCount() {
        return leftRentCount;
    }

    @Override
    public String toString() {
        return "UserJoinRequest{" +
                "name='" + name + '\'' +
                ", oAuthProvider='" + oAuthProvider + '\'' +
                ", oAuthId=" + oAuthId +
                ", leftRentCount=" + leftRentCount +
                '}';
    }
}
