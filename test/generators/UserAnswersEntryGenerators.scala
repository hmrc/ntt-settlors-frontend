/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package generators

import models._
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary.arbitrary
import pages._
import play.api.libs.json.{JsValue, Json}

trait UserAnswersEntryGenerators extends PageGenerators with ModelGenerators {

  implicit lazy val arbitraryWhatIsCountryOfNationalityUserAnswersEntry: Arbitrary[(WhatIsCountryOfNationalityPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsCountryOfNationalityPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsTheDateOfDeathUserAnswersEntry: Arbitrary[(WhatIsTheDateOfDeathPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsTheDateOfDeathPage.type]
        value <- arbitrary[Int].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsTheDateOfBirthUserAnswersEntry: Arbitrary[(WhatIsTheDateOfBirthPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsTheDateOfBirthPage.type]
        value <- arbitrary[Int].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsSettlorsNameUserAnswersEntry: Arbitrary[(WhatIsSettlorsNamePage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsSettlorsNamePage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsCountryOfResidencyUserAnswersEntry: Arbitrary[(WhatIsCountryOfResidencyPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsCountryOfResidencyPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWasTrustSetUpAfterSettlorDiedUserAnswersEntry: Arbitrary[(WasTrustSetUpAfterSettlorDiedPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WasTrustSetUpAfterSettlorDiedPage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryIsCountryOfNationalitySameAsCountryOfResidencyUserAnswersEntry: Arbitrary[(IsCountryOfNationalitySameAsCountryOfResidencyPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[IsCountryOfNationalitySameAsCountryOfResidencyPage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryDoYouKnowDateOfDeathUserAnswersEntry: Arbitrary[(DoYouKnowDateOfDeathPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[DoYouKnowDateOfDeathPage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryDoYouKnowDateOfBirthUserAnswersEntry: Arbitrary[(DoYouKnowDateOfBirthPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[DoYouKnowDateOfBirthPage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryDoYouKnowCountryOfResidencyUserAnswersEntry: Arbitrary[(DoYouKnowCountryOfResidencyPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[DoYouKnowCountryOfResidencyPage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryDoYouKnowCountryOfNationalityUserAnswersEntry: Arbitrary[(DoYouKnowCountryOfNationalityPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[DoYouKnowCountryOfNationalityPage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }
}
