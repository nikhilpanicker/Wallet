package hello

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController



@RestController
@RequestMapping(Array("/"))
class TestClass{

@RequestMapping
 def hello () = {
 	"Hello World"
 }


  
  

}