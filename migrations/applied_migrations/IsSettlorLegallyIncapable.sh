#!/bin/bash

echo ""
echo "Applying migration IsSettlorLegallyIncapable"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /isSettlorLegallyIncapable                        controllers.livingsettlor.IsSettlorLegallyIncapableController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /isSettlorLegallyIncapable                        controllers.livingsettlor.IsSettlorLegallyIncapableController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeIsSettlorLegallyIncapable                  controllers.livingsettlor.IsSettlorLegallyIncapableController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeIsSettlorLegallyIncapable                  controllers.livingsettlor.IsSettlorLegallyIncapableController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "isSettlorLegallyIncapable.title = isSettlorLegallyIncapable" >> ../conf/messages.en
echo "isSettlorLegallyIncapable.heading = isSettlorLegallyIncapable" >> ../conf/messages.en
echo "isSettlorLegallyIncapable.checkYourAnswersLabel = isSettlorLegallyIncapable" >> ../conf/messages.en
echo "isSettlorLegallyIncapable.error.required = Select yes if isSettlorLegallyIncapable" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryIsSettlorLegallyIncapableUserAnswersEntry: Arbitrary[(IsSettlorLegallyIncapablePage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[IsSettlorLegallyIncapablePage.type]";\
    print "        value <- arbitrary[Boolean].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryIsSettlorLegallyIncapablePage: Arbitrary[IsSettlorLegallyIncapablePage.type] =";\
    print "    Arbitrary(IsSettlorLegallyIncapablePage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(IsSettlorLegallyIncapablePage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def isSettlorLegallyIncapable: Option[Row] = userAnswers.get(IsSettlorLegallyIncapablePage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"isSettlorLegallyIncapable.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(yesOrNo(answer)),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.IsSettlorLegallyIncapableController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"isSettlorLegallyIncapable.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration IsSettlorLegallyIncapable completed"
