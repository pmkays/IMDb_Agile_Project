package app.model;

public class ProductionCompany {
    private String name;
    private int productID;

    public ProductionCompany(int id, String name) {
    	this.productID = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    
    public int getProductID() {
        return this.productID;
    }
}
