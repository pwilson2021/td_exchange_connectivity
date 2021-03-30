package turntabl.io.exchange_connectivity.model;

public class QueueTradeModel {
    private String product;
    private Integer quantity;
    private Double price;
    private String side;
    private int exchangeId;
    private int orderId;

    public QueueTradeModel(String product, Integer quantity, Double price, String side, int exchangeId, int orderId) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.exchangeId = exchangeId;
        this.orderId = orderId;
    }

    public QueueTradeModel() {
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public int getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(int exchangeId) {
        this.exchangeId = exchangeId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "QueueTradeModel{" +
                "product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", side='" + side + '\'' +
                ", exchangeId=" + exchangeId +
                '}';
    }
}
