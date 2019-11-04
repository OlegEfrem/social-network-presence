package com.hooyu.sonep.domain.service

import com.hooyu.sonep.domain.model.Person

import scala.collection.immutable.HashSet

trait NetworkPresenceService {
  def noConnectionPeople: HashSet[Person]
}
