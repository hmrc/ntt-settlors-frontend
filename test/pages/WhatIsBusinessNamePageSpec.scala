package pages

import pages.behaviours.PageBehaviours


class WhatIsBusinessNamePageSpec extends PageBehaviours {

  "WhatIsBusinessNamePage" - {

    beRetrievable[String](WhatIsBusinessNamePage)

    beSettable[String](WhatIsBusinessNamePage)

    beRemovable[String](WhatIsBusinessNamePage)
  }
}
