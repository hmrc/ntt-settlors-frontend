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

package utils

import java.time.format.DateTimeFormatter

import controllers.willtrust.{routes => willRoutes}
import controllers.livingsettlor.{routes => livingRoutes}
import models.{CheckMode, Name, UserAnswers}
import pages._
import play.api.i18n.{Messages, MessagesApi}
import CheckYourAnswersHelper._
import services.CountryService
import uk.gov.hmrc.viewmodels._
import uk.gov.hmrc.viewmodels.SummaryList._
import uk.gov.hmrc.viewmodels.Text.Literal

class CheckYourAnswersHelper (userAnswers: UserAnswers,  countryService: CountryService )(implicit messages: Messages) {

  def doYouKnowCountryOfHeadOffice: Option[Row] = userAnswers.get(DoYouKnowCountryOfHeadOfficePage) map {
    answer =>
      Row(
        key     = Key(msg"doYouKnowCountryOfHeadOffice.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = livingRoutes.DoYouKnowCountryOfHeadOfficeController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"doYouKnowCountryOfHeadOffice.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsCountryOfHeadOffice: Option[Row] = userAnswers.get(WhatIsCountryOfHeadOfficePage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsCountryOfHeadOffice.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(country(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = livingRoutes.WhatIsCountryOfHeadOfficeController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsCountryOfHeadOffice.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsBusinessName: Option[Row] = userAnswers.get(WhatIsBusinessNamePage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsBusinessName.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = livingRoutes.WhatIsBusinessNameController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsBusinessName.checkYourAnswersLabel"))
          )
        )
      )
  }

  def isSettlorLegallyIncapable: Option[Row] = userAnswers.get(IsSettlorLegallyIncapablePage) map {
    answer =>
      Row(
        key     = Key(msg"isSettlorLegallyIncapable.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = livingRoutes.IsSettlorLegallyIncapableController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"isSettlorLegallyIncapable.checkYourAnswersLabel"))
          )
        )
      )
  }

  def isSettlorIndividualOrBusiness: Option[Row] = userAnswers.get(IsSettlorIndividualOrBusinessPage) map {
    answer =>
      Row(
        key     = Key(msg"isSettlorIndividualOrBusiness.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = livingRoutes.IsSettlorIndividualOrBusinessController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"isSettlorIndividualOrBusiness.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsCountryOfNationality: Option[Row] = userAnswers.get(WhatIsCountryOfNationalityPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsCountryOfNationality.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(country(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = willRoutes.WhatIsCountryOfNationalityController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsCountryOfNationality.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheDateOfDeath: Option[Row] = userAnswers.get(WhatIsTheDateOfDeathPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheDateOfDeath.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(Literal(answer.format(dateFormatter))),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = willRoutes.WhatIsTheDateOfDeathController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheDateOfDeath.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheDateOfBirth: Option[Row] = userAnswers.get(WhatIsTheDateOfBirthPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheDateOfBirth.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(Literal(answer.format(dateFormatter))),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = willRoutes.WhatIsTheDateOfBirthController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheDateOfBirth.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsSettlorsName: Option[Row] = userAnswers.get(WhatIsSettlorsNamePage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsSettlorsName.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(name(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = willRoutes.WhatIsSettlorsNameController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsSettlorsName.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsCountryOfResidency: Option[Row] = userAnswers.get(WhatIsCountryOfResidencyPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsCountryOfResidency.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(country(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = willRoutes.WhatIsCountryOfResidencyController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsCountryOfResidency.checkYourAnswersLabel"))
          )
        )
      )
  }

  def wasTrustSetUpAfterSettlorDied: Option[Row] = userAnswers.get(WasTrustSetUpAfterSettlorDiedPage) map {
    answer =>
      Row(
        key     = Key(msg"wasTrustSetUpAfterSettlorDied.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = willRoutes.WasTrustSetUpAfterSettlorDiedController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"wasTrustSetUpAfterSettlorDied.checkYourAnswersLabel"))
          )
        )
      )
  }

  def isCountryOfNationalitySameAsCountryOfResidency: Option[Row] = userAnswers.get(IsCountryOfNationalitySameAsCountryOfResidencyPage) map {
    answer =>
      Row(
        key     = Key(msg"isCountryOfNationalitySameAsCountryOfResidency.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = willRoutes.IsCountryOfNationalitySameAsCountryOfResidencyController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"isCountryOfNationalitySameAsCountryOfResidency.checkYourAnswersLabel"))
          )
        )
      )
  }

  def doYouKnowDateOfDeath: Option[Row] = userAnswers.get(DoYouKnowDateOfDeathPage) map {
    answer =>
      Row(
        key     = Key(msg"doYouKnowDateOfDeath.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = willRoutes.DoYouKnowDateOfDeathController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"doYouKnowDateOfDeath.checkYourAnswersLabel"))
          )
        )
      )
  }

  def doYouKnowDateOfBirth: Option[Row] = userAnswers.get(DoYouKnowDateOfBirthPage) map {
    answer =>
      Row(
        key     = Key(msg"doYouKnowDateOfBirth.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = willRoutes.DoYouKnowDateOfBirthController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"doYouKnowDateOfBirth.checkYourAnswersLabel"))
          )
        )
      )
  }

  def doYouKnowCountryOfResidency: Option[Row] = userAnswers.get(DoYouKnowCountryOfResidencyPage) map {
    answer =>
      Row(
        key     = Key(msg"doYouKnowCountryOfResidency.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = willRoutes.DoYouKnowCountryOfResidencyController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"doYouKnowCountryOfResidency.checkYourAnswersLabel"))
          )
        )
      )
  }

  def doYouKnowCountryOfNationality: Option[Row] = userAnswers.get(DoYouKnowCountryOfNationalityPage) map {
    answer =>
      Row(
        key     = Key(msg"doYouKnowCountryOfNationality.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = willRoutes.DoYouKnowCountryOfNationalityController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"doYouKnowCountryOfNationality.checkYourAnswersLabel"))
          )
        )
      )
  }

  private def country(code: String): Content =
    lit"${countryService.getCountryByCode(code).getOrElse("")}"

  private def name(answer: Name): Content =
   lit"${answer.firstName} ${answer.middleName.map(s => s.concat(" ")).getOrElse("")}${answer.lastName}"

  private def yesOrNo(answer: Boolean): Content =
    if (answer) {
      msg"site.yes"
    } else {
      msg"site.no"
    }
}

object CheckYourAnswersHelper {

  private val dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
}
