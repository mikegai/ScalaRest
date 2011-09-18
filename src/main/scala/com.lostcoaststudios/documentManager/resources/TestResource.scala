package com.lostcoaststudios.documentManager.resources

import reflect.BeanProperty
import java.util.logging.Logger
import com.google.inject.Inject
import com.lostcoaststudios.documentManager.ProfileRepo
import com.sun.jersey.multipart.FormDataParam
import javax.servlet.http.HttpServletResponse
import javax.ws.rs.core.MediaType
import javax.ws.rs._
import java.security.MessageDigest

//@ImplicitProduces(Array("text/html;qs=5"))
@Path("/profiles")
@Produces(Array("application/json"))
class ProfilesResource @Inject()(logger: Logger, profileRepo: ProfileRepo, response: HttpServletResponse) {

  @GET
  def getAll = Array(new ProfileDTO("12", "MGai"), new ProfileDTO("13", "BGai"))

  @GET
  @Path("/{id}")
  def get(@PathParam("id") id: String) = {
    logger.info("ECHOING " + id)
    val p = profileRepo.get(id)
    new ProfileDTO(p.id, p.name)
  }


  @POST
  @Path("/docs/{name}")
  @Consumes(Array(MediaType.MULTIPART_FORM_DATA))
  @Produces(Array(MediaType.APPLICATION_JSON))
  def uploadFile(@PathParam("name") fileName: String,
                 @FormDataParam("type") myType: String,
                 @FormDataParam("content") content: java.io.InputStream): String = {
    logger.info("receiving file " + fileName)

    //calculate hash for the fun of it
    val md = MessageDigest.getInstance("MD5")

    val buffer = new Array[Byte](1024)
    Stream.continually(content.read(buffer))
      .takeWhile(_ != -1)
      .foreach(md.update(buffer, 0, _))
    new sun.misc.BASE64Encoder().encode(md.digest)
  }
}

case class ProfileDTO(
                       @BeanProperty id: String,
                       @BeanProperty name: String)