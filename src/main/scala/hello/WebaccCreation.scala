package hello

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotBlank

class WebaccCreation {
 
  var start = 900000
  var end   = 1000000
  var rnd = new scala.util.Random
  @JsonProperty("login_id")
  var login_id : Int = start + rnd.nextInt( (end - start) + 1 )
  @NotBlank(message = "URL cannot be blank!")
  @JsonProperty("url")
  var url :String = _
  @NotBlank(message = "Login ID cannot be blank!")
  @JsonProperty("login")
  var login :String  = _
  @NotBlank(message = "Password cannot be blank!")
  @JsonProperty("password")
  var password :String  = _
  @JsonProperty("user_id")
  var user_id:String = _

  
  
  def this(url:String,login:String,password:String,user_id:String,login_id : Int) {
    this()
    this.login_id = login_id
    this.url = url
    this.login = login
    this.password = password
	this.user_id=user_id
   }
}