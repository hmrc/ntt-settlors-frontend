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

package navigation

import base.SpecBase
import controllers.routes
import controllers.willtrust.{routes => willRoutes}
import controllers.livingsettlor.{routes => livingRoutes}
import generators.Generators
import pages._
import models._
import org.scalacheck.Arbitrary.arbitrary
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class NavigatorSpec extends SpecBase with ScalaCheckPropertyChecks with Generators {

  val navigator = new Navigator

  "Navigator" - {

    "in Will Trust Journey" - {

      "in Normal mode" - {

          s"must go from ${WasTrustSetUpAfterSettlorDiedPage} to ${WhatIsSettlorsNamePage}" in {
            forAll(arbitrary[UserAnswers]) {
              answers =>
                navigator.nextPage(WasTrustSetUpAfterSettlorDiedPage, NormalMode, answers)
                  .mustBe(willRoutes.WhatIsSettlorsNameController.onPageLoad(NormalMode))
            }
          }

          s"must go from ${WhatIsSettlorsNamePage} to ${DoYouKnowDateOfDeathPage}" in {
            forAll(arbitrary[UserAnswers]) {
              answers =>
                navigator.nextPage(WhatIsSettlorsNamePage, NormalMode, answers)
                  .mustBe(willRoutes.DoYouKnowDateOfDeathController.onPageLoad(NormalMode))
            }
          }

          s"must go from ${DoYouKnowDateOfDeathPage} to ${WhatIsTheDateOfDeathPage}" in {
            forAll(arbitrary[UserAnswers]) {
              answers =>
                navigator.nextPage(DoYouKnowDateOfDeathPage, NormalMode, answers)
                  .mustBe(willRoutes.WhatIsTheDateOfDeathController.onPageLoad(NormalMode))
            }
          }

          s"must go from ${WhatIsTheDateOfDeathPage} to ${DoYouKnowDateOfBirthPage}" in {
            forAll(arbitrary[UserAnswers]) {
              answers =>
                navigator.nextPage(WhatIsTheDateOfDeathPage, NormalMode, answers)
                  .mustBe(willRoutes.DoYouKnowDateOfBirthController.onPageLoad(NormalMode))
            }
          }

          s"must go from ${DoYouKnowDateOfBirthPage} to ${WhatIsTheDateOfBirthPage}" in {
            forAll(arbitrary[UserAnswers]) {
              answers =>
                navigator.nextPage(DoYouKnowDateOfBirthPage, NormalMode, answers)
                  .mustBe(willRoutes.WhatIsTheDateOfBirthController.onPageLoad(NormalMode))
            }
          }

          s"must go from ${WhatIsTheDateOfBirthPage} to ${DoYouKnowCountryOfNationalityPage}" in {
            forAll(arbitrary[UserAnswers]) {
              answers =>
                navigator.nextPage(WhatIsTheDateOfBirthPage, NormalMode, answers)
                  .mustBe(willRoutes.DoYouKnowCountryOfNationalityController.onPageLoad(NormalMode))
            }
          }

          s"must go from ${DoYouKnowCountryOfNationalityPage} to ${WhatIsCountryOfNationalityPage}" in {
            forAll(arbitrary[UserAnswers]) {
              answers =>
                navigator.nextPage(DoYouKnowCountryOfNationalityPage, NormalMode, answers)
                  .mustBe(willRoutes.WhatIsCountryOfNationalityController.onPageLoad(NormalMode))
            }
          }

          s"must go from ${WhatIsCountryOfNationalityPage} to ${IsCountryOfNationalitySameAsCountryOfResidencyPage}" in {
            forAll(arbitrary[UserAnswers]) {
              answers =>
                navigator.nextPage(WhatIsCountryOfNationalityPage, NormalMode, answers)
                  .mustBe(willRoutes.IsCountryOfNationalitySameAsCountryOfResidencyController.onPageLoad(NormalMode))
            }
          }

          s"must go from ${IsCountryOfNationalitySameAsCountryOfResidencyPage} to ${DoYouKnowCountryOfResidencyPage}" in {
            forAll(arbitrary[UserAnswers]) {
              answers =>
                navigator.nextPage(IsCountryOfNationalitySameAsCountryOfResidencyPage, NormalMode, answers)
                  .mustBe(willRoutes.DoYouKnowCountryOfResidencyController.onPageLoad(NormalMode))
            }
          }

          s"must go from ${DoYouKnowCountryOfResidencyPage} to ${WhatIsCountryOfResidencyPage}" in {
            forAll(arbitrary[UserAnswers]) {
              answers =>
                navigator.nextPage(DoYouKnowCountryOfResidencyPage, NormalMode, answers)
                  .mustBe(willRoutes.WhatIsCountryOfResidencyController.onPageLoad(NormalMode))
            }
          }

          s"must go from ${WhatIsCountryOfResidencyPage} to ${CheckYourAnswersPage}" in {
            forAll(arbitrary[UserAnswers]) {
              answers =>
                navigator.nextPage(WhatIsCountryOfResidencyPage, NormalMode, answers)
                  .mustBe(willRoutes.CheckYourAnswersController.onPageLoad())
            }
          }

          s"must go from ${CheckYourAnswersPage} to ${SettlorListPage}" in {
            forAll(arbitrary[UserAnswers]) {
              answers =>
                navigator.nextPage(CheckYourAnswersPage, NormalMode, answers)
                  .mustBe(routes.SettlorListController.onPageLoad())
            }
          }
        }

        "in Check mode" - {
            Set(
            WillTrustJourney, WasTrustSetUpAfterSettlorDiedPage, WhatIsSettlorsNamePage,
            DoYouKnowDateOfDeathPage, WhatIsTheDateOfDeathPage, DoYouKnowDateOfBirthPage,
            WhatIsTheDateOfBirthPage, DoYouKnowCountryOfNationalityPage, WhatIsCountryOfNationalityPage,
            IsCountryOfNationalitySameAsCountryOfResidencyPage, DoYouKnowCountryOfResidencyPage, WhatIsCountryOfResidencyPage
            ).foreach(page => {
              s"must go from ${page} to Check Your Answers" in {
                forAll(arbitrary[UserAnswers]) {
                  answers =>
                    navigator.nextPage(page, CheckMode, answers)
                      .mustBe(willRoutes.CheckYourAnswersController.onPageLoad())
                }
              }
            })
        }
      }


    }

  "in Living Settlor Journey" - {

    "in Normal mode" - {

      s"must go from ${IsSettlorIndividualOrBusinessPage} to ${IsSettlorLegallyIncapablePage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(IsSettlorIndividualOrBusinessPage, NormalMode, answers)
              .mustBe(livingRoutes.IsSettlorLegallyIncapableController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${IsSettlorLegallyIncapablePage} to ${WhatIsBusinessNamePage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(IsSettlorLegallyIncapablePage, NormalMode, answers)
              .mustBe(livingRoutes.WhatIsBusinessNameController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsBusinessNamePage} to ${DoYouKnowCountryOfHeadOfficePage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsBusinessNamePage, NormalMode, answers)
              .mustBe(livingRoutes.DoYouKnowCountryOfHeadOfficeController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${DoYouKnowCountryOfHeadOfficePage} to ${WhatIsCountryOfHeadOfficePage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(DoYouKnowCountryOfHeadOfficePage, NormalMode, answers)
              .mustBe(livingRoutes.WhatIsCountryOfHeadOfficeController.onPageLoad(NormalMode))
        }
      }

      s"must go from ${WhatIsCountryOfHeadOfficePage} to ${CheckYourAnswersPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(WhatIsCountryOfHeadOfficePage, NormalMode, answers)
              .mustBe(livingRoutes.CheckYourAnswersController.onPageLoad())
        }
      }

      s"must go from ${CheckYourAnswersPage} to ${SettlorListPage}" in {
        forAll(arbitrary[UserAnswers]) {
          answers =>
            navigator.nextPage(CheckYourAnswersPage, NormalMode, answers)
              .mustBe(routes.SettlorListController.onPageLoad())
        }
      }
    }

    "in Check mode" - {
      Set(
        IsSettlorIndividualOrBusinessPage, IsSettlorLegallyIncapablePage, WhatIsBusinessNamePage,
        DoYouKnowCountryOfHeadOfficePage, WhatIsCountryOfHeadOfficePage
      ).foreach(page => {
        s"must go from ${page} to Check Your Answers" in {
          forAll(arbitrary[UserAnswers]) {
            answers =>
              navigator.nextPage(page, CheckMode, answers)
                .mustBe(livingRoutes.CheckYourAnswersController.onPageLoad())
          }
        }
      })
    }
  }
}
