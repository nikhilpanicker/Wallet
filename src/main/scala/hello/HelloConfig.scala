package hello

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.boot.SpringApplication

@Configuration 
@EnableAutoConfiguration 
@ComponentScan 
class HelloConfig 


object SampleWebApplication extends App { 
  SpringApplication.run(classOf[HelloConfig]); 
  }

