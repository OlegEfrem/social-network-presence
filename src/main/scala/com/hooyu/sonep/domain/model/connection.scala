package com.hooyu.sonep.domain.model

import scala.collection.immutable.HashSet

case class ConnectionData(sn: String, people: Seq[Person], relationships: Seq[Relationship])

case class ConnectionDegree(number: Int, persons: HashSet[Person])

case class PersonConnections(person: Person, connectionDegrees: Seq[ConnectionDegree])
