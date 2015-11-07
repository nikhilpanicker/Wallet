package hello

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestMethod
import scala.collection.mutable.ListBuffer
import com.google.gson.Gson
import java.util.ArrayList
import javax.validation.Valid
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMessages
import org.springframework.validation.FieldError
import java.util.List
import java.util.Date
import org.springframework.validation.ObjectError
//import hello.ErrorMessage

@Controller
@RestController
@RequestMapping(Array("/")) //delete this
class HelloWorldController {

  var mapobj: Map[Int, UserCreation] = Map()
  var Icardmapobj: Map[Int, ICardCreation] = Map()
  var BankAccmapobj: Map[Int, BankaccCreation] = Map()
  var WebAccmapobj: Map[Int, WebaccCreation] = Map()
  var addviewobj2 = new ListBuffer[BankaccCreation]()
  var icardCreationList = new ListBuffer[ICardCreation]()
  var webCreationList = new ListBuffer[WebaccCreation]()
  var bankAccCreationList = new ListBuffer[BankaccCreation]()

  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = Array("/api/v1/users"), method = Array(RequestMethod.POST)) //def createuser (@RequestParam(value="email", required=true)  email : String , @RequestParam(value="password", required=true)  password : String) = {
  def createuser(@Valid @RequestBody user: UserCreation) : UserCreation = {

	    var start = 10000
	    var end   = 200000
	    var rnd = new scala.util.Random
		  
	    
	    user.id  = start + rnd.nextInt( (end - start) + 1 )
	    user.creationdt = new Date().toString()
	    user.updateddt = new Date().toString()
	    mapobj += (user.id -> user)
	    return user
  }

  @RequestMapping(value = Array("/api/v1/users/{user_id}"), method = Array(RequestMethod.GET))
  def viewuser(@PathVariable("user_id") id: Int): UserCreation = {

		var viewuser: UserCreation = null;
		if (mapobj.contains(id)) {
		  viewuser = mapobj.get(id).get
		}
		return viewuser
  }

   @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = Array("/api/v1/users/{user_id}"), method = Array(RequestMethod.PUT))
  def updateuser(@Valid @RequestBody user: UserCreation,@PathVariable("user_id") id: Int) : UserCreation = {
	  
		user.updateddt = new Date().toString()
		var userdt : UserCreation = mapobj.get(id).get
		var tempDateVal : String = userdt.creationdt
		user.creationdt= tempDateVal
		mapobj -= user.id
		user.id = id
		mapobj += (user.id -> user)
		return user
		
  }
  
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = Array("/api/v1/users/{user_id}/idcards"), method = Array(RequestMethod.POST))
  def idcardcreation(@Valid @RequestBody useridcard: ICardCreation,@PathVariable("user_id") id: Int) : ICardCreation = {

	   useridcard.user_id=id
	   icardCreationList+=useridcard
	   Icardmapobj += (useridcard.user_id -> useridcard)
	   return useridcard
	
  }

  @RequestMapping(value = Array("/api/v1/users/{user_id}/idcards"), method = Array(RequestMethod.GET))
  def idcardview(@PathVariable("user_id") id: Int) :ArrayList[ICardCreation]  = {

		val currentCardList = new ArrayList[ICardCreation]
		for (tempcardlist <- icardCreationList)
		{
		println("user id in IDcard bean "+tempcardlist.user_id )
		println("user_id"+tempcardlist.user_id )
		println("id"+id)
		if(tempcardlist.user_id.equals(id))
		  {
			println("working fine")
			currentCardList.add(tempcardlist)
		  }
		 
		}
			   
		return currentCardList
  }
  
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @RequestMapping(value = Array("/api/v1/users/{user_id}/idcards/{card_id}"), method = Array(RequestMethod.DELETE))
  def idcarddelete(@Valid @RequestBody useridcard: ICardCreation,@PathVariable("card_id") cardId: Int) = {

		for (tempcarddeletelist <- icardCreationList)
		{	
		 if(tempcarddeletelist.card_id.equals(cardId))
		  {
			icardCreationList -=tempcarddeletelist
		  }	 
		}
  }
  
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = Array("/api/v1/users/{user_id}/weblogins"), method = Array(RequestMethod.POST))
  def Webacccreate(@Valid @RequestBody webacc: WebaccCreation,@PathVariable("user_id") id: String) : WebaccCreation  = {

	    webacc.user_id=id
	    webCreationList+=webacc
	    return webacc

  }

  @RequestMapping(value = Array("/api/v1/users/{user_id}/weblogins"), method = Array(RequestMethod.GET))
  def viewwebacc(@PathVariable("user_id") id: String) : ArrayList[WebaccCreation] = {
  
		var currentWebUserList = new ArrayList[WebaccCreation]
		
		for (tempwebList <- webCreationList)
		{
		println("user id in IDcard bean "+tempwebList.user_id )
		if(tempwebList.user_id.equals(id))
		  {	
			currentWebUserList.add(tempwebList)
		  }
		 
		}
		return currentWebUserList
  }
    
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @RequestMapping(value = Array("/api/v1/users/{user_id}/weblogins/{login_id}"), method = Array(RequestMethod.DELETE))
  def deletewebacc(@Valid @RequestBody webacc: WebaccCreation,@PathVariable("login_id") loginId: Int) = {

		for (tempWebDeleteList <- webCreationList)
		{	
		if(tempWebDeleteList.login_id.equals(loginId))
		  {		
			webCreationList -=tempWebDeleteList
		  }	 
		}
  }
  
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = Array("/api/v1/users/{user_id}/bankaccounts"), method = Array(RequestMethod.POST))
  def bankacccreate(@Valid @RequestBody bankacc: BankaccCreation,@PathVariable("user_id") id: String) : BankaccCreation = {

		bankacc.user_id=id
		bankAccCreationList +=bankacc
		return bankacc
  }

  @RequestMapping(value = Array("/api/v1/users/{user_id}/bankaccounts"), method = Array(RequestMethod.GET))
  def viewbankacc(@PathVariable("user_id") id: String) : ArrayList[BankaccCreation] = {

		val currentBankUserList = new ArrayList[BankaccCreation]
		for (tempBankAcclist <- bankAccCreationList)
		{
		if(tempBankAcclist.user_id.equals(id))
		  {
			currentBankUserList.add(tempBankAcclist)
		  } 
		}
		return currentBankUserList

  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @RequestMapping(value = Array("/api/v1/users/{user_id}/bankaccounts/{ba_id}"), method = Array(RequestMethod.DELETE))
  def deletebankacc(@Valid @RequestBody bankacc: BankaccCreation,@PathVariable("ba_id") bankid: String) = {

		for (tempBankAccDeleteList <- bankAccCreationList)
		{	
		if(tempBankAccDeleteList.ba_id.equals(bankid))
		  {
			bankAccCreationList -=tempBankAccDeleteList
		  }	 
		}
  }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    def handleException(ex:MethodArgumentNotValidException):ErrorMessage ={
		
        var fldErrorsList:List[FieldError] = ex.getBindingResult().getFieldErrors()       
        var objErrorsList:List[ObjectError] = ex.getBindingResult().getGlobalErrors()      
        var errorList:List[String] = new ArrayList[String]
        var errorString:String = ""
        var tempitr = fldErrorsList.iterator()
		
        while(tempitr.hasNext()){
          var fldError:FieldError = tempitr.next()
          errorString = fldError.getField() + ", "+fldError.getDefaultMessage()
          errorList.add(errorString)
        }
        
        var tempitr2 = objErrorsList.iterator()
        while(tempitr2.hasNext()){
          var objError:ObjectError = tempitr2.next()
          errorString = objError.getObjectName() + ", "+objError.getDefaultMessage()
          errorList.add(errorString)
        }
        return new ErrorMessage(errorList);
    }
   }
    	