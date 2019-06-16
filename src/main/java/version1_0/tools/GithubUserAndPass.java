package version1_0.tools;

public enum GithubUserAndPass {
    USERNAME("svcollegeta"),
    PASSWORD("SVCollege123");

    private String value;
    GithubUserAndPass(String value) {
        this.value =value;
    }

    public String getValue() {
        return value;
    }
}
