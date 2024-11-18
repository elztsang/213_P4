package pizzaria;

public enum Crust {
    DEEPDISH("Chicago"),
    PAN ("Chicago"),
    STUFFED ("Chicago"),
    BROOKLYN ("New York"),
    THIN ("New York"),
    HANDTOSSED ("New York");

    private final String crustType;

    Crust(String crustType) {
        this.crustType = crustType;
    }

    public String getCrustType(){
        return crustType;
    }
}
