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

import org.scalacheck.Arbitrary
import pages._

trait PageGenerators {

  implicit lazy val arbitraryDoYouKnowCountryOfHeadOfficePage: Arbitrary[DoYouKnowCountryOfHeadOfficePage.type] =
    Arbitrary(DoYouKnowCountryOfHeadOfficePage)

  implicit lazy val arbitraryWhatIsCountryOfHeadOfficePage: Arbitrary[WhatIsCountryOfHeadOfficePage.type] =
    Arbitrary(WhatIsCountryOfHeadOfficePage)

  implicit lazy val arbitraryWhatIsBusinessNamePage: Arbitrary[WhatIsBusinessNamePage.type] =
    Arbitrary(WhatIsBusinessNamePage)

  implicit lazy val arbitraryIsSettlorLegallyIncapablePage: Arbitrary[IsSettlorLegallyIncapablePage.type] =
    Arbitrary(IsSettlorLegallyIncapablePage)

  implicit lazy val arbitraryIsSettlorIndividualOrBusinessPage: Arbitrary[IsSettlorIndividualOrBusinessPage.type] =
    Arbitrary(IsSettlorIndividualOrBusinessPage)

  implicit lazy val arbitraryWhatIsCountryOfNationalityPage: Arbitrary[WhatIsCountryOfNationalityPage.type] =
    Arbitrary(WhatIsCountryOfNationalityPage)

  implicit lazy val arbitraryWhatIsTheDateOfDeathPage: Arbitrary[WhatIsTheDateOfDeathPage.type] =
    Arbitrary(WhatIsTheDateOfDeathPage)

  implicit lazy val arbitraryWhatIsTheDateOfBirthPage: Arbitrary[WhatIsTheDateOfBirthPage.type] =
    Arbitrary(WhatIsTheDateOfBirthPage)

  implicit lazy val arbitraryWhatIsSettlorsNamePage: Arbitrary[WhatIsSettlorsNamePage.type] =
    Arbitrary(WhatIsSettlorsNamePage)

  implicit lazy val arbitraryWhatIsCountryOfResidencyPage: Arbitrary[WhatIsCountryOfResidencyPage.type] =
    Arbitrary(WhatIsCountryOfResidencyPage)

  implicit lazy val arbitraryWasTrustSetUpAfterSettlorDiedPage: Arbitrary[WasTrustSetUpAfterSettlorDiedPage.type] =
    Arbitrary(WasTrustSetUpAfterSettlorDiedPage)

  implicit lazy val arbitraryIsCountryOfNationalitySameAsCountryOfResidencyPage: Arbitrary[IsCountryOfNationalitySameAsCountryOfResidencyPage.type] =
    Arbitrary(IsCountryOfNationalitySameAsCountryOfResidencyPage)

  implicit lazy val arbitraryDoYouKnowDateOfDeathPage: Arbitrary[DoYouKnowDateOfDeathPage.type] =
    Arbitrary(DoYouKnowDateOfDeathPage)

  implicit lazy val arbitraryDoYouKnowDateOfBirthPage: Arbitrary[DoYouKnowDateOfBirthPage.type] =
    Arbitrary(DoYouKnowDateOfBirthPage)

  implicit lazy val arbitraryDoYouKnowCountryOfResidencyPage: Arbitrary[DoYouKnowCountryOfResidencyPage.type] =
    Arbitrary(DoYouKnowCountryOfResidencyPage)

  implicit lazy val arbitraryDoYouKnowCountryOfNationalityPage: Arbitrary[DoYouKnowCountryOfNationalityPage.type] =
    Arbitrary(DoYouKnowCountryOfNationalityPage)
}
