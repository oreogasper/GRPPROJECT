package use_case.shopwheel;

import entity.User;
import entity.UserFactory;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * The Shop wheel Interactor.
 */
public class ShopWheelInteractor implements ShopWheelInputBoundary {
    private static final int NUM_SEGMENTS = 16;
    protected static final Map<Integer, Integer> SEGMENT_TO_PRIZE = new HashMap<>();
    private final ShopWheelOutputBoundary userPresenter;
    private final ShopWheelUserDataAccessInterface userDataAccessObject;
    private final UserFactory userFactory;

    public ShopWheelInteractor(ShopWheelOutputBoundary shopWheelOutputBoundary,
                               ShopWheelUserDataAccessInterface userDataAccessObject,
                               UserFactory userFactory) {
        this.userPresenter = shopWheelOutputBoundary;
        this.userDataAccessObject = userDataAccessObject;
        this.userFactory = userFactory;
    }

    static {
        SEGMENT_TO_PRIZE.put(1, 100);
        SEGMENT_TO_PRIZE.put(2, 20);
        SEGMENT_TO_PRIZE.put(3, 10);
        SEGMENT_TO_PRIZE.put(4, 5);
        SEGMENT_TO_PRIZE.put(5, 15);
        SEGMENT_TO_PRIZE.put(6, 35);
        SEGMENT_TO_PRIZE.put(7, 25);
        SEGMENT_TO_PRIZE.put(8, 30);
        SEGMENT_TO_PRIZE.put(9, 12);
        SEGMENT_TO_PRIZE.put(10, 14);
        SEGMENT_TO_PRIZE.put(11, 9);
        SEGMENT_TO_PRIZE.put(12, 4);
        SEGMENT_TO_PRIZE.put(13, 23);
        SEGMENT_TO_PRIZE.put(14, 24);
        SEGMENT_TO_PRIZE.put(15, 31);
        SEGMENT_TO_PRIZE.put(16, 6);
    }

    @Override
    public void switchToShopView() {
        userPresenter.switchToShopView();
    }

    @Override
    public void spinWheel(long lastSpin) {
        final Random random = new Random();
        final int targetSegment = random.nextInt(NUM_SEGMENTS) + 1;
        final int segmentWidth = 360 / NUM_SEGMENTS;
        final int targetAngle = targetSegment * segmentWidth - segmentWidth / 4;

        userPresenter.spinWheel(targetAngle, SEGMENT_TO_PRIZE.get(targetSegment));
    }

    @Override
    public void tooEarly() {
        userPresenter.tooEarly();
    }

    @Override
    public void saveData(ShopWheelInputData shopWheelInputData, Integer newBalance, long newLastSpin) {
        final User infoUser = userDataAccessObject.get(shopWheelInputData.getUsername());
        final JSONObject json = infoUser.getInfo();
        json.put("balance", newBalance);
        json.put("lastSpin", newLastSpin);
        final User updatedUser = userFactory.create(infoUser.getName(), infoUser.getPassword(), json);
        userDataAccessObject.saveNew(updatedUser, json);
    }

}
