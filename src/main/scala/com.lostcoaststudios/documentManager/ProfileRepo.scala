package com.lostcoaststudios.documentManager

trait ProfileRepo {
  def get (id:String):Profile
}

class ProfileRepoImpl extends ProfileRepo {
  def get (id:String) = new Profile(id,"Gai" +id)
}

case class Profile (var id:String,var name:String)