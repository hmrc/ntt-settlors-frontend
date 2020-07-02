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

import javax.inject.{Inject, Singleton}

import play.api.mvc.Call
import controllers.routes
import controllers.willtrust.{routes => willRoutes}
import controllers.livingsettlor.{routes => livingRoutes}
import pages._
import models._

@Singleton
class Navigator @Inject()() {

  private val normalRoutes: Page => UserAnswers => Call = {
    case  WillTrustJourney                  => _ => willRoutes.WasTrustSetUpAfterSettlorDiedController.onPageLoad(NormalMode)
    case  WasTrustSetUpAfterSettlorDiedPage => _ => willRoutes.WhatIsSettlorsNameController.onPageLoad(NormalMode)
    case  WhatIsSettlorsNamePage            => _ => willRoutes.DoYouKnowDateOfDeathController.onPageLoad(NormalMode)
    case  DoYouKnowDateOfDeathPage          => _ => willRoutes.WhatIsTheDateOfDeathController.onPageLoad(NormalMode)
    case  WhatIsTheDateOfDeathPage          => _ => willRoutes.DoYouKnowDateOfBirthController.onPageLoad(NormalMode)
    case  DoYouKnowDateOfBirthPage          => _ => willRoutes.WhatIsTheDateOfBirthController.onPageLoad(NormalMode)
    case  WhatIsTheDateOfBirthPage          => _ => willRoutes.DoYouKnowCountryOfNationalityController.onPageLoad(NormalMode)
    case  DoYouKnowCountryOfNationalityPage => _ => willRoutes.WhatIsCountryOfNationalityController.onPageLoad(NormalMode)
    case  WhatIsCountryOfNationalityPage    => _ => willRoutes.IsCountryOfNationalitySameAsCountryOfResidencyController.onPageLoad(NormalMode)
    case  IsCountryOfNationalitySameAsCountryOfResidencyPage => _ => willRoutes.DoYouKnowCountryOfResidencyController.onPageLoad(NormalMode)
    case  DoYouKnowCountryOfResidencyPage   => _ => willRoutes.WhatIsCountryOfResidencyController.onPageLoad(NormalMode)
    case  WhatIsCountryOfResidencyPage      => _ => willRoutes.CheckYourAnswersController.onPageLoad()

    case  LivingSettlorJourney              => _ => livingRoutes.IsSettlorIndividualOrBusinessController.onPageLoad(NormalMode)
    case  IsSettlorIndividualOrBusinessPage => _ => livingRoutes.IsSettlorLegallyIncapableController.onPageLoad(NormalMode)
    case  IsSettlorLegallyIncapablePage     => _ => livingRoutes.WhatIsBusinessNameController.onPageLoad(NormalMode)
    case  WhatIsBusinessNamePage            => _ => livingRoutes.DoYouKnowCountryOfHeadOfficeController.onPageLoad(NormalMode)
    case  DoYouKnowCountryOfHeadOfficePage  => _ => livingRoutes.WhatIsCountryOfHeadOfficeController.onPageLoad(NormalMode)
    case  WhatIsCountryOfHeadOfficePage     => _ => livingRoutes.CheckYourAnswersController.onPageLoad()

    case  CheckYourAnswersPage => _ => routes.SettlorListController.onPageLoad()
  }

  private val checkRouteMap: Page => UserAnswers => Call = {

    case  IsSettlorIndividualOrBusinessPage => _ => livingRoutes.CheckYourAnswersController.onPageLoad()
    case  IsSettlorLegallyIncapablePage     => _ => livingRoutes.CheckYourAnswersController.onPageLoad()
    case  WhatIsBusinessNamePage            => _ => livingRoutes.CheckYourAnswersController.onPageLoad()
    case  DoYouKnowCountryOfHeadOfficePage  => _ => livingRoutes.CheckYourAnswersController.onPageLoad()
    case  WhatIsCountryOfHeadOfficePage     => _ => livingRoutes.CheckYourAnswersController.onPageLoad()

    case _ => _ => willRoutes.CheckYourAnswersController.onPageLoad()
  }

  def nextPage(page: Page, mode: Mode, userAnswers: UserAnswers): Call = mode match {
    case NormalMode =>
      normalRoutes(page)(userAnswers)
    case CheckMode =>
      checkRouteMap(page)(userAnswers)
  }
}
