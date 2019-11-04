package com.hooyu.sonep.domain.service.impl

import com.hooyu.sonep.domain.model.{Graph, Person, UnitTest}
import scala.collection.immutable.HashSet

class DefaultPresenceServiceTest extends UnitTest {
  val graph                         = Graph(connectionData)
  def service(graph: Graph = graph) = new DefaultPresenceService(graph)

  "noConnectionPeople should" - {
    "return the persons without connections" in {
      service().noConnectionPeople shouldBe HashSet(noConnectionPerson)
    }
    "return empty list if there are no persons without connections" in {
      val graphWithConnectionsPersons = Graph(connectionData.copy(people = persons.filterNot(_ == noConnectionPerson)))
      service(graphWithConnectionsPersons).noConnectionPeople shouldBe empty
    }
  }

  "findAllConnections should" - {
    "find all existing degree 1 connections of a person" in {
      service().findAllConnections(john, 1).value.connectionDegrees.headOption.value.persons should contain theSameElementsAs relationsHashMap.get(john).value
    }

    "find all existing degree 2 connections of a person " in {
      val result = service().findAllConnections(john, 2)
      println(result)
      result.value.connectionDegrees.lastOption.value.persons shouldBe HashSet(anna)
    }

    "return None for a non existing person" in {
      service().findAllConnections(Person("NonExistingPerson"), 2) shouldBe None
    }

    "return empty connections for a person without connections" in {
      service().findAllConnections(noConnectionPerson, 2).value.connectionDegrees shouldBe empty
    }
  }
}
