package com.hooyu.sonep.domain.service.impl

import com.hooyu.sonep.domain.model.{ConnectionDegree, Graph, Person, PersonConnections}
import com.hooyu.sonep.domain.service.NetworkPresenceService
import scala.annotation.tailrec
import scala.collection.immutable.HashSet

/** Default implementation of [[NetworkPresenceService]].
  * @note this is service package private as the implementation should not be exposed, so we can change it.
  * An instance of it must only be created via [[NetworkPresenceService]] companion factory method.
  * */
private[service] class DefaultPresenceService(graph: Graph) extends NetworkPresenceService {

  override def noConnectionPeople: HashSet[Person] = {
    graph.allPersons.filter(graph.verticesFor(_).exists(_.isEmpty))
  }

  override def findAllConnections(of: Person, upTo: SeparationDegree): Option[PersonConnections] = {
    require(upTo > 0, s"separation degree must be bigger than zero, but was: $upTo")
    val allConnections   = findConnectionsFor(of, HashSet(of), (1 to upTo).toList, Seq())
    val maybeARootPerson = graph.verticesFor(of)
    maybeARootPerson.map(_ => PersonConnections(of, allConnections))
  }
  @tailrec
  private def findConnectionsFor(rootPerson: Person,
                                 persons: HashSet[Person],
                                 degrees: List[SeparationDegree],
                                 result: Seq[ConnectionDegree]): Seq[ConnectionDegree] = {
    degrees match {
      case Nil => result
      case h :: t =>
        val cd = connectionDegreeFor(rootPerson, persons, h)
        if (cd.persons.isEmpty) result
        else findConnectionsFor(rootPerson, cd.persons, t, result.appended(cd))
    }
  }

  private def connectionDegreeFor(rootPerson: Person, persons: HashSet[Person], degree: Int): ConnectionDegree = {
    val previousDegreesPersons = persons + rootPerson
    val connections = for {
      person     <- persons
      connection <- graph.verticesFor(person).toSeq.flatten
    } yield connection
    ConnectionDegree(degree, connections.removedAll(previousDegreesPersons))
  }

}
