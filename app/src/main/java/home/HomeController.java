package home;

import database.Db;
import io.javalin.http.Context;
import model.TransportSearchModel;
import java.util.ArrayList;
import java.util.Objects;

public class HomeController {
    public static final String HOME_PATH = "/home";

    public static void handleHome(Context context){
        String currentLocation = Objects.requireNonNull(context.formParam("location")).toLowerCase();
        String toLocation = Objects.requireNonNull(context.formParam("destination")).toLowerCase();

        if(!currentLocation.contains("rank")){

            String newLocation = currentLocation +" rank";
            Db db = new Db();
            ArrayList<TransportSearchModel> taxiRankList = db.selectTaxiRank(newLocation);

            for (TransportSearchModel rank:taxiRankList
                 ) {
                System.out.println("++++: "+ rank.getTaxiRankName());
                  System.out.println("++++: "+ rank.getTaxiRankID().toString());
            }
        }

        System.out.println("location: "+currentLocation);
        System.out.println("destination: "+toLocation);

    }




}
