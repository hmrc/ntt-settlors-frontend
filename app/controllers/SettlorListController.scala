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

package controllers

import com.google.inject.Inject
import controllers.actions.{DataRequiredAction, DataRetrievalAction, IdentifierAction}
import forms.IsSettlorIndividualOrBusinessFormProvider
import models.NormalMode
import pages.IsSettlorIndividualOrBusinessPage
import play.api.data.Field
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, MessagesControllerComponents}
import renderer.Renderer
import services.CountryService
import uk.gov.hmrc.play.bootstrap.controller.FrontendBaseController
import uk.gov.hmrc.viewmodels.Radios.Item
import uk.gov.hmrc.viewmodels.{NunjucksSupport, Radios, SummaryList}
import utils.CheckYourAnswersHelper

import scala.concurrent.ExecutionContext

class SettlorListController @Inject()(
    override val messagesApi: MessagesApi,
    identify: IdentifierAction,
    getData: DataRetrievalAction,
    requireData: DataRequiredAction,
    countryService: CountryService,
    formProvider: IsSettlorIndividualOrBusinessFormProvider,
    val controllerComponents: MessagesControllerComponents,
    renderer: Renderer
)(implicit ec: ExecutionContext) extends FrontendBaseController with I18nSupport with NunjucksSupport {

  private def radios(field: Field)(implicit messages: Messages): Seq[Item] = Seq(
    Item(id = s"${field.id}-yes-now", text = msg"settlorlist.yes.now", value = "yesnow", checked = field.value.contains("yesnow")),
    Item(id = s"${field.id}-yes-later", text = msg"settlorlist.yes.later", value = "yeslater", checked = field.value.contains("yeslater")),
    Item(id = s"${field.id}-no", text = msg"settlorlist.no", value = "no", checked = field.value.contains("no"))
  )

  private val form = formProvider()

  def onPageLoad(): Action[AnyContent] = (identify andThen getData andThen requireData).async {
    implicit request =>

      val json = Json.obj(
        "form"   -> form,
        "mode"   -> NormalMode,
        "radios" -> radios(form("value"))
      )

      renderer.render("settlor-list.njk", json).map(Ok(_))
  }
}
