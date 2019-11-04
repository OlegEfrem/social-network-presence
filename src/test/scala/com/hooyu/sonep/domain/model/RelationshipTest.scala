package com.hooyu.sonep.domain.model

class RelationshipTest extends UnitTest {
  "findRelatedTo should" - {
    "return related person" in {
      john_peter.findRelatedTo(john).value shouldBe peter
    }
    "return None when it is not a related person" in {
      john_peter.findRelatedTo(Person("NotRelatedPerson")) shouldBe None
    }
  }
}
