//https://tests4geeks.com/blog/spring-data-boot-mongodb-example/
package com.example.repositories.user;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "profiles.users")
public class User {
    
    @Id private String id;
    
    //private String name;
    //private String biography;
    //private String picture_link; //change
    private String email;
    private String password;
    private String acct_type;
    
    public User(){}
    //want to add resume and project information later
    //location, skills[?]
    public User(String email, String password, String acct_type){
        this.email= email;
        this.password = password;
        this.acct_type = acct_type;
    }    
    
    @Override
    public String toString(){
        return String.format("User[id=%s, email='%s', password='%s']", 
                              id, email,password);
    }
    /*private String getId(){
        return id;
    }
    /*private String getName(){
        return name;
    }
    private String getPictureLink(){
        return picture_link;
    }
    private String getBio(){
        return biography;
    }*/
    /*private String getEmail(){
        return email;
    }
    private String getPassword(){
        return password;
    }
    private String getAcctType(){
        return acct_type;
    }
    /*private void setName(String name){
        this.name = name;
    }   
    private void setBio(String biography){
        this.biography = biography;
    }
    private void setPictureLink(String picture_link){
        this.picture_link = picture_link;
    }*/
    /*private void setEmail(String email){
        this.email = email;
    }
    private void setPassword(String password){
        this.password = password;
    }
    private void setAcctType(String acct_type){
        this.acct_type = acct_type;
    }*/
}
