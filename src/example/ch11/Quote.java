package ch11;

/**
 * 将解析的字符串各部分赋值给该类的字段
 */
public class Quote {

    private final String shopName;
    private final double price;
    private final Discount.Code discountCode;


    public Quote(String shopName, double price, Discount.Code discountCode) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = discountCode;
    }

    // 该静态方法用来解析字符串，然后返回Quote类
    public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble((split[1]));
        Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, discountCode);
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getDiscountCode() {
        return discountCode;
    }
}
