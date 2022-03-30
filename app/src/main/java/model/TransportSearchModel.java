package model;

public class TransportSearchModel {
    private Integer taxiRankID;
    private Integer routeID;
    private Integer fromID;
    private Integer toID;
    private String taxiRankName;
    private String routeName;

    public Integer getTaxiRankID(){
        return taxiRankID;
    }

    public void setTaxiRankID(Integer taxiRankID) {
        this.taxiRankID = taxiRankID;
    }

    public Integer getRouteID(){
        return routeID;
    }

    public void setRouteID(Integer routeID) {
        this.routeID = routeID;
    }

    public Integer getFromID(){
        return  fromID;
    }

    public void setFromID(Integer fromID){
        this.fromID = fromID;
    }

    public Integer getToID(){
        return toID;
    }

    public void setToID(Integer toID){
        this.toID = toID;
    }

    public String getTaxiRankName(){
        return taxiRankName;
    }

    public void setTaxiRankName(String taxiRankName){
        this.taxiRankName = taxiRankName;
    }

    public String getRouteName(){
        return routeName;
    }

    public void setRouteName(String routeName){
        this.routeName = routeName;
    }
}
