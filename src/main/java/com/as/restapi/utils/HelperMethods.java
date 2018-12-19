package com.as.restapi.utils;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class HelperMethods {
    /*
    Verify the http response status returned. Check Status Code is 200?
    We can use Rest Assured library's response's getStatusCode method
    */
    public static void checkStatusIs200 (Response res) {
        assertEquals("Status Check Failed!", 200, res.getStatusCode());
    }

    /*
     * We can get all id's with this code --> "jp.get("id");" this will return
     * all id's under "items" tag.
    */
    public static ArrayList getIdList (JsonPath jp) {
        ArrayList idList = jp.get("id");
        return idList;
    }


    public static ArrayList getIds (JsonPath jp) {
        ArrayList idsList = jp.get("id");
        return idsList;
    }


    //Merge IdList and relatedIdList as mergedList
    public  static ArrayList mergeIdLists (ArrayList idList, ArrayList relatedIdList){
        ArrayList mergedIdList = new ArrayList(idList);
        mergedIdList.addAll(relatedIdList);
        return mergedIdList;
    }

    //Find Duplicate Ids
    public static boolean findDuplicateIds (List<Integer> IdList) {
        for (int i=0; i< IdList.size(); i++) {
            if(Collections.frequency(IdList, IdList.get(i)) > 1){
                System.out.println("This id is duplicated: " + IdList.get(i));
                return false;
            }
        }
        return true;
    }
}
