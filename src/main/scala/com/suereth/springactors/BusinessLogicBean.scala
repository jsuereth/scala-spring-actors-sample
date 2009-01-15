package com.suereth.springactors

import scala.actors.Actor
import Actor.loop
import org.springframework.beans.factory.annotation._
import org.springframework.stereotype._

/**
 * Some kind of bean the emulates business logic.
 */
@Service("blb")
class BusinessLogicBean extends Actor with DisposableSpringActorBean with InitializingSpringActorBean {

  private[this] var emailService : EmailService = _
  
  //TODO - Make spring "scala aware" for var's, so we can use the emailService_= method.
  @Autowired
  def setEmailService(emailService : EmailService) {
    this.emailService = emailService
  }
  
  
  def handleData(data : String) {
    this ! HandleData(data)        
  }
  
  
  private case class HandleData(data : String)
  
  override def act() {
    loop {
      react {
        case HandleData(data) =>
          emailService.sendEmail("me@company.org", "Business Logic was performed!", data)
      }
    }
  }
  
}
