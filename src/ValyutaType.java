public enum ValyutaType {
    AZN("Azerbaijani Manat"),
    USD("US Dollar"),
    EUR("Euro");

    private final String valyutaAd;

    ValyutaType(String valyutaAd){
        this.valyutaAd=valyutaAd;
    }

    public String getValyutaAd(){
        return valyutaAd;
    }

    public static ValyutaType fromString(String valyuta){
        switch(valyuta.toUpperCase()){
            case "AZN": return AZN;
            case "USD": return USD;
            case "EUR": return EUR;
            default:
                throw new IllegalArgumentException("Duzgun valyuta mezennesi daxil edilmeyib: " + valyuta);
        }
    }
}
