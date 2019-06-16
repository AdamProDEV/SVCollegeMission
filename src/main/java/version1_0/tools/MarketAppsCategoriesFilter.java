package version1_0.tools;

public enum MarketAppsCategoriesFilter {

    API_MANAGEMENT("api_management"),
    CHAT("chat"),
    CODE_QUALITY("code"),
    CODE_REVIEW("code review"),
    CONTINUOUS_INTEGRATION("continuous integration"),
    DEPENDENCY_MANAGEMENT("dependency management"),
    DEPLOYMENT("deployment");

    private final String category;

    MarketAppsCategoriesFilter(String category){
        this.category = category;
    }

    public String getCategory(){
        return category;
    }

}
