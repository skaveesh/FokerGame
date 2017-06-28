package foker.core;

/**
 * Created by samintha on 6/27/2017.
 */
public class PlayerPosition {
    private int playerId;
    private Double positionX;
    private Double positionY;

    public PlayerPosition(int playerId, Double positionX, Double positionY) {
        this.playerId = playerId;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public Double getPositionX() {
        return positionX;
    }

    public void setPositionX(Double positionX) {
        this.positionX = positionX;
    }

    public Double getPositionY() {
        return positionY;
    }

    public void setPositionY(Double positionY) {
        this.positionY = positionY;
    }
}
