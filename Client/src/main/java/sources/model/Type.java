package sources.model;

/**
 * Created by Tomsoir on 09.04.2017.
 */
public enum Type {

    ON_DEMAND("ON_DEMAND"), URGENT("URGENT"),
    SETTLEMENT("SETTLEMENT"), ACCUMULATION("ACCUMULATION"),
    SAVINGS("SAVINGS"), METALLIC("METALLIC");

    private String type;

    Type(String type){
        this.type = type;
    }

    public String getType(){
        return this.type.toLowerCase();
    }

}