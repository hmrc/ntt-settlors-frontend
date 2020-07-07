#!/bin/bash

echo ""
echo "Applying migration WhatIsCountryOfNationality"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /whatIsCountryOfNationality                        controllers.willtrust.WhatIsCountryOfNationalityController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /whatIsCountryOfNationality                        controllers.willtrust.WhatIsCountryOfNationalityController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeWhatIsCountryOfNationality                  controllers.willtrust.WhatIsCountryOfNationalityController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeWhatIsCountryOfNationality                  controllers.willtrust.WhatIsCountryOfNationalityController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "whatIsCountryOfNationality.title = whatIsCountryOfNationality" >> ../conf/messages.en
echo "whatIsCountryOfNationality.heading = whatIsCountryOfNationality" >> ../conf/messages.en
echo "whatIsCountryOfNationality.checkYourAnswersLabel = whatIsCountryOfNationality" >> ../conf/messages.en
echo "whatIsCountryOfNationality.error.required = Enter whatIsCountryOfNationality" >> ../conf/messages.en
echo "whatIsCountryOfNationality.error.length = WhatIsCountryOfNationality must be 100 characters or less" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsCountryOfNationalityUserAnswersEntry: Arbitrary[(WhatIsCountryOfNationalityPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[WhatIsCountryOfNationalityPage.type]";\
    print "        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsCountryOfNationalityPage: Arbitrary[WhatIsCountryOfNationalityPage.type] =";\
    print "    Arbitrary(WhatIsCountryOfNationalityPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(WhatIsCountryOfNationalityPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def whatIsCountryOfNationality: Option[Row] = userAnswers.get(WhatIsCountryOfNationalityPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"whatIsCountryOfNationality.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(lit\"$answer\"),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.WhatIsCountryOfNationalityController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"whatIsCountryOfNationality.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration WhatIsCountryOfNationality completed"
