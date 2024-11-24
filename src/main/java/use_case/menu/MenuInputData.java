package use_case.menu;

/**
 * The Input Data for the Welcome Use Case.
 */
public class MenuInputData {
    private final String name;

    public MenuInputData(String username) {
        name = username;
    }

    public String getName() {
        return name;
    }
}
