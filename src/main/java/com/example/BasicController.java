package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
//import java.util.List;

/* Mongo stuff */
import com.example.repositories.user.User;
import com.example.repositories.user.UserRepository;
import com.example.repositories.profile.Profile;
import com.example.repositories.profile.ProfileRepository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DB;
import com.mongodb.MongoClient;

/*  reading HTML files and making the string for it/ html parsing     */
import java.io.*;  
import java.io.IOException; 
/* ===================================================  */

/* AWS IMPORTS */
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

@RestController
public class BasicController {
    @Value("${accesskey}")
    String accesskey;
    @Value("${secretkey}")
    String secretkey;
    @Value("${bucketName}")
    String bucketName;
    
    String image_link;
    
    public String get_html(String filename){
        String html = "";
            if(filename.contains(".html")== false){
                filename = filename + ".html";
            }
            
            try{                       
                File test = new File("C:\\Users\\Berezia\\Documents\\NetBeansProjects\\Assn3\\src\\main\\java\\com\\example\\html\\"+filename);   //html file path
                //change path for ubuntu
                FileInputStream reading = new FileInputStream(test);
                
                int a = 0;
                while((a=reading.read())!= -1){
                    html = html + (char)a;
                }
            }
            
            catch(Exception e){
                //e.printStackTrace();
                System.out.println("File Not Found");
                return "Error";
            }
        return html;  
        }
           
    @RequestMapping("/home")
    public String home_page() {
        String html = get_html("home");
        return html;
    }    
    @GetMapping("/forgot")
    public String recovery(){
        String html = get_html("forgotPassword");
        return html;
    }         
        
    @RequestMapping("/edit")
    public String edit_profile() {
        String html = get_html("edit");
        return html;	
    }
    
    @PostMapping(value = "/upload")
    public String uploads3(@RequestParam("photo") MultipartFile image, 
				 @RequestParam(name = "desc") String desc) {
        ModelAndView returnPage = new ModelAndView();
        System.out.println("description      " + desc);
        System.out.println(image.getOriginalFilename());
    
        BasicAWSCredentials cred = new BasicAWSCredentials(accesskey, secretkey);
        AmazonS3 client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(cred))
                .withRegion(Regions.US_EAST_2).build();
        try {
            PutObjectRequest put = new PutObjectRequest(bucketName, image.getOriginalFilename(),
                    image.getInputStream(), new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead);
            client.putObject(put);

            String imgSrc = "http://" + bucketName + ".s3.amazonaws.com/" + image.getOriginalFilename();

            returnPage.setViewName("home");
            returnPage.addObject("name", desc);
            returnPage.addObject("imgSrc", imgSrc);

            //Save this in the DB. 
        } catch (IOException e) {
            e.printStackTrace();
            returnPage.setViewName("error");
        }
        return "redirect:home";

    }
    
    @RequestMapping("/uploaded")
    public String upload(){
        String html = get_html("uploaded");
        return html;    
    }   
    
    @RequestMapping("/login")
    public String login() {
        String html = get_html("login");
        return html;
    }  
    @RequestMapping("/log")
    public String logging(){
        return get_html("log");
    }    

    @RequestMapping("/create_profile")
    public String create_profile(){
        String html = get_html("create_profile");
        return html;
    }
    
    @RequestMapping("/register")
    public String regitser() {   
        String html = get_html("register");
        return html;
    }   
    
    @RequestMapping(value = "/reg")
    public String reg(){
        String html = get_html("reg");
        return html;
    }   
    
    @GetMapping("/")
    public void index(HttpServletResponse httpResponse) throws Exception{
        httpResponse.sendRedirect("/home");
    }
    
    //profile controller
    //@Autowired
    ProfileRepository profileRepository;

    @RequestMapping("/profiles")
    public String prof_list(Model model) {
        model.addAttribute("Profiles", profileRepository.findAll());
        return get_html("unavailable");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addProfile(@ModelAttribute Profile profile) {
        profileRepository.save(profile);
        return get_html("created");
    }

    @RequestMapping(value = "/search_profiles")
    public String search(@RequestParam String search) {
        return get_html("unavailable");
    }
   
    //user controller
    //@Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user) {
        userRepository.save(user);
        return get_html("reg");
    }

}
