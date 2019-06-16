package version1_0.tools;

public enum FindingAppBySearchOrManual {

    BY_SEARCH("search"),
    BY_MANUAL("manual");

    private final String howTo;


    FindingAppBySearchOrManual(String howTo) {
        this.howTo = howTo;

    }

    public String gethowTo() {
        return howTo;
    }




}
