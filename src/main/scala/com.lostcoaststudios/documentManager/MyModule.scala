package com.lostcoaststudios.documentManager

import com.google.inject._
/**
 * A servlet context listener which registers
 * <a href="http://code.google.com/p/google-guice/wiki/Servlets">Guice Servlet</a>
 *
 * @version $Revision: 1.1 $
 */

class MyModule extends AbstractModule {
  def configure() {
    bind(classOf[ProfileRepo]).to(classOf[ProfileRepoImpl])
  }
}



