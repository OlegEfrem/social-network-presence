package com.hooyu.sonep.domain.service.impl

import com.hooyu.sonep.domain.model.{Graph, UnitTest}
import scala.collection.immutable.HashSet

class DefaultPresenceServiceTest extends UnitTest {
  def service(graph: Graph) = new DefaultPresenceService(graph)

  "noConnectionPeople should" - {
    "return the persons without connections" in {
      val graph = Graph(connectionData)
      service(graph).noConnectionPeople shouldBe HashSet(harry)
    }
    "return empty list if there are no persons without connections" in {
      val graph = Graph(connectionData.copy(people = persons.filterNot(_ == harry)))
      service(graph).noConnectionPeople shouldBe empty
    }

  }
}
