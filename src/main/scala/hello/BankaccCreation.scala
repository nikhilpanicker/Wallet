
package hello

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotBlank

class BankaccCreation {
  
  var start = 10550000
  var end   = 20550000
  var rnd = new scala.util.Random
	
  //@JsonProperty("ba_id")
  //var ba_id : Int = start + rnd.nextInt( (end - start) + 1 )
  @NotBlank(message = "Bank Id cannot be blank!")
  @JsonProperty("ba_id")
  var ba_id : String = _
  @NotBlank(message = "Account Name cannot be blank!")
  @JsonProperty("account_name")
  var account_name: String = _
  @NotBlank(message = "Routing Number cannot be blank!")
  @JsonProperty("routing_number")
  var routing_number:String = _
  @NotBlank(message = "Account Number cannot be blank!")
  @JsonProperty("account_number")
  var account_number:String  = _
 // @NotBlank(message = "User Id cannot be blank!")
  @JsonProperty("user_id")
  var user_id:String = _

  
  
  def this(account_name:String,routing_number:String,account_number:String,ba_id : String,user_id:String) {
    this()
    this.account_name = account_name
    this.routing_number = routing_number
    this.account_number = account_number
    this.ba_id = ba_id
	this.user_id = user_id

}
  
}