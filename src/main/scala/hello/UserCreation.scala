package hello

import scala.beans.BeanProperty
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotBlank
//import java.util.Calendar
import java.util.Date


class UserCreation(){
   
 // @JsonProperty("id")
 /* var start = 10000
  var end   = 200000
  var rnd = new scala.util.Random
	
  @JsonProperty("id")
  var id : Int = start + rnd.nextInt( (end - start) + 1 )
  */
  @JsonProperty("id")
  var id: Int = _
  @NotBlank(message = "Email cannot be blank!") 
  @JsonProperty("email")
  var email:String = _
  @NotBlank(message = "Password cannot be blank!")
  @JsonProperty("password")
  var password:String  = _
 @JsonProperty("creationdate")
 var creationdt : String = _
 @JsonProperty("updateddate")
 var updateddt : String = _
  
  
  def this(email:String,password:String,creationdt:String,id: Int,updateddt : String) {
    this()
   
    this.email = email
    this.password = password
    this.creationdt = creationdt
	this.id = id
	this.updateddt = updateddt
  }
  
  
  
}


/*package hello

class UserCreation() {
  
  
    val start = 10000
	val end   = 200000
	val rnd = new scala.util.Random
	
	val userid = start + rnd.nextInt( (end - start) + 1 ) 
	
	val creationtimestamp: Long = System.currentTimeMillis / 1000

  
     def addtohash(userid: Int, email : String, password: String, creationdate : Long)
  {
         //val useridval : Int = userid
         var passwordval : String = password
         var creationval : Long = creationdate
         
         var userid = Map("password" -> passwordval,
                       "creationdate" -> creationval)
  }
  
  def returnhashvalues()
  {
    val info : String = ""
      
    if (userid.contains(userid))
    {
      userid.keys.foreach{
        i =>	info += i +":"+useridval(i)
      }
      return info
    }
    }
  }

}*/