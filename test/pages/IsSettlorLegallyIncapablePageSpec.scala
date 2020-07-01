package pages

import pages.behaviours.PageBehaviours

class IsSettlorLegallyIncapablePageSpec extends PageBehaviours {

  "IsSettlorLegallyIncapablePage" - {

    beRetrievable[Boolean](IsSettlorLegallyIncapablePage)

    beSettable[Boolean](IsSettlorLegallyIncapablePage)

    beRemovable[Boolean](IsSettlorLegallyIncapablePage)
  }
}
