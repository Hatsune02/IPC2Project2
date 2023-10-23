package com.navi.jobhub.utils;

import java.io.*;
import java.sql.Date;
import java.time.LocalDate;

import org.json.simple.*;
import org.json.simple.parser.*;

import javax.swing.text.html.parser.Parser;

public class FillDataBase {
    public static void parser(FileReader reader) {
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(reader);


            JSONObject jsonObject = (JSONObject) obj;

            JSONObject admin = (JSONObject) jsonObject.get("admin");
            JSONArray employees = (JSONArray) jsonObject.get("empleadores");
            JSONArray users = (JSONArray) jsonObject.get("usuarios");
            JSONArray categories = (JSONArray) jsonObject.get("categorias");
            JSONArray offer = (JSONArray) jsonObject.get("ofertas");


            admin(admin);
            for(Object employee: employees){
                employee((JSONObject) employee);
            }

        } catch(ParseException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
    private static void employee(JSONObject object){
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