package com.example.repositories.profile;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "profiles.Profile")
public class Profile {
    @Id private String id;
    
    private String name;
    private String biography;
    private String picture_link; //change
    private String email;
    //private String acct_type;
    
    public Profile(){}
    //want to add resume and project information later
    //location, skills[?]
    public Profile(String email, String name,String biogrpahy, String picture_link){
        this.email= email;
        this.name = name;
        this.biography = biogrpahy;
        this.picture_link = picture_link;
    }    
    
    @Override
    public String toString(){
        return String.format("profile[id=%s, picture_url='%s' name='%s', biography='%s', email='%s']", 
                              id,picture_link, name,biography,email);
    }
    private String getId(){
        return id;
    }
    private String getName(){
        return name;
    }
    private String getPictureLink(){
        return picture_link;
    }
    private String getBio(){
        return biography;
    }
    private String getEmail(){
        return email;
    }
    /*private String getAcctType(){
        return acct_type;
    }*/
    private void setName(String name){
        this.name = name;
    }   
    private void setBio(String biography){
        this.biography = biography;
    }
    private void setPictureLink(String picture_link){
        this.picture_link = picture_link;
    }
    private void setEmail(String email){
        this.email = email;
    }
    /*private void setAcctType(String acct_type){
        this.acct_type = acct_type;
    }*/
}
