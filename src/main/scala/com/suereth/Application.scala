package com.suereth

import org.springframework.context.support.ClassPathXmlApplicationContext
import springactors.BusinessLogicBean

object SpringApplication {
  
  
  
  def main(args : Array[String]) : Unit = {
    val ctx = new ClassPathXmlApplicationContext("applicationContext-actors.xml");
    
    ctx.getBean("blb") match {
      case bean : BusinessLogicBean =>
        bean.handleData("HAI WURLD!")
      case _ =>
        Console.println("Failed to initialize beans correctly!")
    }
    
    ctx.close()
  }
}
