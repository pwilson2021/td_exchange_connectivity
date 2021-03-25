package turntabl.io.exchange_connectivity.model;

public class QueueTradeModel {
    private String product;
    private Integer quantity;
    private Double price;
    private String side;
    private int exchangeId;

    public QueueTradeModel(String product, Integer quantity, Double price, String side, int exchangeId) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.exchangeId = exchangeId;
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
