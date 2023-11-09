package com.navi.jobhub.utils;

import java.sql.Date;
import java.time.LocalDate;

import org.json.simple.*;
import org.json.simple.parser.*;

public class FillDataBase {
    public static void parser(String fileContent) {
        JSONParser jsonParser = new JSONParser();
        try {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileContent);

            JSONObject admin = (JSONObject) jsonObject.get("admin");
            JSONArray employers = (JSONArray) jsonObject.get("empleadores");
            JSONArray users = (JSONArray) jsonObject.get("usuarios");
            JSONArray categories = (JSONArray) jsonObject.get("categorias");
            JSONArray offer = (JSONArray) jsonObject.get("ofertas");


            admin(admin);
            for(Object employer: employers){
                employer((JSONObject) employer);
            }

        } catch(ParseException e){
            e.printStackTrace();
        }
    }
    private static void admin(JSONObject object){
        try{
            Long id = (Long) object.get("codigo");
            int id1 = Math.toIntExact(id);
            String name = (String) object.get("nombre");
            String address = (String) object.get("direccion");
            String username = (String) object.get("username");
            String password = (String) object.get("password");
            String email = (String) object.get("email");
            String CUI = (String) object.get("CUI");
            String date = (String) object.get("fechaNacimiento");
            Date date1 = new Date(1);
            boolean valid = false;

            try{
                LocalDate localDate = LocalDate.parse(date);
                date1 = Date.valueOf(localDate);
                valid = true;
            } catch (Exception ignored){}

            if(valid){

            }
        }catch (Exception ignored){}
    }
    private static void employer(JSONObject object){
        try{
            boolean valid = false;
            Long id = (Long) object.get("codigo");
            int id1 = Math.toIntExact(id);
            String name = (String) object.get("nombre");
            String address = (String) object.get("direccion");
            String username = (String) object.get("username");
            String password = (String) object.get("password");
            String email = (String) object.get("email");
            String CUI = (String) object.get("CUI");
            String date = (String) object.get("fechaNacimiento");
            Date date1 = new Date(1);

            JSONObject card = (JSONObject) object.get("tarjeta");
            JSONArray phoneNumbers = (JSONArray) object.get("telefonos");

            card(card);
            for(Object phone: phoneNumbers){
                String phoneNumber = (String) phone;
            }

            String mission = (String) object.get("mision");
            String vision = (String) object.get("vision");


            try{
                LocalDate localDate = LocalDate.parse(date);
                date1 = Date.valueOf(localDate);
                valid = true;
            } catch (Exception ignored){}

            if(valid){

            }
        }catch (Exception ignored){}
    }
    private static void card(JSONObject object){
        String username = (String) object.get("Titular");
        String number = (String) object.get("numero");
        String password = (String) object.get("codigoSeguridad");
    }
}