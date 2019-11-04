package com.hooyu.sonep.domain.model

class AdjacencyListTest extends UnitTest {
  val adjacencyList = AdjacencyList(connectionData)

  "verticesFor should" - {

    "return found connections for the person" in {
      adjacencyList.verticesFor(john).value should contain theSameElementsAs relationsHashMap.get(john).value
    }

    "return None if person is not found" in {
      adjacencyList.verticesFor(Person("NonExistentPerson")) shouldBe None
    }

    "return empty set if person has no connections" in {
      adjacencyList.verticesFor(harry).value shouldBe empty
    }
  }

  "allPersons should" - {
    "return all persons in the graph" in {
      adjacencyList.allPersons should contain theSameElementsAs persons
    }

    "return empty set if no persons are in the graph" in {
      val graph = AdjacencyList(connectionData.copy(people = Seq()))
      graph.allPersons shouldBe empty
    }

  }

  "apply should" - {
    "map connection people to graph persons" in {
      adjacencyList.allPersons should contain theSameElementsAs persons
    }
    "map connection relationships to graph relationships" in {
      adjacencyList.verticesFor(john).value should contain theSameElementsAs relationsHashMap.get(john).value
    }

    "map empty connection people list to empty graph" in {
      val graph = AdjacencyList(connectionData.copy(people = Seq()))
      graph.allPersons shouldBe empty
    }
    "map empty connection relationships to empty graph persons relationships" in {
      val graph = AdjacencyList(connectionData.copy(relationships = Seq()))
      graph.verticesFor(john).value shouldBe empty
    }
  }
}
