#!/bin/bash

echo ""
echo "Applying migration WhatIsTheDateOfBirth"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /whatIsTheDateOfBirth                  controllers.WhatIsTheDateOfBirthController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /whatIsTheDateOfBirth                  controllers.WhatIsTheDateOfBirthController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeWhatIsTheDateOfBirth                        controllers.WhatIsTheDateOfBirthController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeWhatIsTheDateOfBirth                        controllers.WhatIsTheDateOfBirthController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "whatIsTheDateOfBirth.title = WhatIsTheDateOfBirth" >> ../conf/messages.en
echo "whatIsTheDateOfBirth.heading = WhatIsTheDateOfBirth" >> ../conf/messages.en
echo "whatIsTheDateOfBirth.hint = For example, 12 11 2007" >> ../conf/messages.en
echo "whatIsTheDateOfBirth.checkYourAnswersLabel = WhatIsTheDateOfBirth" >> ../conf/messages.en
echo "whatIsTheDateOfBirth.error.required.all = Enter the whatIsTheDateOfBirth" >> ../conf/messages.en
echo "whatIsTheDateOfBirth.error.required.two = The whatIsTheDateOfBirth" must include {0} and {1} >> ../conf/messages.en
echo "whatIsTheDateOfBirth.error.required = The whatIsTheDateOfBirth must include {0}" >> ../conf/messages.en
echo "whatIsTheDateOfBirth.error.invalid = Enter a real WhatIsTheDateOfBirth" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsTheDateOfBirthUserAnswersEntry: Arbitrary[(WhatIsTheDateOfBirthPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[WhatIsTheDateOfBirthPage.type]";\
    print "        value <- arbitrary[Int].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsTheDateOfBirthPage: Arbitrary[WhatIsTheDateOfBirthPage.type] =";\
    print "    Arbitrary(WhatIsTheDateOfBirthPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(WhatIsTheDateOfBirthPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def whatIsTheDateOfBirth: Option[Row] = userAnswers.get(WhatIsTheDateOfBirthPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"whatIsTheDateOfBirth.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(Literal(answer.format(dateFormatter))),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.WhatIsTheDateOfBirthController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"whatIsTheDateOfBirth.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration WhatIsTheDateOfBirth completed"
