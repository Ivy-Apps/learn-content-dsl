package ivy.learn.dsl

import ivy.learn.TextStyle

fun main() {

}

fun exampleLesson() = lessonContent {
  intro()
  whyQuestion()
  whyExplanation()
  arraysInternals()
  internalsQuestion()
  openQuestion("open") {
    question = "What is 2 + 2? (enter exact answer)"
    correctAnswer = "4"
  }
}

private fun LessonContentScope.intro() {
  text("intro") {
    text = "Arrays"
    style = TextStyle.Heading
  }
  text("intro_text") {
    text = "The most fundamental data structure"
  }
}

private fun LessonContentScope.whyQuestion() {
  question("why_q") {
    question = "Why arrays are so important?"
    clarification = "Think about their properties."
    answer(
      text = "Lorem ipsum",
      correct = true,
      explanation = "Lorem ipsum"
    )
    answer(
      text = "Lorem ipsum 2",
      explanation = "test"
    )
  }
}

private fun LessonContentScope.whyExplanation() {
  text("why_explain") {
    text = "Lorem ipsum"
    style = TextStyle.BodySpacingLarge
  }
}

private fun LessonContentScope.arraysInternals() {
  image("internals_img") {
    imageUrl = "url"
  }
  text("internals") {
    text = "lorem ipsum"
    style = TextStyle.BodySpacingMedium
  }
}

private fun LessonContentScope.internalsQuestion() {
  question("internals_q") {
    question = "Why Lorem ipsum?"
    answer(
      text = "Abc",
      correct = true,
      explanation = "..."
    )
    answer(
      text = "Def",
      correct = true,
      explanation = "..."
    )
    answer(
      text = "42",
      explanation = "..."
    )
    answer(
      text = "3.14",
      explanation = "..."
    )
  }
}