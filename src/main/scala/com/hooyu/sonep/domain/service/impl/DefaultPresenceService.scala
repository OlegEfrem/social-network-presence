package com.hooyu.sonep.domain.service.impl

import com.hooyu.sonep.domain.model.{Graph, Person}
import com.hooyu.sonep.domain.service.NetworkPresenceService
import scala.collection.immutable.HashSet

class DefaultPresenceService(graph: Graph) extends NetworkPresenceService {
  override def noConnectionPeople: HashSet[Person] = {
    graph.allPersons.filter(graph.verticesFor(_).exists(_.isEmpty))
  }
}
