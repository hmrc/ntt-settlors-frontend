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
import pages._
import models._

@Singleton
class Navigator @Inject()() {

  private val normalRoutes: Page => UserAnswers => Call = {
    case  IndexPage => _ => routes.WasTrustSetUpAfterSettlorDiedController.onPageLoad(NormalMode)
    case  WasTrustSetUpAfterSettlorDiedPage => _ => routes.WhatIsSettlorsNameController.onPageLoad(NormalMode)
    case  WhatIsSettlorsNamePage => _ => routes.DoYouKnowDateOfDeathController.onPageLoad(NormalMode)
    case  DoYouKnowDateOfDeathPage => _ => routes.WhatIsTheDateOfDeathController.onPageLoad(NormalMode)
    case  WhatIsTheDateOfDeathPage => _ => routes.DoYouKnowDateOfBirthController.onPageLoad(NormalMode)
    case  DoYouKnowDateOfBirthPage => _ => routes.WhatIsTheDateOfBirthController.onPageLoad(NormalMode)
    case  WhatIsTheDateOfBirthPage => _ => routes.DoYouKnowCountryOfNationalityController.onPageLoad(NormalMode)
    case  WhatIsCountryOfNationalityPage => _ => routes.IsCountryOfNationalitySameAsCountryOfResidencyController.onPageLoad(NormalMode)
    case  IsCountryOfNationalitySameAsCountryOfResidencyPage => _ => routes.DoYouKnowCountryOfResidencyController.onPageLoad(NormalMode)
    case  DoYouKnowCountryOfResidencyPage => _ => routes.WhatIsCountryOfResidencyController.onPageLoad(NormalMode)
  }

  private val checkRouteMap: Page => UserAnswers => Call = {
    case _ => _ => routes.CheckYourAnswersController.onPageLoad()
  }

  def nextPage(page: Page, mode: Mode, userAnswers: UserAnswers): Call = mode match {
    case NormalMode =>
      normalRoutes(page)(userAnswers)
    case CheckMode =>
      checkRouteMap(page)(userAnswers)
  }
}
