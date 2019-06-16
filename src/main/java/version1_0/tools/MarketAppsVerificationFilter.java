package version1_0.tools;

public enum MarketAppsVerificationFilter {

    VERIFIED("Verified"),
    UNVERFIED("Unverified");

    private final String status;

    MarketAppsVerificationFilter(String status) {
        this.status =status;
    }

    public String getStatus() {
        return status;
    }
}
