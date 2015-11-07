package hello

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotBlank

class ICardCreation {
 
 
  var start = 801000
  var end   = 888000
  var Newrnd = new scala.util.Random
  @JsonProperty("card_id")
  var card_id : Int = start + Newrnd.nextInt( (end - start) + 1 )
  @JsonProperty("user_id")
  var user_id:Int = _
  @NotBlank(message = "Card Name cannot be blank!")
  @JsonProperty("card_name")
  var card_name:String = _
  @NotBlank(message = "Card Number cannot be blank!")
  @JsonProperty("card_number")
  var card_number:String  = _
  @NotBlank(message = "Expiration Date cannot be blank!")
  @JsonProperty("expiration_date")
  var expiration_date:String  = _

  
  
  def this(card_name:String,card_number:String,card_id : Int,expiration_date : String,user_id:Int ) {
    this()
    this.card_name = card_name
    this.card_number = card_number
	this.card_id = card_id
	this.expiration_date=expiration_date
	this.user_id=user_id
	}
}