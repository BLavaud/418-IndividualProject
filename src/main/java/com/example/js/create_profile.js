// take form data and insert in db
function create_profile(form){
    var MongoClient = require('mongodb').MongoClient;
    var url = "mongodb://localhost:27017/";

    var name = form.document.getElementById("name").value;
    var bio = document.getElementById("desc").value;
    var picture = document.getElementById("photo").value;
    var email = document.getElementById().value;

    var unreg = document.getElementsById("profileType1");
    var reg = document.getElementsById("profileType2");

    //profile belongs to [?] email
    MongoClient.connect(url, function(err, db) {   
    
    if (err) throw err;
    var dbo = db.db("TermProject");
  
    var name_exists = {name: name};
    //var email_exists = {email: email};
  
    dbo.collection("profile").find(name_exists).toArray, 
    function(err, result)
    {
        if (err) alert("profile dne");
            var myobj = {pic: picture, name: name, bio: bio, email: email };
            dbo.collection("profile").insertOne(myobj, function(err, res) 
            {
                if (err) throw err;
                console.log("profile created");
            });
            //if not found[doesnt exist]        
        //else [if found]
        alert("Profile with name: "+ name +" already exists.");            
    };
   
        db.close();
        if(reg.checked === true)
            window.location.href="\register";
        ;
    });
}

