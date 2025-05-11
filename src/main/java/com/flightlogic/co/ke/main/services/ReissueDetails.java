/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.flightlogic.co.ke.main.services;

import com.flightlogic.co.ke.main.utilities.Utils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.text.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.gson.JsonArray;

/**
 *
 * @author jgithu
 */
public class ReissueDetails {

    private final Logger loggger = LogManager.getLogger(ReissueDetails.class);
    Utils utility = new Utils();

    public JsonObject reissueQuote(String request, String url, JsonObject credentials) {
        JsonObject responseObject = new JsonObject();

        try {

            //JsonObject incomingRequest = new JsonObject(request);
            JsonObject requestObject = JsonParser.parseString(request).getAsJsonObject();
            //  System.out.print("JSON REQUEST" + request);

            JsonObject flightRequestObject = new JsonObject();
            //form request here 

            flightRequestObject.addProperty("user_id", credentials.get("user_id").getAsString());
            flightRequestObject.addProperty("user_password", credentials.get("user_password").getAsString());
            flightRequestObject.addProperty("access", credentials.get("access").getAsString());
            flightRequestObject.addProperty("ip_address", credentials.get("ip_address").getAsString());
            flightRequestObject.addProperty("UniqueID", requestObject.get("UniqueID").getAsString());

            // Add paxDetails array
            JsonArray paxDetailsArray = requestObject.getAsJsonArray("paxDetails");
            flightRequestObject.add("paxDetails", paxDetailsArray);

            JsonArray originDestinationArray = requestObject.getAsJsonArray("OriginDestinationInfo");
            flightRequestObject.add("OriginDestinationInfo", originDestinationArray);

            loggger.info("REISSUE_QUOTE_REQUEST |  URL " + url + "  REQ " + flightRequestObject);

            responseObject = utility.flightLogicRequest(flightRequestObject, url);
            loggger.info("REISSUE_QUOTE_RESPONSE |  " + responseObject);

            if (responseObject.has("errors")) {
                responseObject.addProperty("status", "01");
                responseObject.addProperty("message", "An error occured while processing your request. Please try again later");

                return responseObject;
            }

        } catch (IOException | ParseException ex) {
            loggger.info("Exception  |  " + ex.getMessage());
            responseObject.addProperty("status", "01");
            responseObject.addProperty("message", ex.getMessage());
            responseObject.addProperty("response", "");
        }

        return responseObject;

    }

    public JsonObject reissueTicket(String request, String url, JsonObject credentials) {
        JsonObject responseObject = new JsonObject();

        try {

            //JsonObject incomingRequest = new JsonObject(request);
            JsonObject requestObject = JsonParser.parseString(request).getAsJsonObject();
            //  System.out.print("JSON REQUEST" + request);

            JsonObject flightRequestObject = new JsonObject();
            //form request here 
              
            flightRequestObject.addProperty("user_id", credentials.get("user_id").getAsString());
            flightRequestObject.addProperty("user_password", credentials.get("user_password").getAsString());
            flightRequestObject.addProperty("access", credentials.get("access").getAsString());
            flightRequestObject.addProperty("ip_address", credentials.get("ip_address").getAsString());
            flightRequestObject.addProperty("UniqueID", requestObject.get("UniqueID").getAsString());
            flightRequestObject.addProperty("ptrUniqueID", requestObject.get("ptrUniqueID").getAsString());
            flightRequestObject.addProperty("PreferenceOption", requestObject.get("PreferenceOption").getAsInt());
            flightRequestObject.addProperty("remark", requestObject.get("remark").getAsString());

            loggger.info("REISSUE_TICKET_REQUEST |  URL " + url + "  REQ " + flightRequestObject);

            responseObject = utility.flightLogicRequest(flightRequestObject, url);
            loggger.info("REISSUE_TICKET_RESPONSE |  " + responseObject);

            if (responseObject.has("errors")) {
                responseObject.addProperty("status", "01");
                responseObject.addProperty("message", "An error occured while processing your request. Please try again later");

                return responseObject;
            }

        } catch (IOException | ParseException ex) {
            loggger.info("Exception  |  " + ex.getMessage());
            responseObject.addProperty("status", "01");
            responseObject.addProperty("message", ex.getMessage());
            responseObject.addProperty("response", "");
        }

        return responseObject;

    }

}
