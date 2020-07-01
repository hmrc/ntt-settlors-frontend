package forms

import forms.behaviours.BooleanFieldBehaviours
import play.api.data.FormError

class DoYouKnowCountryOfHeadOfficeFormProviderSpec extends BooleanFieldBehaviours {

  val requiredKey = "doYouKnowCountryOfHeadOffice.error.required"
  val invalidKey = "error.boolean"

  val form = new DoYouKnowCountryOfHeadOfficeFormProvider()()

  ".value" - {

    val fieldName = "value"

    behave like booleanField(
      form,
      fieldName,
      invalidError = FormError(fieldName, invalidKey)
    )

    behave like mandatoryField(
      form,
      fieldName,
      requiredError = FormError(fieldName, requiredKey)
    )
  }
}
