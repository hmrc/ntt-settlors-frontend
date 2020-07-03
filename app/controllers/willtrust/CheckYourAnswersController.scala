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

package controllers.willtrust

import com.google.inject.Inject
import controllers.actions.{DataRequiredAction, DataRetrievalAction, IdentifierAction}
import models.{NormalMode, UserAnswers}
import navigation.Navigator
import pages.{CheckYourAnswersPage, LivingSettlorJourney}
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, MessagesControllerComponents}
import renderer.Renderer
import repositories.SessionRepository
import services.CountryService
import uk.gov.hmrc.play.bootstrap.controller.FrontendBaseController
import uk.gov.hmrc.viewmodels.{NunjucksSupport, SummaryList}
import utils.CheckYourAnswersHelper

import scala.concurrent.ExecutionContext

class CheckYourAnswersController @Inject()(
                                            override val messagesApi: MessagesApi,
                                            navigator: Navigator,
                                            identify: IdentifierAction,
                                            getData: DataRetrievalAction,
                                            requireData: DataRequiredAction,
                                            countryService: CountryService,
                                            sessionRepository: SessionRepository,
                                            val controllerComponents: MessagesControllerComponents,
                                            renderer: Renderer
)(implicit ec: ExecutionContext) extends FrontendBaseController with I18nSupport with NunjucksSupport {

  def onPageLoad(): Action[AnyContent] = (identify andThen getData andThen requireData).async {
    implicit request =>

      val helper = new CheckYourAnswersHelper(request.userAnswers, countryService)

      val answers: Seq[SummaryList.Row] = Seq(
              helper.wasTrustSetUpAfterSettlorDied,
              helper.whatIsSettlorsName,
              helper.doYouKnowDateOfDeath,
              helper.whatIsTheDateOfDeath,
              helper.doYouKnowDateOfBirth,
              helper.whatIsTheDateOfBirth,
              helper.doYouKnowCountryOfNationality,
              helper.whatIsCountryOfNationality,
              helper.isCountryOfNationalitySameAsCountryOfResidency,
              helper.doYouKnowCountryOfResidency,
              helper.whatIsCountryOfResidency)
        .filter(row => row.isDefined)
        .map(row => row.get)

      renderer.render(
        "check-your-answers-living.njk",
        Json.obj("list" -> answers)
      ).map(Ok(_))
  }

  def onSubmit: Action[AnyContent] = (identify andThen getData andThen requireData).async {
    implicit request =>
      val answers = UserAnswers(request.internalId)
      for {
        _              <- sessionRepository.set(answers)
      } yield Redirect(navigator.nextPage(CheckYourAnswersPage, NormalMode, answers))
  }
}
