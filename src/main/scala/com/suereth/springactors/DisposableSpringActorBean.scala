package com.suereth.springactors

import org.springframework.beans.factory.DisposableBean;
import actors.Actor
import actors.Exit

/** 
 * A trait that forwards spring's container dispose event to an actor. 
 */
trait DisposableSpringActorBean extends DisposableBean { self : Actor =>
  
  @throws(classOf[Exception])
  override def destroy() {
    Console.println("Shutting down: " + this)
    self ! Exit(this, "Spring Container Shutdown")
  }
}

import org.springframework.beans.factory.InitializingBean

/** 
 * This is a trait which will start an actor after a bean's properties have been set.
 */
trait InitializingSpringActorBean extends InitializingBean { 
  self : Actor =>  
  override def afterPropertiesSet() {
    start
  }
}