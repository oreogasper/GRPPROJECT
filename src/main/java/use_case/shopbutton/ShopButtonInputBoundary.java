package use_case.shopbutton;

/**
 * Input Boundary for actions which are related to the ShopButton screen.
 */
public interface ShopButtonInputBoundary {

    /**
     * Executes the switch to menu view use case (exit).
     */
    void switchToShopView();

    /**
     * Executes the click of the button.
     * @param clicksMade is the current number of clicks.
     */
    void buttonClick(int clicksMade);

    void saveData(ShopButtonInputData shopButtonInputData, Integer newBalance);

}
