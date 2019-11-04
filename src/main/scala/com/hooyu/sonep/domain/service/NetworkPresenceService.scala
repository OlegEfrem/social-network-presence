package com.hooyu.sonep.domain.service

import com.hooyu.sonep.domain.model.{Graph, Person, PersonConnections}
import com.hooyu.sonep.domain.service.impl.DefaultPresenceService
import scala.collection.immutable.HashSet

trait NetworkPresenceService {
  type SeparationDegree = Int
  def noConnectionPeople: HashSet[Person]
  def findAllConnections(of: Person, upTo: SeparationDegree): Option[PersonConnections]
}

/** Companion object with factory method deciding on the chosen implementation.
  * */
object NetworkPresenceService {
  def apply(graph: Graph): NetworkPresenceService = new DefaultPresenceService(graph)
}
