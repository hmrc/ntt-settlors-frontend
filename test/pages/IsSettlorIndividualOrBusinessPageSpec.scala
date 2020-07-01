package pages

import pages.behaviours.PageBehaviours

class IsSettlorIndividualOrBusinessPageSpec extends PageBehaviours {

  "IsSettlorIndividualOrBusinessPage" - {

    beRetrievable[Boolean](IsSettlorIndividualOrBusinessPage)

    beSettable[Boolean](IsSettlorIndividualOrBusinessPage)

    beRemovable[Boolean](IsSettlorIndividualOrBusinessPage)
  }
}
