package use_case.shopwheel;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * The Shop wheel Interactor.
 */
public class ShopWheelInteractor implements ShopWheelInputBoundary {
    private static final int NUM_SEGMENTS = 8;
    private static final Map<Integer, Integer> SEGMENT_TO_PRIZE = new HashMap<>();

    static {
        SEGMENT_TO_PRIZE.put(1, 1);
        SEGMENT_TO_PRIZE.put(2, 20);
        SEGMENT_TO_PRIZE.put(3, 10);
        SEGMENT_TO_PRIZE.put(4, 5);
        SEGMENT_TO_PRIZE.put(5, 15);
        SEGMENT_TO_PRIZE.put(6, 35);
        SEGMENT_TO_PRIZE.put(7, 25);
        SEGMENT_TO_PRIZE.put(8, 30);
    }

    private final ShopWheelOutputBoundary userPresenter;

    public ShopWheelInteractor(ShopWheelOutputBoundary shopWheelOutputBoundary) {
        this.userPresenter = shopWheelOutputBoundary;
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
        final int targetAngle = targetSegment * segmentWidth - random.nextInt(segmentWidth);

        userPresenter.spinWheel(targetAngle, SEGMENT_TO_PRIZE.get(targetSegment));
    }

    @Override
    public void tooEarly() {
        userPresenter.tooEarly();
    }

}
