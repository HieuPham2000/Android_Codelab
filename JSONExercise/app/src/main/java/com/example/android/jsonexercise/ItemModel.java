package com.example.android.jsonexercise;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;

public class ItemModel implements Serializable {
    private final String BASE_URL = "https://lebavui.github.io";
    private int id;
    private String name;
    private String username;
    private String email;
    private HashMap<String, String> avatar;
    private HashMap<String, String> address;
    private String phone;
    private HashMap<String, String> company;

    public ItemModel() {

    }

    public ItemModel(int id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public ItemModel(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getInt("id");
            this.name = jsonObject.getString("name");
            this.username = jsonObject.getString("username");
            this.email = jsonObject.getString("email");

            JSONObject avatar = jsonObject.getJSONObject("avatar");
            String thumbnail = BASE_URL + avatar.getString("thumbnail");
            String photo = BASE_URL + avatar.getString("photo");
            this.avatar = new HashMap<>();
            this.avatar.put("thumbnail", thumbnail);
            this.avatar.put("photo", photo);

            JSONObject address = jsonObject.getJSONObject("address");
            String street = address.getString("street");
            String suite = address.getString("suite");
            String city = address.getString("city");
            String zipcode = address.getString("zipcode");
            this.address = new HashMap<>();
            this.address.put("street", street);
            this.address.put("suite", suite);
            this.address.put("city", city);
            this.address.put("zipcode", zipcode);

            this.phone = jsonObject.getString("phone");

            JSONObject company = jsonObject.getJSONObject("company");
            String companyName = company.getString("name");
            this.company = new HashMap<>();
            this.company.put("name", companyName);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, String> getAvatar() {
        return avatar;
    }

    public void setAvatar(HashMap<String, String> avatar) {
        this.avatar = avatar;
    }

    public HashMap<String, String> getAddress() {
        return address;
    }


    public void setAddress(HashMap<String, String> address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public HashMap<String, String> getCompany() {
        return company;
    }

    public void setCompany(HashMap<String, String> company) {
        this.company = company;
    }

    // get address, company
    public String getAddressString() {
        try {
            String street = this.address.get("street");
            String suite = this.address.get("suite");
            String city = this.address.get("city");
            String zipcode = this.address.get("zipcode");
            return street + ", " + suite + ", " + city + ", " + zipcode;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getCompanyString() {
        try {
            String companyName = this.company.get("name");
            return companyName;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
