package interface_adapter.und_ovr;

import entity.PlayingCard;
import use_case.Over_Under.OverUnderInteractor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class OverUnderViewModel {
    private PlayingCard currentCard;
    private String guessResult;
    private String gameStatus;
    private String message;

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private OverUnderInteractor interactor;

    // Getters and Setters with PropertyChangeSupport
    public int getCurrentCardValue() {
        return currentCard.getRank();
    }

    public String getGuessResult() {
        return guessResult;
    }

    public void setGuessResult(String guessResult) {
        String oldValue = this.guessResult;
        this.guessResult = guessResult;
        propertyChangeSupport.firePropertyChange("guessResult", oldValue, guessResult);
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        final String oldValue = this.gameStatus;
        this.gameStatus = gameStatus;
        propertyChangeSupport.firePropertyChange("gameStatus", oldValue, gameStatus);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        final String oldValue = this.message;
        this.message = message;
        propertyChangeSupport.firePropertyChange("message", oldValue, message);
    }

    // Add and Remove PropertyChangeListeners
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public void setInteractor(OverUnderInteractor overUnderInteractor) {
        this.interactor = overUnderInteractor;
    }

    public String getBalance() {
        return String.valueOf(interactor.getBalance());
    }

    public PlayingCard getCurrentCard() {
        return this.currentCard;
    }

    public void setCurrentCard(PlayingCard currentCard) {
        this.currentCard = currentCard;
    }

    public void setCurrentCardValue(String cardValue) {

    }

    public void setError(String errorMessage) {
    }

    public void setBalance(int newBalance) {

    }
}
