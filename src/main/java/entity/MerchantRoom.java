package entity;

public class MerchantRoom extends AbstractRoom {
    private Merchant merchant;


    @Override
    public int getRoomType() {
        return 3; // Merchant Room
    }
    /**
     * Creates a MerchantRoom.
     * @param merchant the merchant in the MerchantRoom.
     */

    public MerchantRoom(int roomNumber, Merchant merchant) {
        super(roomNumber);
        this.merchant = merchant;
    }

    /**
     * Returns merchant.
     * @return the merchant in the MerchantRoom.
     */
    public Merchant getMerchant() {
        return merchant;
    }

    /**
     * Lets us set the merchant in the MerchantRoom.
     * @param merchant the merchant in the MerchantRoom.
     */
    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
}
