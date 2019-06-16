package version1_0.tools;

public enum  MarketAppsCostFilter {

    FREE("free"),
    FREE_TRIAL("free trial");

    private final String cost;


    MarketAppsCostFilter(String cost) {
        this.cost = cost;
    }

    public String getCost() {
        return cost;
    }
}
