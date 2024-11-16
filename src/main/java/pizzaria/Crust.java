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

//    @Override
//    public String toString(){
//        return String.format("(%s - %s)", this.name(), Crust.valueOf(this.name()));
//    }
}
