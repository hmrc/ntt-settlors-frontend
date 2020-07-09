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

import base.SpecBase
import forms.IsSettlorIndividualOrBusinessFormProvider
import models.NormalMode
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._
import org.mockito.Matchers.any
import play.api.mvc.Call
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.twirl.api.Html
import uk.gov.hmrc.viewmodels.Radios
import play.api.libs.json.{JsObject, Json}
import matchers.JsonMatchers
import org.scalatestplus.mockito.MockitoSugar
import play.api.data.Field
import play.api.i18n.Messages
import uk.gov.hmrc.viewmodels.NunjucksSupport
import uk.gov.hmrc.viewmodels.Radios.Item

import scala.concurrent.Future

class SettlorListControllerSpec extends SpecBase with MockitoSugar with NunjucksSupport with JsonMatchers {
  def onwardRoute = Call("GET", "/foo")

  def radios(field: Field)(implicit messages: Messages): Seq[Item] = Seq(
    Item(id = s"${field.id}-yes-now", text = msg"settlorlist.yes.now", value = "yesnow", checked = field.value.contains("yesnow")),
    Item(id = s"${field.id}-yes-later", text = msg"settlorlist.yes.later", value = "yeslater", checked = field.value.contains("yeslater")),
    Item(id = s"${field.id}-no", text = msg"settlorlist.no", value = "no", checked = field.value.contains("no"))
  )

  val formProvider = new IsSettlorIndividualOrBusinessFormProvider()
  val form = formProvider()

  lazy val settlorListControllerRoute = routes.SettlorListController.onPageLoad().url

  "IsSettlorIndividualOrBusiness Controller" - {

    "must return OK and the correct view for a GET" in {

      when(mockRenderer.render(any(), any())(any()))
        .thenReturn(Future.successful(Html("")))

      val application = applicationBuilder(userAnswers = Some(emptyUserAnswers)).build()
      val request = FakeRequest(GET, settlorListControllerRoute)
      val templateCaptor = ArgumentCaptor.forClass(classOf[String])
      val jsonCaptor = ArgumentCaptor.forClass(classOf[JsObject])

      val result = route(application, request).value

      status(result) mustEqual OK

      verify(mockRenderer, times(1)).render(templateCaptor.capture(), jsonCaptor.capture())(any())

      val expectedJson = Json.obj(
        "form" -> form,
        "mode" -> NormalMode,
        "radios" -> radios(form("value"))
      )

      templateCaptor.getValue mustEqual "settlor-list.njk"
      jsonCaptor.getValue must containJson(expectedJson)

      application.stop()
    }
  }
}
