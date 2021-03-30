package turntabl.io.exchange_connectivity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TradeModel {
    @JsonProperty("product")
    private String product;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("side")
    private String side;

    public TradeModel(String product, Integer quantity, Double price, String side) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
    }

    public TradeModel() {
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
}
