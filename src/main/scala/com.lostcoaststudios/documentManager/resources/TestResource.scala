package com.lostcoaststudios.documentManager.resources

import reflect.{BeanProperty}
import javax.ws.rs.{PathParam, GET, Path, Produces}
import java.util.logging.Logger
import com.google.inject.Inject
import com.lostcoaststudios.documentManager.ProfileRepo

//@ImplicitProduces(Array("text/html;qs=5"))
@Path("/profiles")
@Produces(Array("application/json"))
class ProfilesResource @Inject()(logger:Logger, profileRepo:ProfileRepo) {
  @GET
  def getAll = Array(new ProfileDTO("12","MGai") , new ProfileDTO("13","BGai"))

  @GET
  @Path("/{id}")
  def get(@PathParam("id") id:String) = {
    logger.info("ECHOING " + id)
    val p = profileRepo.get(id)
    new ProfileDTO(p.id,p.name)
  }

}

case class  ProfileDTO (
  @BeanProperty id:String,
  @BeanProperty name:String);