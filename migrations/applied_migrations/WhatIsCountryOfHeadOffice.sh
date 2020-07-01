#!/bin/bash

echo ""
echo "Applying migration WhatIsCountryOfHeadOffice"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /whatIsCountryOfHeadOffice                        controllers.livingsettlor.WhatIsCountryOfHeadOfficeController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /whatIsCountryOfHeadOffice                        controllers.livingsettlor.WhatIsCountryOfHeadOfficeController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeWhatIsCountryOfHeadOffice                  controllers.livingsettlor.WhatIsCountryOfHeadOfficeController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeWhatIsCountryOfHeadOffice                  controllers.livingsettlor.WhatIsCountryOfHeadOfficeController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "whatIsCountryOfHeadOffice.title = whatIsCountryOfHeadOffice" >> ../conf/messages.en
echo "whatIsCountryOfHeadOffice.heading = whatIsCountryOfHeadOffice" >> ../conf/messages.en
echo "whatIsCountryOfHeadOffice.checkYourAnswersLabel = whatIsCountryOfHeadOffice" >> ../conf/messages.en
echo "whatIsCountryOfHeadOffice.error.required = Enter whatIsCountryOfHeadOffice" >> ../conf/messages.en
echo "whatIsCountryOfHeadOffice.error.length = WhatIsCountryOfHeadOffice must be 10 characters or less" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsCountryOfHeadOfficeUserAnswersEntry: Arbitrary[(WhatIsCountryOfHeadOfficePage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[WhatIsCountryOfHeadOfficePage.type]";\
    print "        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsCountryOfHeadOfficePage: Arbitrary[WhatIsCountryOfHeadOfficePage.type] =";\
    print "    Arbitrary(WhatIsCountryOfHeadOfficePage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(WhatIsCountryOfHeadOfficePage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def whatIsCountryOfHeadOffice: Option[Row] = userAnswers.get(WhatIsCountryOfHeadOfficePage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"whatIsCountryOfHeadOffice.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(lit\"$answer\"),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.WhatIsCountryOfHeadOfficeController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"whatIsCountryOfHeadOffice.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration WhatIsCountryOfHeadOffice completed"
