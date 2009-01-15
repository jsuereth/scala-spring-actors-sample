package com.suereth.springactors

/**
 * This is the interface for an email service.
 * For use in spring.
 */
trait EmailService {
  def sendEmail(to : String, subject : String, msg : String) : Unit 
}



package impl {
  import actors.Actor
  import Actor.loop
  import org.springframework.beans.factory.annotation._
  import org.springframework.stereotype._
  
  /** Implementation of emailService */
  @Component
  class ActorBasedEmailService extends Actor with DisposableSpringActorBean with EmailService {
    
    /** Internal message passing */
    private case class SendEmail(to : String, subject : String, msg : String)

    /**
     * Forwards the request to my actor.
     */
    def sendEmail(to : String, subject : String, msg : String) { 
      this ! SendEmail(to, subject, msg) 
    }
    
    //Defines how this actor is acting
    def act() {
      loop {
        react {
          case SendEmail(to, subject, msg) =>
            Console.println("This should actually send an email message to: " + to)
        }
      }
    }
    start
  }  
}

