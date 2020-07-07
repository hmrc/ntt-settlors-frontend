#!/bin/bash

echo ""
echo "Applying migration IsSettlorIndividualOrBusiness"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /isSettlorIndividualOrBusiness                        controllers.livingsettlor.IsSettlorIndividualOrBusinessController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /isSettlorIndividualOrBusiness                        controllers.livingsettlor.IsSettlorIndividualOrBusinessController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeIsSettlorIndividualOrBusiness                  controllers.livingsettlor.IsSettlorIndividualOrBusinessController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeIsSettlorIndividualOrBusiness                  controllers.livingsettlor.IsSettlorIndividualOrBusinessController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "isSettlorIndividualOrBusiness.title = isSettlorIndividualOrBusiness" >> ../conf/messages.en
echo "isSettlorIndividualOrBusiness.heading = isSettlorIndividualOrBusiness" >> ../conf/messages.en
echo "isSettlorIndividualOrBusiness.checkYourAnswersLabel = isSettlorIndividualOrBusiness" >> ../conf/messages.en
echo "isSettlorIndividualOrBusiness.error.required = Select yes if isSettlorIndividualOrBusiness" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryIsSettlorIndividualOrBusinessUserAnswersEntry: Arbitrary[(IsSettlorIndividualOrBusinessPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[IsSettlorIndividualOrBusinessPage.type]";\
    print "        value <- arbitrary[Boolean].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryIsSettlorIndividualOrBusinessPage: Arbitrary[IsSettlorIndividualOrBusinessPage.type] =";\
    print "    Arbitrary(IsSettlorIndividualOrBusinessPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(IsSettlorIndividualOrBusinessPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def isSettlorIndividualOrBusiness: Option[Row] = userAnswers.get(IsSettlorIndividualOrBusinessPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"isSettlorIndividualOrBusiness.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(yesOrNo(answer)),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.IsSettlorIndividualOrBusinessController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"isSettlorIndividualOrBusiness.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration IsSettlorIndividualOrBusiness completed"
