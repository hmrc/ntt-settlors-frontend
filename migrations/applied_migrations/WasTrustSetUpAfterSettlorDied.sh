#!/bin/bash

echo ""
echo "Applying migration WasTrustSetUpAfterSettlorDied"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /wasTrustSetUpAfterSettlorDied                        controllers.willtrust.WasTrustSetUpAfterSettlorDiedController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /wasTrustSetUpAfterSettlorDied                        controllers.willtrust.WasTrustSetUpAfterSettlorDiedController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeWasTrustSetUpAfterSettlorDied                  controllers.willtrust.WasTrustSetUpAfterSettlorDiedController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeWasTrustSetUpAfterSettlorDied                  controllers.willtrust.WasTrustSetUpAfterSettlorDiedController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "wasTrustSetUpAfterSettlorDied.title = wasTrustSetUpAfterSettlorDied" >> ../conf/messages.en
echo "wasTrustSetUpAfterSettlorDied.heading = wasTrustSetUpAfterSettlorDied" >> ../conf/messages.en
echo "wasTrustSetUpAfterSettlorDied.checkYourAnswersLabel = wasTrustSetUpAfterSettlorDied" >> ../conf/messages.en
echo "wasTrustSetUpAfterSettlorDied.error.required = Select yes if wasTrustSetUpAfterSettlorDied" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWasTrustSetUpAfterSettlorDiedUserAnswersEntry: Arbitrary[(WasTrustSetUpAfterSettlorDiedPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[WasTrustSetUpAfterSettlorDiedPage.type]";\
    print "        value <- arbitrary[Boolean].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWasTrustSetUpAfterSettlorDiedPage: Arbitrary[WasTrustSetUpAfterSettlorDiedPage.type] =";\
    print "    Arbitrary(WasTrustSetUpAfterSettlorDiedPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(WasTrustSetUpAfterSettlorDiedPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def wasTrustSetUpAfterSettlorDied: Option[Row] = userAnswers.get(WasTrustSetUpAfterSettlorDiedPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"wasTrustSetUpAfterSettlorDied.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(yesOrNo(answer)),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.WasTrustSetUpAfterSettlorDiedController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"wasTrustSetUpAfterSettlorDied.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration WasTrustSetUpAfterSettlorDied completed"
