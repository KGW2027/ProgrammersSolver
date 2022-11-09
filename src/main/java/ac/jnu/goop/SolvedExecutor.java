package ac.jnu.goop;

public class SolvedExecutor {

    private SolvedTestable instance;

    public SolvedExecutor(SolvedTestable instance) {
        this.instance = instance;
    }

    public void test() {
        instance.solution();
    }
}
